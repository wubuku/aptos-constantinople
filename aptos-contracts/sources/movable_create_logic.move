module aptos_constantinople_demo::movable_create_logic {
    use aptos_constantinople_demo::movable;

    friend aptos_constantinople_demo::movable_aggregate;

    public(friend) fun verify(
        account: &signer,
        store: address,      
        player_id: address,
        value: bool,
    ): movable::MovableCreated {
        let _ = account;
        movable::asset_movable_not_exists(store, player_id);
        movable::new_movable_created(
            player_id,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store: address,
        movable_created: &movable::MovableCreated,
    ): movable::Movable {
        let (player_id, value) = movable::get_movable_created_all_properties(movable_created);
        movable::create_movable(
            store,
            player_id,
            value,
        )
    }

}
