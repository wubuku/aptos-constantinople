module aptos_constantinople_demo::movable_create_logic {
    use aptos_constantinople_demo::movable;

    friend aptos_constantinople_demo::movable_aggregate;

    public(friend) fun verify(
        account: &signer,
        player_id: address,
        value: bool,
    ): movable::MovableCreated {
        let _ = account;
        movable::asset_movable_not_exists(player_id);
        movable::new_movable_created(
            player_id,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        movable_created: &movable::MovableCreated,
    ): movable::Movable {
        let player_id = movable::movable_created_player_id(movable_created);
        let value = movable::movable_created_value(movable_created);
        movable::create_movable(
            player_id,
            value,
        )
    }

}
