// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::movable {
    use aptos_framework::account;
    use aptos_framework::event;
    use aptos_std::table::{Self, Table};
    friend aptos_constantinople_demo::movable_create_logic;
    friend aptos_constantinople_demo::movable_aggregate;

    const EIdAlreadyExists: u64 = 101;
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        movable_created_handle: event::EventHandle<MovableCreated>,
    }

    struct Tables has key {
        movable_table: Table<address, Movable>,
    }

    public fun initialize(store_account: &signer) {
        move_to(store_account, Events {
            movable_created_handle: account::new_event_handle<MovableCreated>(store_account),
        });

        move_to(
            store_account,
            Tables {
                movable_table: table::new(),
            },
        );

    }

    struct Movable has store {
        player_id: address,
        version: u64,
        value: bool,
    }

    public fun player_id(movable: &Movable): address {
        movable.player_id
    }

    public fun version(movable: &Movable): u64 {
        movable.version
    }

    public(friend) fun set_value(movable: &mut Movable, value: bool) {
        movable.value = value;
    }

    fun new_movable(
        player_id: address,
        value: bool,
    ): Movable {
        Movable {
            player_id,
            version: 0,
            value,
        }
    }

    struct MovableCreated has store, drop {
        player_id: address,
        value: bool,
    }

    public fun get_movable_created_all_properties(movable_created: &MovableCreated): (address, bool) {
        (movable_created.player_id, movable_created.value)
    }

    public(friend) fun new_movable_created(
        player_id: address,
        value: bool,
    ): MovableCreated {
        MovableCreated {
            player_id,
            value,
        }
    }


    public(friend) fun create_movable(
        store_address: address,
        player_id: address,
        value: bool,
    ): Movable acquires Tables {
        asset_movable_not_exists(store_address, player_id);
        let movable = new_movable(
            player_id,
            value,
        );
        movable
    }

    public(friend) fun asset_movable_not_exists(
        store_address: address, player_id: address,
    ) acquires Tables {
        assert!(exists<Tables>(store_address), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store_address);
        assert!(!table::contains(&tables.movable_table, player_id), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(store_address: address, movable: Movable) acquires Tables {
        movable.version = movable.version + 1;
        //assert!(movable.version != 0, EInappropriateVersion);
        private_add_movable(store_address, movable);
    }

    public(friend) fun add_movable(store_address: address, movable: Movable) acquires Tables {
        assert!(movable.version == 0, EInappropriateVersion);
        private_add_movable(store_address, movable);
    }

    public(friend) fun remove_movable(store_address: address, player_id: address): Movable acquires Tables {
        assert!(exists<Tables>(store_address), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store_address);
        table::remove(&mut tables.movable_table, player_id)
    }

    fun private_add_movable(store_address: address, movable: Movable) acquires Tables {
        assert!(exists<Tables>(store_address), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store_address);
        table::add(&mut tables.movable_table, movable.player_id, movable);
    }

    public fun get_all_porperties(store_address: address, player_id: address): bool acquires Tables {
        assert!(exists<Tables>(store_address), ENotInitialized);
        let tables = borrow_global<Tables>(store_address);
        let movable = table::borrow(&tables.movable_table, player_id);
        all_porperties(movable)
    }

    public fun all_porperties(movable: &Movable): bool {
        movable.value
    }

    public(friend) fun drop_movable(movable: Movable) {
        let Movable {
            version: _version,
            player_id: _player_id,
            value: _value,
        } = movable;
    }

    public fun contains_movable(store_address: address, player_id: address): bool acquires Tables {
        let tables = borrow_global<Tables>(store_address);
        table::contains(&tables.movable_table, player_id)
    }

    public(friend) fun emit_movable_created(store_address: address, movable_created: MovableCreated) acquires Events {
        assert!(exists<Events>(store_address), ENotInitialized);
        let events = borrow_global_mut<Events>(store_address);
        event::emit_event(&mut events.movable_created_handle, movable_created);
    }

}