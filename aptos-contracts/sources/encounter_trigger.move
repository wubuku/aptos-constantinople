// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::encounter_trigger {
    use aptos_constantinople_demo::position::Position;
    use aptos_framework::account;
    use aptos_framework::event;
    use aptos_std::table::{Self, Table};
    friend aptos_constantinople_demo::encounter_trigger_create_logic;
    friend aptos_constantinople_demo::encounter_trigger_aggregate;

    const EIdAlreadyExists: u64 = 101;
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        encounter_trigger_created_handle: event::EventHandle<EncounterTriggerCreated>,
    }

    struct Tables has key {
        encounter_trigger_table: Table<Position, EncounterTrigger>,
    }

    public fun initialize(store_account: &signer) {
        move_to(store_account, Events {
            encounter_trigger_created_handle: account::new_event_handle<EncounterTriggerCreated>(store_account),
        });

        move_to(
            store_account,
            Tables {
                encounter_trigger_table: table::new(),
            },
        );

    }

    struct EncounterTrigger has store {
        position: Position,
        version: u64,
        value: bool,
    }

    public fun position(encounter_trigger: &EncounterTrigger): Position {
        encounter_trigger.position
    }

    public fun version(encounter_trigger: &EncounterTrigger): u64 {
        encounter_trigger.version
    }

    public(friend) fun set_value(encounter_trigger: &mut EncounterTrigger, value: bool) {
        encounter_trigger.value = value;
    }

    fun new_encounter_trigger(
        position: Position,
        value: bool,
    ): EncounterTrigger {
        EncounterTrigger {
            position,
            version: 0,
            value,
        }
    }

    struct EncounterTriggerCreated has store, drop {
        position: Position,
        value: bool,
    }

    public fun get_encounter_trigger_created_all_properties(encounter_trigger_created: &EncounterTriggerCreated): (Position, bool) {
        (encounter_trigger_created.position, encounter_trigger_created.value)
    }

    public(friend) fun new_encounter_trigger_created(
        position: Position,
        value: bool,
    ): EncounterTriggerCreated {
        EncounterTriggerCreated {
            position,
            value,
        }
    }


    public(friend) fun create_encounter_trigger(
        store: address,
        position: Position,
        value: bool,
    ): EncounterTrigger acquires Tables {
        asset_encounter_trigger_not_exists(store, position);
        let encounter_trigger = new_encounter_trigger(
            position,
            value,
        );
        encounter_trigger
    }

    public(friend) fun asset_encounter_trigger_not_exists(
        store: address, position: Position,
    ) acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store);
        assert!(!table::contains(&tables.encounter_trigger_table, position), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(store: address, encounter_trigger: EncounterTrigger) acquires Tables {
        encounter_trigger.version = encounter_trigger.version + 1;
        //assert!(encounter_trigger.version != 0, EInappropriateVersion);
        private_add_encounter_trigger(store, encounter_trigger);
    }

    public(friend) fun add_encounter_trigger(store: address, encounter_trigger: EncounterTrigger) acquires Tables {
        assert!(encounter_trigger.version == 0, EInappropriateVersion);
        private_add_encounter_trigger(store, encounter_trigger);
    }

    public(friend) fun remove_encounter_trigger(store: address, position: Position): EncounterTrigger acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store);
        table::remove(&mut tables.encounter_trigger_table, position)
    }

    fun private_add_encounter_trigger(store: address, encounter_trigger: EncounterTrigger) acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global_mut<Tables>(store);
        table::add(&mut tables.encounter_trigger_table, encounter_trigger.position, encounter_trigger);
    }

    public fun get_all_porperties(store: address, position: Position): bool acquires Tables {
        assert!(exists<Tables>(store), ENotInitialized);
        let tables = borrow_global<Tables>(store);
        let encounter_trigger = table::borrow(&tables.encounter_trigger_table, position);
        all_porperties(encounter_trigger)
    }

    public fun all_porperties(encounter_trigger: &EncounterTrigger): bool {
        encounter_trigger.value
    }

    public(friend) fun drop_encounter_trigger(encounter_trigger: EncounterTrigger) {
        let EncounterTrigger {
            version: _version,
            position: _position,
            value: _value,
        } = encounter_trigger;
    }

    public fun contains_encounter_trigger(store: address, position: Position): bool acquires Tables {
        let tables = borrow_global<Tables>(store);
        table::contains(&tables.encounter_trigger_table, position)
    }

    public(friend) fun emit_encounter_trigger_created(store: address, encounter_trigger_created: EncounterTriggerCreated) acquires Events {
        assert!(exists<Events>(store), ENotInitialized);
        let events = borrow_global_mut<Events>(store);
        event::emit_event(&mut events.encounter_trigger_created_handle, encounter_trigger_created);
    }

}
