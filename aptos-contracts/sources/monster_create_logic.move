module aptos_constantinople_demo::monster_create_logic {
    use aptos_constantinople_demo::monster;
    use aptos_constantinople_demo::monster_created;

    friend aptos_constantinople_demo::monster_aggregate;

    public(friend) fun verify(
        account: &signer,
        monster_id: address,
        monster_type: u64,
    ): monster::MonsterCreated {
        let _ = account;
        monster::asset_monster_not_exists(monster_id);
        monster::new_monster_created(
            monster_id,
            monster_type,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        monster_created: &monster::MonsterCreated,
    ): monster::Monster {
        let monster_id = monster_created::monster_id(monster_created);
        let monster_type = monster_created::monster_type(monster_created);
        monster::create_monster(
            monster_id,
            monster_type,
        )
    }

}
