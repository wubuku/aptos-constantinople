// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::monster {
    use aptos_constantinople_demo::genesis_account;
    use aptos_constantinople_demo::pass_object;
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

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            monster_created_handle: account::new_event_handle<MonsterCreated>(&res_account),
            monster_deleted_handle: account::new_event_handle<MonsterDeleted>(&res_account),
        });

        move_to(
            &res_account,
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

    public fun monster_type(monster: &Monster): u64 {
        monster.monster_type
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

    public fun monster_created_monster_id(monster_created: &MonsterCreated): address {
        monster_created.monster_id
    }

    public fun monster_created_monster_type(monster_created: &MonsterCreated): u64 {
        monster_created.monster_type
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

    public fun monster_deleted_monster_id(monster_deleted: &MonsterDeleted): address {
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
        monster_id: address,
        monster_type: u64,
    ): Monster acquires Tables {
        asset_monster_not_exists(monster_id);
        let monster = new_monster(
            monster_id,
            monster_type,
        );
        monster
    }

    public(friend) fun asset_monster_not_exists(
        monster_id: address,
    ) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        assert!(!table::contains(&tables.monster_table, monster_id), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(monster: Monster) acquires Tables {
        monster.version = monster.version + 1;
        //assert!(monster.version != 0, EInappropriateVersion);
        private_add_monster(monster);
    }

    public(friend) fun add_monster(monster: Monster) acquires Tables {
        assert!(monster.version == 0, EInappropriateVersion);
        private_add_monster(monster);
    }

    public(friend) fun remove_monster(monster_id: address): Monster acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::remove(&mut tables.monster_table, monster_id)
    }

    fun private_add_monster(monster: Monster) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::add(&mut tables.monster_table, monster_id(&monster), monster);
    }

    public fun get_monster(monster_id: address): pass_object::PassObject<Monster> acquires Tables {
        let monster = remove_monster(monster_id);
        pass_object::new(monster)
    }

    public fun return_monster(monster_pass_obj: pass_object::PassObject<Monster>) acquires Tables {
        let monster = pass_object::extract(monster_pass_obj);
        private_add_monster(monster);
    }

    public(friend) fun drop_monster(monster: Monster) {
        let Monster {
            version: _version,
            monster_id: _monster_id,
            monster_type: _monster_type,
        } = monster;
    }

    public(friend) fun emit_monster_created(monster_created: MonsterCreated) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.monster_created_handle, monster_created);
    }

    public(friend) fun emit_monster_deleted(monster_deleted: MonsterDeleted) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.monster_deleted_handle, monster_deleted);
    }

}
