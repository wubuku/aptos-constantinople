// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::movable_aggregate {
    use aptos_constantinople_demo::movable;
    use aptos_constantinople_demo::movable_create_logic;

    friend aptos_constantinople_demo::rpg_service;

    public(friend) fun create(
        account: &signer,
        store: address,
        player_id: address,
        value: bool,
    ) {
        let movable_created = movable_create_logic::verify(
            account,
            store,
            player_id,
            value,
        );
        let movable = movable_create_logic::mutate(
            account,
            store,
            &movable_created,
        );
        movable::add_movable(store, movable);
        movable::emit_movable_created(store, movable_created);
    }

}
