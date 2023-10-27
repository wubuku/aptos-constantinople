module aptos_constantinople_demo::player_position_update_logic {
    use aptos_constantinople_demo::player_position;
    use aptos_constantinople_demo::position::Position;

    friend aptos_constantinople_demo::player_position_aggregate;

    public(friend) fun verify(
        account: &signer,
        position: Position,
        player_position: &player_position::PlayerPosition,
    ): player_position::PlayerPositionUpdated {
        let _ = account;
        player_position::new_player_position_updated(
            player_position,
            position,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        player_position_updated: &player_position::PlayerPositionUpdated,
        player_position: player_position::PlayerPosition,
    ): player_position::PlayerPosition {
        let player_id = player_position::player_id(&player_position);
        let (_, position) = player_position::get_player_position_updated_all_properties(player_position_updated);
        let _ = player_id;
        player_position::set_position(&mut player_position, position);
        player_position
    }

}
