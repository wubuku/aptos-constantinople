// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::encounter {
    use aptos_constantinople_demo::genesis_account;
    use aptos_constantinople_demo::pass_object;
    use aptos_framework::account;
    use aptos_framework::event;
    use aptos_std::table::{Self, Table};
    friend aptos_constantinople_demo::encounter_create_logic;
    friend aptos_constantinople_demo::encounter_update_logic;
    friend aptos_constantinople_demo::encounter_delete_logic;
    friend aptos_constantinople_demo::encounter_aggregate;

    const EIdAlreadyExists: u64 = 101;
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        encounter_created_handle: event::EventHandle<EncounterCreated>,
        encounter_updated_handle: event::EventHandle<EncounterUpdated>,
        encounter_deleted_handle: event::EventHandle<EncounterDeleted>,
    }

    struct Tables has key {
        encounter_table: Table<address, Encounter>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            encounter_created_handle: account::new_event_handle<EncounterCreated>(&res_account),
            encounter_updated_handle: account::new_event_handle<EncounterUpdated>(&res_account),
            encounter_deleted_handle: account::new_event_handle<EncounterDeleted>(&res_account),
        });

        move_to(
            &res_account,
            Tables {
                encounter_table: table::new(),
            },
        );

    }

    struct Encounter has store {
        player_id: address,
        version: u64,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    }

    public fun player_id(encounter: &Encounter): address {
        encounter.player_id
    }

    public fun version(encounter: &Encounter): u64 {
        encounter.version
    }

    public fun is_existent(encounter: &Encounter): bool {
        encounter.is_existent
    }

    public(friend) fun set_is_existent(encounter: &mut Encounter, is_existent: bool) {
        encounter.is_existent = is_existent;
    }

    public fun monster_id(encounter: &Encounter): address {
        encounter.monster_id
    }

    public(friend) fun set_monster_id(encounter: &mut Encounter, monster_id: address) {
        encounter.monster_id = monster_id;
    }

    public fun catch_attempts(encounter: &Encounter): u64 {
        encounter.catch_attempts
    }

    public(friend) fun set_catch_attempts(encounter: &mut Encounter, catch_attempts: u64) {
        encounter.catch_attempts = catch_attempts;
    }

    fun new_encounter(
        player_id: address,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    ): Encounter {
        Encounter {
            player_id,
            version: 0,
            is_existent,
            monster_id,
            catch_attempts,
        }
    }

    struct EncounterCreated has store, drop {
        player_id: address,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    }

    public fun encounter_created_player_id(encounter_created: &EncounterCreated): address {
        encounter_created.player_id
    }

    public fun encounter_created_is_existent(encounter_created: &EncounterCreated): bool {
        encounter_created.is_existent
    }

    public fun encounter_created_monster_id(encounter_created: &EncounterCreated): address {
        encounter_created.monster_id
    }

    public fun encounter_created_catch_attempts(encounter_created: &EncounterCreated): u64 {
        encounter_created.catch_attempts
    }

    public(friend) fun new_encounter_created(
        player_id: address,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    ): EncounterCreated {
        EncounterCreated {
            player_id,
            is_existent,
            monster_id,
            catch_attempts,
        }
    }

    struct EncounterUpdated has store, drop {
        player_id: address,
        version: u64,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    }

    public fun encounter_updated_player_id(encounter_updated: &EncounterUpdated): address {
        encounter_updated.player_id
    }

    public fun encounter_updated_is_existent(encounter_updated: &EncounterUpdated): bool {
        encounter_updated.is_existent
    }

    public fun encounter_updated_monster_id(encounter_updated: &EncounterUpdated): address {
        encounter_updated.monster_id
    }

    public fun encounter_updated_catch_attempts(encounter_updated: &EncounterUpdated): u64 {
        encounter_updated.catch_attempts
    }

    public(friend) fun new_encounter_updated(
        encounter: &Encounter,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    ): EncounterUpdated {
        EncounterUpdated {
            player_id: player_id(encounter),
            version: version(encounter),
            is_existent,
            monster_id,
            catch_attempts,
        }
    }

    struct EncounterDeleted has store, drop {
        player_id: address,
        version: u64,
    }

    public fun encounter_deleted_player_id(encounter_deleted: &EncounterDeleted): address {
        encounter_deleted.player_id
    }

    public(friend) fun new_encounter_deleted(
        encounter: &Encounter,
    ): EncounterDeleted {
        EncounterDeleted {
            player_id: player_id(encounter),
            version: version(encounter),
        }
    }


    public(friend) fun create_encounter(
        player_id: address,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    ): Encounter acquires Tables {
        asset_encounter_not_exists(player_id);
        let encounter = new_encounter(
            player_id,
            is_existent,
            monster_id,
            catch_attempts,
        );
        encounter
    }

    public(friend) fun asset_encounter_not_exists(
        player_id: address,
    ) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        assert!(!table::contains(&tables.encounter_table, player_id), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(encounter: Encounter) acquires Tables {
        encounter.version = encounter.version + 1;
        //assert!(encounter.version != 0, EInappropriateVersion);
        private_add_encounter(encounter);
    }

    public(friend) fun add_encounter(encounter: Encounter) acquires Tables {
        assert!(encounter.version == 0, EInappropriateVersion);
        private_add_encounter(encounter);
    }

    public(friend) fun remove_encounter(player_id: address): Encounter acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::remove(&mut tables.encounter_table, player_id)
    }

    fun private_add_encounter(encounter: Encounter) acquires Tables {
        assert!(exists<Tables>(genesis_account::resouce_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resouce_account_address());
        table::add(&mut tables.encounter_table, player_id(&encounter), encounter);
    }

    public fun get_encounter(player_id: address): pass_object::PassObject<Encounter> acquires Tables {
        let encounter = remove_encounter(player_id);
        pass_object::new(encounter)
    }

    public fun return_encounter(encounter_pass_obj: pass_object::PassObject<Encounter>) acquires Tables {
        let encounter = pass_object::extract(encounter_pass_obj);
        private_add_encounter(encounter);
    }

    public(friend) fun drop_encounter(encounter: Encounter) {
        let Encounter {
            version: _version,
            player_id: _player_id,
            is_existent: _is_existent,
            monster_id: _monster_id,
            catch_attempts: _catch_attempts,
        } = encounter;
    }

    public(friend) fun emit_encounter_created(encounter_created: EncounterCreated) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.encounter_created_handle, encounter_created);
    }

    public(friend) fun emit_encounter_updated(encounter_updated: EncounterUpdated) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.encounter_updated_handle, encounter_updated);
    }

    public(friend) fun emit_encounter_deleted(encounter_deleted: EncounterDeleted) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.encounter_deleted_handle, encounter_deleted);
    }

}
