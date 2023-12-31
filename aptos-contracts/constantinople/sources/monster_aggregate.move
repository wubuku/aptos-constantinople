// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::monster_aggregate {
    use aptos_constantinople_demo::monster;
    use aptos_constantinople_demo::monster_create_logic;
    use aptos_constantinople_demo::monster_delete_logic;

    friend aptos_constantinople_demo::rpg_service;

    public(friend) fun create(
        account: &signer,
        store_address: address,
        monster_id: address,
        monster_type: u64,
    ) {
        let monster_created = monster_create_logic::verify(
            account,
            store_address,
            monster_id,
            monster_type,
        );
        let monster = monster_create_logic::mutate(
            account,
            store_address,
            &monster_created,
        );
        monster::add_monster(store_address, monster);
        monster::emit_monster_created(store_address, monster_created);
    }

    public(friend) fun delete(
        account: &signer,
        store_address: address,
        monster_id: address,
    ) {
        let monster = monster::remove_monster(store_address, monster_id);
        let monster_deleted = monster_delete_logic::verify(
            account,
            store_address,
            &monster,
        );
        let updated_monster = monster_delete_logic::mutate(
            account,
            &monster_deleted,
            monster,
        );
        monster::drop_monster(updated_monster);
        monster::emit_monster_deleted(store_address, monster_deleted);
    }

}
