# Constantinople

原版的 [constantinople](https://github.com/0xobelisk/constantinople) 是一个基于全链游戏引擎 [obelisk](https://obelisk.build) 开发的运行在 Sui 上的游戏。

下面我们尝试使用 dddappp 低代码开发工具，将这个游戏迁移到 Aptos 上。

## 原版分析

实体的 key（ID）都是 address，感觉这个是目前 obelisk 框架的限制。

如果我们做领域分析的时候，认为某个实体的领域 ID 不是 address 呢？那么实现的时候可以将这个领域 ID 映射为 address（比如通过一个 hash 函数）。

### 实体

只有一个 bool 类型的属性（value）的实体：

* movable：Player 是否可以移动。如果在这里 player 没有被设置为 true，那么就无法“移动”。
* obstruction：表示障碍物，bool 类型的 value 属性没有被使用到。这个实体的 key 是从位置坐标（x，y）派生的地址。
* player：表示玩家。属性 value 没有被使用到。
* encounter_trigger：表示某个位置上是否可以“触发相遇”的执行逻辑。属性 value 没有被使用到。这个实体的 key 是从位置坐标派生的。
* encounterable：表示某个玩家是否可以“触发相遇”。相当于一个白名单。不在这里名单里面的玩家其实不能“触发相遇”。属性 value 没有被用到。

其他只有一个属性的实体：

* monster：表示怪兽（👾）。value 表示👾的类型。抓👾失败后，该实体的实例会被移除。
* owned_monsters：玩家拥有的👾。实体的 key 是玩家地址。

有多个属性的实体：

* position：记录玩家的当前位置。
* encounter：用于相遇后的处理。key 是玩家。属性包括👾ID，抓👾的尝试次数，另外一个 bool 类型的属性应该没有用到。
* random_seed：随机种子数信息。这是一个单例对象。
* Map：地图。这是一个单例对象。

表示事件的特殊“实体”：

* catch_result：表示抓👾的结果。其实它不是一般意义的实体，更多是一个用于表示事件的结构体。在 obelisk 的 schema 中，设置了 `ephemeral: true`。 在 throw_ball 操作中会 emit 这个事件。

### 操作

操作（public entry fun）：

* init_map：通过 map 初始化 obstruction 和 encounter 信息。
* register：玩家注册。初始化玩家的 position，movable，encounterable 信息。
* move_t: 玩家移动。修改 position 实体。如果和👾相遇，会修改 monster、encounter 实体。
* throw_ball：抓👾。可能修改 owned_monsters 实体，以及 monster、encounter 实体。

## 编码

[TBD]

### 编写 DDDML 模型文件

在代码库的根目录下的 `dddml` 目录，创建一个名为 `constantinople.dddml` DDDML 文件。文件内容像[这样](./dddml/constantinople.yaml)。

[TBD]

### 运行 dddappp 项目创建工具

在代码库的根目录，执行：

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

[TBD]

### 实现业务逻辑

dddappp CLI 已经为我们生成了应用的大部分代码。

只有以下文件是需要我们填充业务逻辑的：

* [owned_monsters_add_monster_logic.move](aptos-contracts/constantinople/sources/owned_monsters_add_monster_logic.move)。这是一个在聚合（实体）上定义方法的例子。我们不想直接生成和使用 UPDATE 方法。我们想让对单个实体的操作方法具有更好的语义。 
* [rpg_service.move](aptos-contracts/constantinople/sources/rpg_service.move)。领域服务的实现。这里的 public entry fun 的签名部分，是工具已经生成好的，我们只需要实现方法体。


## 测试应用

[TBD]

### 部署合约

在目录 `aptos-contracts/constantinople-map` 下，执行：

```shell
aptos move publish --named-addresses aptos_constantinople_demo=default --assume-yes
```

在目录 `aptos-contracts/constantinople` 下，执行：

```shell
aptos move publish --named-addresses aptos_constantinople_demo=default --assume-yes
```

在目录 `aptos-contracts/constantinople-store` 下，执行：

```shell
aptos move publish --named-addresses aptos_constantinople_demo=default --assume-yes
```

### 初始化

执行合约的初始化操作：

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

比如得到的资源账户的地址是：0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f

初始化地图信息：

```shell
aptos move run --function-id 'default::map_service::init_map' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f --assume-yes
```

你可以使用浏览器来查看这个资源账户下都有哪些资源：

```text
https://explorer.aptoslabs.com/account/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/resources?network=devnet
```

#### 获取 EncounterTrigger 的创建事件（EncounterTriggerCreated）

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/{RESOURCE_ACCOUNT_ADDRESS}/events/{ACCOUNT_ADDRESS}::encounter_trigger::Events/encounter_trigger_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

我们下面假设 `{ACCOUNT_ADDRESS}` 是 `0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7`。 那么执行：

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::encounter_trigger::Events/encounter_trigger_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

可以看到有哪些位置可以“触发相遇”：

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

### 玩家注册

执行：

```shell
aptos move run --function-id 'default::rpg_service::register' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f u64:10 u64:2 --assume-yes
```

查看 `EncounterableCreated` 事件：

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::encounterable::Events/encounterable_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```


### 玩家移动

交替执行下面两行命令， 直到出现错误提示 `...::rpg_service: ECannotMoveInEncounter(0x4): error cannot move during an encounter` 为止：

```shell
aptos move run --function-id 'default::rpg_service::player_move' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f u64:9 u64:2 --assume-yes
```

```shell
aptos move run --function-id 'default::rpg_service::player_move' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f u64:10 u64:2 --assume-yes
```

然后，查看 `EncounterCreated` 事件：

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::encounter::Events/encounter_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

可以看到玩家和👾相遇了。

### 抓👾

反复执行下面的命令，直到返回提示 `...Move abort...` 为止：

```shell
aptos move run --function-id 'default::rpg_service::throw_ball' --args address:0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f --assume-yes
```

然后，查看 `OwnedMonstersCreated` 事件：

```shell
curl --request GET \
  --url 'https://fullnode.devnet.aptoslabs.com/v1/accounts/0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f/events/0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::owned_monsters::Events/owned_monsters_created_handle?start=0&limit=100' \
  --header 'Accept: application/json'
```

如果返回类似下面的结果，即表示成功抓住了👾：

```json
[{"version":"776829","guid":{"creation_number":"14","account_address":"0x309015d18113265726eaf676ae4b05954cfe0c18934569f9d46aea50a514321f"},"sequence_number":"0","type":"0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7::owned_monsters::OwnedMonstersCreated","data":{"monsters":["0x3fe946e82fd59a0ecf0276ac0f40f6dcc270cca03ab5796982bd29806d096033"],"player_id":"0x48fce222d854eefc165e642797933bd71f8424c52e889e07044b5c5ddc762de7"}}]
```

