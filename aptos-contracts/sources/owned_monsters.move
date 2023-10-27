// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::owned_monsters {
    use aptos_constantinople_demo::genesis_account;
    use aptos_constantinople_demo::pass_object;
    use aptos_framework::account;
    use aptos_framework::event;
    use aptos_std::table::{Self, Table};
    friend aptos_constantinople_demo::owned_monsters_create_logic;
    friend aptos_constantinople_demo::owned_monsters_add_monster_logic;
    friend aptos_constantinople_demo::owned_monsters_aggregate;

    const EIdAlreadyExists: u64 = 101;
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        owned_monsters_created_handle: event::EventHandle<OwnedMonstersCreated>,
        monster_added_to_player_handle: event::EventHandle<MonsterAddedToPlayer>,
    }

    struct Tables has key {
        owned_monsters_table: Table<address, OwnedMonsters>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            owned_monsters_created_handle: account::new_event_handle<OwnedMonstersCreated>(&res_account),
            monster_added_to_player_handle: account::new_event_handle<MonsterAddedToPlayer>(&res_account),
        });

        move_to(
            &res_account,
            Tables {
                owned_monsters_table: table::new(),
            },
        );

    }

    struct OwnedMonsters has store {
        player_id: address,
        version: u64,
        monsters: vector<address>,
    }

    public fun player_id(owned_monsters: &OwnedMonsters): address {
        owned_monsters.player_id
    }

    public fun version(owned_monsters: &OwnedMonsters): u64 {
        owned_monsters.version
    }

    public(friend) fun set_monsters(owned_monsters: &mut OwnedMonsters, monsters: vector<address>) {
        owned_monsters.monsters = monsters;
    }

    fun new_owned_monsters(
        player_id: address,
        monsters: vector<address>,
    ): OwnedMonsters {
        OwnedMonsters {
            player_id,
            version: 0,
            monsters,
        }
    }

    struct OwnedMonstersCreated has store, drop {
        player_id: address,
        monsters: vector<address>,
    }

    public fun get_owned_monsters_created_all_properties(owned_monsters_created: &OwnedMonstersCreated): (address, vector<address>) {
        (owned_monsters_created.player_id, owned_monsters_created.monsters)
    }

    public(friend) fun new_owned_monsters_created(
        player_id: address,
        monsters: vector<address>,
    ): OwnedMonstersCreated {
        OwnedMonstersCreated {
            player_id,
            monsters,
        }
    }

    struct MonsterAddedToPlayer has store, drop {
        player_id: address,
        version: u64,
        monster_id: address,
    }

    public fun get_monster_added_to_player_all_properties(monster_added_to_player: &MonsterAddedToPlayer): (address, address) {
        (monster_added_to_player.player_id, monster_added_to_player.monster_id)
    }

    public(friend) fun new_monster_added_to_player(
        owned_monsters: &OwnedMonsters,
        monster_id: address,
    ): MonsterAddedToPlayer {
        MonsterAddedToPlayer {
            player_id: player_id(owned_monsters),
            version: version(owned_monsters),
            monster_id,
        }
    }


    public(friend) fun create_owned_monsters(
        player_id: address,
        monsters: vector<address>,
    ): OwnedMonsters acquires Tables {
        asset_owned_monsters_not_exists(player_id);
        let owned_monsters = new_owned_monsters(
            player_id,
            monsters,
        );
        owned_monsters
    }

    public(friend) fun asset_owned_monsters_not_exists(
        player_id: address,
    ) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        assert!(!table::contains(&tables.owned_monsters_table, player_id), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(owned_monsters: OwnedMonsters) acquires Tables {
        owned_monsters.version = owned_monsters.version + 1;
        //assert!(owned_monsters.version != 0, EInappropriateVersion);
        private_add_owned_monsters(owned_monsters);
    }

    public(friend) fun add_owned_monsters(owned_monsters: OwnedMonsters) acquires Tables {
        assert!(owned_monsters.version == 0, EInappropriateVersion);
        private_add_owned_monsters(owned_monsters);
    }

    public(friend) fun remove_owned_monsters(player_id: address): OwnedMonsters acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::remove(&mut tables.owned_monsters_table, player_id)
    }

    fun private_add_owned_monsters(owned_monsters: OwnedMonsters) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::add(&mut tables.owned_monsters_table, player_id(&owned_monsters), owned_monsters);
    }

    public fun get_all_porperties(player_id: address): vector<address> acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global<Tables>(genesis_account::resouce_account_address());
        let owned_monsters = table::borrow(&tables.owned_monsters_table, player_id);
        owned_monsters.monsters
    }

    public(friend) fun drop_owned_monsters(owned_monsters: OwnedMonsters) {
        let OwnedMonsters {
            version: _version,
            player_id: _player_id,
            monsters: _monsters,
        } = owned_monsters;
    }

    public fun contains_owned_monsters(player_id: address): bool acquires Tables {
        let tables = borrow_global<Tables>(genesis_account::resouce_account_address());
        table::contains(&tables.owned_monsters_table,  player_id)
    }

    public(friend) fun emit_owned_monsters_created(owned_monsters_created: OwnedMonstersCreated) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.owned_monsters_created_handle, owned_monsters_created);
    }

    public(friend) fun emit_monster_added_to_player(monster_added_to_player: MonsterAddedToPlayer) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.monster_added_to_player_handle, monster_added_to_player);
    }

}
