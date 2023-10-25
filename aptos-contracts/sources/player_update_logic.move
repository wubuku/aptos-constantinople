module aptos_constantinople_demo::player_update_logic {
    use aptos_constantinople_demo::player;
    use aptos_constantinople_demo::player_updated;

    friend aptos_constantinople_demo::player_aggregate;

    public(friend) fun verify(
        account: &signer,
        value: bool,
        player: &player::Player,
    ): player::PlayerUpdated {
        let _ = account;
        player::new_player_updated(
            player,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        player_updated: &player::PlayerUpdated,
        player: player::Player,
    ): player::Player {
        let value = player_updated::value(player_updated);
        let player_id = player::player_id(&player);
        let _ = player_id;
        player::set_value(&mut player, value);
        player
    }

}
