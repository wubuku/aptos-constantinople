module aptos_constantinople_demo::monster_create_logic {
    use aptos_constantinople_demo::monster;

    friend aptos_constantinople_demo::monster_aggregate;

    public(friend) fun verify(
        account: &signer,
        store_address: address,      
        monster_id: address,
        monster_type: u64,
    ): monster::MonsterCreated {
        let _ = account;
        monster::asset_monster_not_exists(store_address, monster_id);
        monster::new_monster_created(
            monster_id,
            monster_type,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store_address: address,
        monster_created: &monster::MonsterCreated,
    ): monster::Monster {
        let (monster_id, monster_type) = monster::get_monster_created_all_properties(monster_created);
        monster::create_monster(
            store_address,
            monster_id,
            monster_type,
        )
    }

}
