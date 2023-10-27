module aptos_constantinople_demo::player_create_logic {
    use aptos_constantinople_demo::player;

    friend aptos_constantinople_demo::player_aggregate;

    public(friend) fun verify(
        account: &signer,
        store_address: address,      
        player_id: address,
        value: bool,
    ): player::PlayerCreated {
        let _ = account;
        player::asset_player_not_exists(store_address, player_id);
        player::new_player_created(
            player_id,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store_address: address,
        player_created: &player::PlayerCreated,
    ): player::Player {
        let (player_id, value) = player::get_player_created_all_properties(player_created);
        player::create_player(
            store_address,
            player_id,
            value,
        )
    }

}
