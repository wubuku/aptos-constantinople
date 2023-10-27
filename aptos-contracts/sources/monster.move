// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::monster {
    use aptos_framework::account;
    use aptos_framework::event;
    use aptos_std::table::{Self, Table};
    friend aptos_constantinople_demo::monster_create_logic;
    friend aptos_constantinople_demo::monster_delete_logic;
    friend aptos_constantinople_demo::monster_aggregate;

    const EIdAlreadyExists: u64 = 101;
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        monster_created_handle: event::EventHandle<MonsterCreated>,
        monster_deleted_handle: event::EventHandle<MonsterDeleted>,
    }

    struct Tables has key {
        monster_table: Table<address, Monster>,
    }

    public fun initialize(store_account: &signer) {
        move_to(store_account, Events {
            monster_created_handle: account::new_event_handle<MonsterCreated>(store_account),
            monster_deleted_handle: account::new_event_handle<MonsterDeleted>(store_account),
        });

        move_to(
            store_account,
            Tables {
                monster_table: table::new(),
            },
        );

    }

    struct Monster has store {
        monster_id: address,
        version: u64,
        monster_type: u64,
    }

    public fun monster_id(monster: &Monster): address {
        monster.monster_id
    }

    public fun version(monster: &Monster): u64 {
        monster.version
    }

    public(friend) fun set_monster_type(monster: &mut Monster, monster_type: u64) {
        monster.monster_type = monster_type;
    }

    fun new_monster(
        monster_id: address,
        monster_type: u64,
    ): Monster {
        Monster {
            monster_id,
            version: 0,
            monster_type,
        }
    }

    struct MonsterCreated has store, drop {
        monster_id: address,
        monster_type: u64,
    }

    public fun get_monster_created_all_properties(monster_created: &MonsterCreated): (address, u64) {
        (monster_created.monster_id, monster_created.monster_type)
    }

    public(friend) fun new_monster_created(
        monster_id: address,
        monster_type: u64,
    ): MonsterCreated {
        MonsterCreated {
            monster_id,
            monster_type,
        }
    }

    struct MonsterDeleted has store, drop {
        monster_id: address,
        version: u64,
    }

    public fun get_monster_deleted_all_properties(monster_deleted: &MonsterDeleted): address {
        monster_deleted.monster_id
    }

    public(friend) fun new_monster_deleted(
        monster: &Monster,
    ): MonsterDeleted {
        MonsterDeleted {
            monster_id: monster_id(monster),
            version: version(monster),
        }
    }


    public(friend) fun create_monster(
        store: address,
        monster_id: address,
        monster_type: u64,
    ): Monster acquires Tables {
        asset_monster_not_exists(store, monster_id);
        let monster = new_monster(
            monster_id,
            monster_type,
        );
        monster
    }

    public(friend) fun asset_monster_not_exists(
        store: address, monster_id: address,
    ) acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store);
        assert!(!table::contains(&tables.monster_table, monster_id), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(store: address, monster: Monster) acquires Tables {
        monster.version = monster.version + 1;
        //assert!(monster.version != 0, EInappropriateVersion);
        private_add_monster(store, monster);
    }

    public(friend) fun add_monster(store: address, monster: Monster) acquires Tables {
        assert!(monster.version == 0, EInappropriateVersion);
        private_add_monster(store, monster);
    }

    public(friend) fun remove_monster(store: address, monster_id: address): Monster acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store);
        table::remove(&mut tables.monster_table, monster_id)
    }

    fun private_add_monster(store: address, monster: Monster) acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store);
        table::add(&mut tables.monster_table, monster.monster_id, monster);
    }

    public fun get_all_porperties(store: address, monster_id: address): u64 acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global<Tables>(store);
        let monster = table::borrow(&tables.monster_table, monster_id);
        all_porperties(monster)
    }

    public fun all_porperties(monster: &Monster): u64 {
        monster.monster_type
    }

    public(friend) fun drop_monster(monster: Monster) {
        let Monster {
            version: _version,
            monster_id: _monster_id,
            monster_type: _monster_type,
        } = monster;
    }

    public fun contains_monster(store: address, monster_id: address): bool acquires Tables {
        let tables = borrow_global<Tables>(store);
        table::contains(&tables.monster_table, monster_id)
    }

    public(friend) fun emit_monster_created(store: address, monster_created: MonsterCreated) acquires Events {
        assert!(exists<Events>(store), ENotInitialized);
        let events = borrow_global_mut<Events>(store);
        event::emit_event(&mut events.monster_created_handle, monster_created);
    }

    public(friend) fun emit_monster_deleted(store: address, monster_deleted: MonsterDeleted) acquires Events {
        assert!(exists<Events>(store), ENotInitialized);
        let events = borrow_global_mut<Events>(store);
        event::emit_event(&mut events.monster_deleted_handle, monster_deleted);
    }

}
