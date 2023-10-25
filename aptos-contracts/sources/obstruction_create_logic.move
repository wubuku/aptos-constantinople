module aptos_constantinople_demo::obstruction_create_logic {
    use aptos_constantinople_demo::obstruction;
    use aptos_constantinople_demo::obstruction_created;
    use aptos_constantinople_demo::position::Position;

    friend aptos_constantinople_demo::obstruction_aggregate;

    public(friend) fun verify(
        account: &signer,
        position: Position,
        value: bool,
    ): obstruction::ObstructionCreated {
        let _ = account;
        obstruction::asset_obstruction_not_exists(position);
        obstruction::new_obstruction_created(
            position,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        obstruction_created: &obstruction::ObstructionCreated,
    ): obstruction::Obstruction {
        let position = obstruction_created::position(obstruction_created);
        let value = obstruction_created::value(obstruction_created);
        obstruction::create_obstruction(
            position,
            value,
        )
    }

}
