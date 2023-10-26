module aptos_constantinople_demo::owned_monsters_add_monster_logic {
    use std::vector;
    use aptos_constantinople_demo::owned_monsters;

    friend aptos_constantinople_demo::owned_monsters_aggregate;

    public(friend) fun verify(
        account: &signer,
        monster_id: address,
        owned_monsters: &owned_monsters::OwnedMonsters,
    ): owned_monsters::MonsterAddedToPlayer {
        let _ = account;
        owned_monsters::new_monster_added_to_player(owned_monsters, monster_id)
    }

    public(friend) fun mutate(
        _account: &signer,
        monster_added_to_player: &owned_monsters::MonsterAddedToPlayer,
        owned_monsters: owned_monsters::OwnedMonsters,
    ): owned_monsters::OwnedMonsters {
        let monster_id = owned_monsters::monster_added_to_player_monster_id(monster_added_to_player);
        let player_id = owned_monsters::player_id(&owned_monsters);
        let monsters = owned_monsters::get_all_porperties(player_id);
        vector::push_back(&mut monsters, monster_id);
        owned_monsters::set_monsters(&mut owned_monsters, monsters);
        owned_monsters
    }

}
