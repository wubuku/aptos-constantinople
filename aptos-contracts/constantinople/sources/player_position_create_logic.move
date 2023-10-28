module aptos_constantinople_demo::player_position_create_logic {
    use aptos_constantinople_demo::player_position;
    use aptos_constantinople_demo::position::Position;

    friend aptos_constantinople_demo::player_position_aggregate;

    public(friend) fun verify(
        account: &signer,
        store_address: address,      
        player_id: address,
        position: Position,
    ): player_position::PlayerPositionCreated {
        let _ = account;
        player_position::asset_player_position_not_exists(store_address, player_id);
        player_position::new_player_position_created(
            player_id,
            position,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store_address: address,
        player_position_created: &player_position::PlayerPositionCreated,
    ): player_position::PlayerPosition {
        let (player_id, position) = player_position::get_player_position_created_all_properties(player_position_created);
        player_position::create_player_position(
            store_address,
            player_id,
            position,
        )
    }

}
