# Constantinople

## 原版分析

实体的 key（ID）都是 address，感觉这个是目前 obelisk 架的限制。

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

