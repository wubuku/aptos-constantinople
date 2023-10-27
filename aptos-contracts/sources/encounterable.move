// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::encounterable {
    use aptos_constantinople_demo::genesis_account;
    use aptos_framework::account;
    use aptos_framework::event;
    use aptos_std::table::{Self, Table};
    friend aptos_constantinople_demo::encounterable_create_logic;
    friend aptos_constantinople_demo::encounterable_aggregate;

    const EIdAlreadyExists: u64 = 101;
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        encounterable_created_handle: event::EventHandle<EncounterableCreated>,
    }

    struct Tables has key {
        encounterable_table: Table<address, Encounterable>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            encounterable_created_handle: account::new_event_handle<EncounterableCreated>(&res_account),
        });

        move_to(
            &res_account,
            Tables {
                encounterable_table: table::new(),
            },
        );

    }

    struct Encounterable has store {
        player_id: address,
        version: u64,
        value: bool,
    }

    public fun player_id(encounterable: &Encounterable): address {
        encounterable.player_id
    }

    public fun version(encounterable: &Encounterable): u64 {
        encounterable.version
    }

    public(friend) fun set_value(encounterable: &mut Encounterable, value: bool) {
        encounterable.value = value;
    }

    fun new_encounterable(
        player_id: address,
        value: bool,
    ): Encounterable {
        Encounterable {
            player_id,
            version: 0,
            value,
        }
    }

    struct EncounterableCreated has store, drop {
        player_id: address,
        value: bool,
    }

    public fun get_encounterable_created_all_properties(encounterable_created: &EncounterableCreated): (address, bool) {
        (encounterable_created.player_id, encounterable_created.value)
    }

    public(friend) fun new_encounterable_created(
        player_id: address,
        value: bool,
    ): EncounterableCreated {
        EncounterableCreated {
            player_id,
            value,
        }
    }


    public(friend) fun create_encounterable(
        player_id: address,
        value: bool,
    ): Encounterable acquires Tables {
        asset_encounterable_not_exists(player_id);
        let encounterable = new_encounterable(
            player_id,
            value,
        );
        encounterable
    }

    public(friend) fun asset_encounterable_not_exists(
        player_id: address,
    ) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        assert!(!table::contains(&tables.encounterable_table, player_id), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(encounterable: Encounterable) acquires Tables {
        encounterable.version = encounterable.version + 1;
        //assert!(encounterable.version != 0, EInappropriateVersion);
        private_add_encounterable(encounterable);
    }

    public(friend) fun add_encounterable(encounterable: Encounterable) acquires Tables {
        assert!(encounterable.version == 0, EInappropriateVersion);
        private_add_encounterable(encounterable);
    }

    public(friend) fun remove_encounterable(player_id: address): Encounterable acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::remove(&mut tables.encounterable_table, player_id)
    }

    fun private_add_encounterable(encounterable: Encounterable) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::add(&mut tables.encounterable_table, encounterable.player_id, encounterable);
    }

    public fun get_all_porperties(player_id: address): bool acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global<Tables>(genesis_account::resouce_account_address());
        let encounterable = table::borrow(&tables.encounterable_table, player_id);
        encounterable.value
    }

    public(friend) fun drop_encounterable(encounterable: Encounterable) {
        let Encounterable {
            version: _version,
            player_id: _player_id,
            value: _value,
        } = encounterable;
    }

    public fun contains_encounterable(player_id: address): bool acquires Tables {
        let tables = borrow_global<Tables>(genesis_account::resouce_account_address());
        table::contains(&tables.encounterable_table, player_id)
    }

    public(friend) fun emit_encounterable_created(encounterable_created: EncounterableCreated) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.encounterable_created_handle, encounterable_created);
    }

}
