module aptos_constantinople_demo::obstruction_create_logic {
    use aptos_constantinople_demo::obstruction;
    use aptos_constantinople_demo::position::Position;

    friend aptos_constantinople_demo::obstruction_aggregate;

    public(friend) fun verify(
        account: &signer,
        store_address: address,      
        position: Position,
        value: bool,
    ): obstruction::ObstructionCreated {
        let _ = account;
        obstruction::asset_obstruction_not_exists(store_address, position);
        obstruction::new_obstruction_created(
            position,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store_address: address,
        obstruction_created: &obstruction::ObstructionCreated,
    ): obstruction::Obstruction {
        let (position, value) = obstruction::get_obstruction_created_all_properties(obstruction_created);
        obstruction::create_obstruction(
            store_address,
            position,
            value,
        )
    }

}
