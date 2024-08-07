// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo_map::obstruction_aggregate {
    use aptos_constantinople_demo_map::obstruction;
    use aptos_constantinople_demo_map::obstruction_create_logic;
    use aptos_constantinople_demo_map::position::{Self, Position};

    friend aptos_constantinople_demo_map::map_service;

    public(friend) fun create(
        account: &signer,
        store_address: address,
        position_x: u64,
        position_y: u64,
        value: bool,
    ) {
        let position: Position = position::new(
            position_x,
            position_y,
        );

        let obstruction_created = obstruction_create_logic::verify(
            account,
            store_address,
            position,
            value,
        );
        let obstruction = obstruction_create_logic::mutate(
            account,
            store_address,
            &obstruction_created,
        );
        obstruction::add_obstruction(store_address, obstruction);
        obstruction::emit_obstruction_created(store_address, obstruction_created);
    }

}
