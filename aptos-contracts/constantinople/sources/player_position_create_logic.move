module aptos_constantinople_demo::player_position_create_logic {
    use aptos_constantinople_demo::player_position;
    use aptos_constantinople_demo_map::position::Position;

    friend aptos_constantinople_demo::player_position_aggregate;

    public(friend) fun verify(
        account: &signer,
        player_id: address,
        position: Position,
    ): player_position::PlayerPositionCreated {
        let _ = account;
        player_position::asset_player_position_not_exists(player_id);
        player_position::new_player_position_created(
            player_id,
            position,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        player_position_created: &player_position::PlayerPositionCreated,
    ): player_position::PlayerPosition {
        let (player_id, position) = player_position::get_player_position_created_all_properties(player_position_created);
        player_position::create_player_position(
            player_id,
            position,
        )
    }

}
