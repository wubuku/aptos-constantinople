# Constantinople

The original [constantinople](https://github.com/0xobelisk/constantinople) is a game that was developed using the full-chain game engine [obelisk](https://obelisk.build) and runs on Sui.

Here we try to migrate this game to Aptos using the dddappp low-code development tool.

## Analysis of the Original Version

The key (ID) of an entity is address, which feels like a limitation of the current obelisk framework.

What if when we do domain analysis, we think that the domain ID of an entity is not address? 
Well, we can map the domain ID to address (e.g., through a hash function) when we implement it.

### Entities

Entities that have only one property (value) of type `bool`:

* `movable`: whether the player can move. If player is not set to true here, then it cannot move.
* `obstruction`: represents an obstruction, the value property of type bool is not used. The key of this entity is the address derived from the position coordinates (x, y).
* `player`: represents the player. The value property is not used.
* `encounter_trigger`: Indicates whether an encounter can be "triggered" at a position. The value property is not used. The key of this entity is derived from the position coordinates.
* `encounterable`: Indicates if a player can "trigger an encounter". This is like a white list. Players not on this list cannot actually "trigger an encounter". The value property is not used.

Other entities have only one property:

* `monster`: means 👾. `value` means the type of 👾. Instances of this entity are removed when catching 👾 fails.
* `owned_monsters`: 👾s owned by the player. The key of the entity is the player address.

Entities with multiple properties:

* `position`: keeps track of the player's current position.
* `encounter`: used for post encounter processing. Key is the player. Properties include 👾ID, number of attempts to catch 👾, and another `bool` type property that is not used.
* `random_seed`: random seed number information. This is a singleton object.
* `map`: map. This is a single instance object.

There is a special "entity" that represents the event:

* `catch_result`: the result of catching 👾. It's not really an entity in the usual sense, but more of a structure for representing event. In obelisk's schema, `ephemeral: true` is set. This event is emitted in the `throw_ball` operation.

### Operations

Operations (`public entry fun`s):

* `init_map`: initialize `obstruction` and `encounter` information with the map.
* `register`: player register. Initialize player's `position`, `movable` and `encounterable` information.
* `move_t`: player move. Modifies the `position` entity. If encountered with 👾, will modify `monster`, `encounter` entity.
* `throw_ball`: catch 👾. May modify the `owned_monsters` entity, as well as the `monster`, `encounter` entities.

## Programming

### Write DDDML Model File

In the `dddml` directory in the root of the repository, create a DDDML file named `constantinople.dddml`. Like [this](./dddml/constantinople.yaml).


> **Tip**
>
> About DDDML, here is an introductory article: ["Introducing DDDML: The Key to Low-Code Development for Decentralized Applications"](https://github.com/wubuku/Dapp-LCDP-Demo/blob/main/IntroducingDDDML.md).


### Run dddappp Project Creation Tool

#### Update dddappp Docker Image

Since the dddappp v0.0.1 image is updated frequently, you may be required to manually delete the image and pull it again before `docker run`.

```shell
# If you have already run it, you may need to Clean Up Exited Docker Containers first
docker rm $(docker ps -aq --filter "ancestor=wubuku/dddappp-aptos:0.0.1")
# remove the image
docker image rm wubuku/dddappp-aptos:0.0.1
# pull the image
git pull wubuku/dddappp-aptos:0.0.1
```

---

In repository root directory, run:

```shell
docker run \
-v .:/myapp \
wubuku/dddappp-aptos:0.0.1 \
--dddmlDirectoryPath /myapp/dddml \
--boundedContextName Test.AptosConstantinopleDemo \
--aptosMoveProjectDirectoryPath /myapp/aptos-contracts \
--boundedContextAptosPackageName AptosConstantinopleDemo \
--boundedContextAptosNamedAddress aptos_constantinople_demo \
--boundedContextJavaPackageName org.test.aptosconstantinopledemo \
--javaProjectsDirectoryPath /myapp/aptos-java-service \
--javaProjectNamePrefix aptosconstantinopledemo \
--pomGroupId test.aptosconstantinopledemo
```

The command parameters above are straightforward:

* The first line indicates mounting your local directory into the `/myapp` directory inside the container.
* `dddmlDirectoryPath` is the directory where DDDML model files are located. It should be a readable directory path in the container.
* Interpret the value of parameter `boundedContextName` as the name of your application you want to develop. When there are multiple parts in your name, separate them with dots and use PascalCase naming style for each part. Bounded-context is a term in Domain-driven design (DDD) that refers to a specific problem domain scope that contains specific business boundaries, constraints, and language. If you don't understand this concept for now, it's not a big deal.
* `aptosMoveProjectDirectoryPath` is directory path where on-chain Aptos contract code is placed. It should be a readable and writable directory path in container.
* `boundedContextAptosPackageName` is package name of on-chain Aptos contracts. It's recommended to use PascalCase naming style.
* `boundedContextAptosNamedAddress` is default named address of on-chain Aptos contracts. It's recommended to use snake_case naming style.
* `boundedContextJavaPackageName` is Java package name of off-chain service. According to Java naming conventions, it should be all lowercase and parts should be separated by dots.
* `javaProjectsDirectoryPath` is directory path where off-chain service code is placed. Off-chain service consists of multiple modules (projects). It should be a readable and writable directory path in container.
* `javaProjectNamePrefix` is name prefix of each module of off-chain service. It's recommended to use an all-lowercase name.
* `pomGroupId` is GroupId of off-chain service. We use Maven as project management tool for off-chain service. It should be all lowercase and parts should be separated by dots.

After executing above command successfully, a directory `aptos-contracts` should be added to local current directory.


### Implementing Business Logic

If CRUD is all the business logic you need, You **don't** need to write a single line of code other than the DDDML model above.

The tool has generated some files with the suffix `_logic.move` in the directory `aptos-contracts/sources`.

Generally, these files contain the scaffolding code of functions that implement business logic, namely the signature part of the functions. You just need to fill in the implementation part of the functions. However, in this example, the `MOVE_CRUD_IT` preprocessor already generates the full CRUD methods for us.

---

Only the following files are required for us to fill in the business logic:

* [owned_monsters_add_monster_logic.move](aptos-contracts/constantinople/sources/owned_monsters_add_monster_logic.move). This is an example of defining a method on an aggregate (entity). We don't want to generate and use the UPDATE method directly. We want to have better semantics for methods that operate on individual entities.
* [rpg_service.move](aptos-contracts/constantinople/sources/rpg_service.move). The implementation of the domain service. The signature part of the public entry fun here is already generated by the tool, we just need to implement the function body.


## Test Application

### Compile Aptos Move contracts

#### Some preparatory work that may need to be done

It should be noted that below we assume that you will publish the Move contract to the Aptos devnet, so we skip the explanation of the modifications to some configuration files required for publishing to other networks.

We can create a new account on devnet to perform the following test.

Confirm that Aptos CLI is installed and enter the directory `aptos-contracts`, then run:

```shell
aptos init
# Press Enter to confirm using the default values:
aptos account fund-with-faucet --account default --amount 50000000000
# View Aptos Profiles:
aptos config show-profiles
```

It should display similar information:

```shell
{
  "Result": {
    "default": {
      "has_private_key": true,
      "public_key": "...",
      "account": "{ACCOUNT_ADDRESS}",
      "rest_url": "https://fullnode.devnet.aptoslabs.com",
      "faucet_url": "https://faucet.devnet.aptoslabs.com"
    }
  }
}
```

---

In the directory `aptos-contracts/constantinople`, execute the compilation, which should now succeed:

```shell
aptos move compile --named-addresses aptos_constantinople_demo=default
```

At this point, the coding phase of the application development is complete! Isn't it very simple?

---

Next, we will deploy and test the Demo application.

### Publish the Aptos contracts

In the directory `aptos-contracts/constantinople-map`, execute:

```shell
aptos move publish --named-addresses aptos_constantinople_demo=default --assume-yes
```

If the command is executed successfully, it should display similar information:

```shell
{
  "Result": {
    "transaction_hash": "{TRANSACTION_HASH}"
    "gas_used": 20722,
    "gas_unit_price": 100,
    "sender": "{ACCOUNT_ADDRESS}",
    "sequence_number": 0,
    "success": true,
    "timestamp_us": 1688909362156606,
    "version": 11446005,
    "vm_status": "Executed successfully"
  }
}
```

In the directory `aptos-contracts/constantinople`, execute:

```shell
aptos move publish --named-addresses aptos_constantinople_demo=default --assume-yes
```

In the directory `aptos-contracts/constantinople-store`, execute:

```shell
aptos move publish --named-addresses aptos_constantinople_demo=default --assume-yes
```

### Initialization

Performs the initialization operation of the contracts:

```shell
aptos move run --function-id 'default::aptos_constantinople_demo_store_init::initialize' --assume-yes
```

#### Get Resource Account Address

Our contracts use a separate resource account to hold information of articles and comments.

You can get the address of this resource account by using the following command:

```shell
curl https://fullnode.devnet.aptoslabs.com/v1/accounts/{ACCOUNT_ADDRESS}/resource/{ACCOUNT_ADDRESS}::resource_account::ResourceAccount
```

The output is similar to the following:

```json
{"type":"{ACCOUNT_ADDRESS}::resource_account::ResourceAccount","data":{"cap":{"account":"{RESOURCE_ACCOUNT_ADDRESS}"}}}
```

In the location `{RESOURCE_ACCOUNT_ADDRESS}` above, the address of the resource account will be displayed.

In the following sample command, we assume that the address of the resource account obtained is: `0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f`.

#### Initialize Map Information

Execute:

```shell
aptos move run --function-id 'default::map_service::init_map' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f --assume-yes
```

You can use a web browser to see what resources are available under the resource account:

```text
https://explorer.aptoslabs.com/account/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/resources?network=devnet
```

#### Getting the creation event of an EncounterTrigger (EncounterTriggerCreated)

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/{RESOURCE_ACCOUNT_ADDRESS}/events/{ACCOUNT_ADDRESS}::encounter_trigger::Events/encounter_trigger_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

We assume below that `{ACCOUNT_ADDRESS}` is `0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7`. Then execute:

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::encounter_trigger::Events/encounter_trigger_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

You can see where you can "trigger encounters":

```json
//...
  {
    "version": "483285",
    "guid": {
      "creation_number": "3",
      "account_address": "0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f"
    },
    "sequence_number": "0",
    "type": "0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::encounter_trigger::EncounterTriggerCreated",
    "data": {
      "position": {
        "x": "9",
        "y": "2"
      },
      "value": true
    }
  },
//...
```

### Player Registration

Execute:

```shell
aptos move run --function-id 'default::rpg_service::register' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f u64:10 u64:2 --assume-yes
```

We intentionally initialize the player's position to (10, 2). This position is only one step away from a position where an encounter can be "triggered".

Check out the `EncounterableCreated` event:

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::encounterable::Events/encounterable_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

### Player Movement

Execute the following two lines alternately until you get the error `... ::rpg_service: ECannotMoveInEncounter(0x4): error cannot move during an encounter`:

```shell
aptos move run --function-id 'default::rpg_service::player_move' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f u64:9 u64:2 --assume-yes
```

```shell
aptos move run --function-id 'default::rpg_service::player_move' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f u64:10 u64:2 --assume-yes
```

Then, look at the `EncounterCreated` event:

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::encounter::Events/encounter_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

The player can be seen encountering 👾.

### Catching 👾

Repeat the following command until you are returned to the prompt `... .Move abort... `:

```shell
aptos move run --function-id 'default::rpg_service::throw_ball' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f --assume-yes
```

Then, look for the `OwnedMonstersCreated` event:

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::owned_monsters::Events/owned_monsters_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

A successful capture of 👾 is indicated if a result similar to the following is returned:

```json
[{"version":"776829","guid":{"creation_number":"14","account_address":"0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f"},"sequence_number":"0","type":"0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::owned_monsters::OwnedMonstersCreated","data":{"monsters":["0x3fe946e82fd59a0ecf0276ac0f40f6dcc270cca03ab5796982bd29806d096033"],"player_id":"0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7"}}]
```

It's normal if you don't see such a result. This is because the probability of catching 👾 is not 100%.
You can repeat the "move" and "catch 👾" operations until you have caught 👾.

