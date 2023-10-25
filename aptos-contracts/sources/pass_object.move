// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::pass_object {

    friend aptos_constantinople_demo::player;
    friend aptos_constantinople_demo::movable;
    friend aptos_constantinople_demo::encounterable;
    friend aptos_constantinople_demo::monster;
    friend aptos_constantinople_demo::obstruction;
    friend aptos_constantinople_demo::encounter_trigger;
    friend aptos_constantinople_demo::player_position;
    friend aptos_constantinople_demo::encounter;
    friend aptos_constantinople_demo::owned_monsters;
    friend aptos_constantinople_demo::random_seed;
    friend aptos_constantinople_demo::map;

    /// read-only 'hot potato' wrapper.
    struct PassObject<T: store> {
        value: T,
    }

    public(friend) fun new<T: store>(value: T): PassObject<T> {
        PassObject { value }
    }

    public(friend) fun extract<T: store>(pass_object: PassObject<T>): T {
        let PassObject { value } = pass_object;
        value
    }

    public fun borrow<T: store>(pass_object: &PassObject<T>): &T {
        &pass_object.value
    }
}

