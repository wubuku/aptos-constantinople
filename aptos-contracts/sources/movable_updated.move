// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::movable_updated {

    use aptos_constantinople_demo::movable::{Self, MovableUpdated};

    public fun player_id(movable_updated: &MovableUpdated): address {
        movable::movable_updated_player_id(movable_updated)
    }

    public fun value(movable_updated: &MovableUpdated): bool {
        movable::movable_updated_value(movable_updated)
    }

}
