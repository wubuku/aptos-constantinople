module aptos_constantinople_demo::monster_delete_logic {
    use aptos_constantinople_demo::monster;

    friend aptos_constantinople_demo::monster_aggregate;

    public(friend) fun verify(
        account: &signer,
        monster: &monster::Monster,
    ): monster::MonsterDeleted {
        let _ = account;
        monster::new_monster_deleted(
            monster,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        monster_deleted: &monster::MonsterDeleted,
        monster: monster::Monster,
    ): monster::Monster {
        let monster_id = monster::monster_id(&monster);
        let _ = monster_id;
        let _ = monster_deleted;
        monster
    }

}
