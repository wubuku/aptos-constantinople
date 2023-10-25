module aptos_constantinople_demo::movable_update_logic {
    use aptos_constantinople_demo::movable;
    use aptos_constantinople_demo::movable_updated;

    friend aptos_constantinople_demo::movable_aggregate;

    public(friend) fun verify(
        account: &signer,
        value: bool,
        movable: &movable::Movable,
    ): movable::MovableUpdated {
        let _ = account;
        movable::new_movable_updated(
            movable,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        movable_updated: &movable::MovableUpdated,
        movable: movable::Movable,
    ): movable::Movable {
        let value = movable_updated::value(movable_updated);
        let player_id = movable::player_id(&movable);
        let _ = player_id;
        movable::set_value(&mut movable, value);
        movable
    }

}
