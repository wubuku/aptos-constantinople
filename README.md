# Constantinople

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


## Test Example

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

In the directory `aptos-contracts`, execute the compilation, which should now succeed:

```shell
aptos move compile --named-addresses aptos_constantinople_demo=default
```

At this point, the coding phase of the application development is complete! Isn't it very simple?

---

Next, we will deploy and test the Demo application.

### Publish the Aptos contracts

Execute the following command in the directory `aptos-contracts` to publish the contracts to the chain:

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
