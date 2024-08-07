// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::owned_monsters_aggregate {
    use aptos_constantinople_demo::owned_monsters;
    use aptos_constantinople_demo::owned_monsters_add_monster_logic;
    use aptos_constantinople_demo::owned_monsters_create_logic;

    friend aptos_constantinople_demo::rpg_service;

    public(friend) fun create(
        account: &signer,
        player_id: address,
        monsters: vector<address>,
    ) {
        let owned_monsters_created = owned_monsters_create_logic::verify(
            account,
            player_id,
            monsters,
        );
        let owned_monsters = owned_monsters_create_logic::mutate(
            account,
            &owned_monsters_created,
        );
        owned_monsters::add_owned_monsters(owned_monsters);
        owned_monsters::emit_owned_monsters_created(owned_monsters_created);
    }

    public(friend) fun add_monster(
        account: &signer,
        player_id: address,
        monster_id: address,
    ) {
        let owned_monsters = owned_monsters::remove_owned_monsters(player_id);
        let monster_added_to_player = owned_monsters_add_monster_logic::verify(
            account,
            monster_id,
            &owned_monsters,
        );
        let updated_owned_monsters = owned_monsters_add_monster_logic::mutate(
            account,
            &monster_added_to_player,
            owned_monsters,
        );
        owned_monsters::update_version_and_add(updated_owned_monsters);
        owned_monsters::emit_monster_added_to_player(monster_added_to_player);
    }

}
