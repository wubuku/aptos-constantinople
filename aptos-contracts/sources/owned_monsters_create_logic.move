module aptos_constantinople_demo::owned_monsters_create_logic {
    use aptos_constantinople_demo::owned_monsters;

    friend aptos_constantinople_demo::owned_monsters_aggregate;

    public(friend) fun verify(
        account: &signer,
        player_id: address,
        monsters: vector<address>,
    ): owned_monsters::OwnedMonstersCreated {
        let _ = account;
        owned_monsters::asset_owned_monsters_not_exists(player_id);
        owned_monsters::new_owned_monsters_created(
            player_id,
            monsters,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        owned_monsters_created: &owned_monsters::OwnedMonstersCreated,
    ): owned_monsters::OwnedMonsters {
        let player_id = owned_monsters::owned_monsters_created_player_id(owned_monsters_created);
        let monsters = owned_monsters::owned_monsters_created_monsters(owned_monsters_created);
        owned_monsters::create_owned_monsters(
            player_id,
            monsters,
        )
    }

}
