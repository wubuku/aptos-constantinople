module aptos_constantinople_demo::player_create_logic {
    use aptos_constantinople_demo::player;

    friend aptos_constantinople_demo::player_aggregate;

    public(friend) fun verify(
        account: &signer,
        player_id: address,
        value: bool,
    ): player::PlayerCreated {
        let _ = account;
        player::asset_player_not_exists(player_id);
        player::new_player_created(
            player_id,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        player_created: &player::PlayerCreated,
    ): player::Player {
        let (player_id, value) = player::get_player_created_all_properties(player_created);
        player::create_player(
            player_id,
            value,
        )
    }

}
