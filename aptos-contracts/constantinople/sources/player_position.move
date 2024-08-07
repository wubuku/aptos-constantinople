// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::player_position {
    use aptos_constantinople_demo::genesis_account;
    use aptos_constantinople_demo_map::position::Position;
    use aptos_framework::account;
    use aptos_framework::event;
    use aptos_std::table::{Self, Table};
    friend aptos_constantinople_demo::player_position_create_logic;
    friend aptos_constantinople_demo::player_position_update_logic;
    friend aptos_constantinople_demo::player_position_aggregate;

    const EIdAlreadyExists: u64 = 101;
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        player_position_created_handle: event::EventHandle<PlayerPositionCreated>,
        player_position_updated_handle: event::EventHandle<PlayerPositionUpdated>,
    }

    struct Tables has key {
        player_position_table: Table<address, PlayerPosition>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            player_position_created_handle: account::new_event_handle<PlayerPositionCreated>(&res_account),
            player_position_updated_handle: account::new_event_handle<PlayerPositionUpdated>(&res_account),
        });

        move_to(
            &res_account,
            Tables {
                player_position_table: table::new(),
            },
        );

    }

    struct PlayerPosition has store {
        player_id: address,
        version: u64,
        position: Position,
    }

    public fun player_id(player_position: &PlayerPosition): address {
        player_position.player_id
    }

    public fun version(player_position: &PlayerPosition): u64 {
        player_position.version
    }

    public(friend) fun set_position(player_position: &mut PlayerPosition, position: Position) {
        player_position.position = position;
    }

    fun new_player_position(
        player_id: address,
        position: Position,
    ): PlayerPosition {
        PlayerPosition {
            player_id,
            version: 0,
            position,
        }
    }

    struct PlayerPositionCreated has store, drop {
        player_id: address,
        position: Position,
    }

    public fun get_player_position_created_all_properties(player_position_created: &PlayerPositionCreated): (address, Position) {
        (player_position_created.player_id, player_position_created.position)
    }

    public(friend) fun new_player_position_created(
        player_id: address,
        position: Position,
    ): PlayerPositionCreated {
        PlayerPositionCreated {
            player_id,
            position,
        }
    }

    struct PlayerPositionUpdated has store, drop {
        player_id: address,
        version: u64,
        position: Position,
    }

    public fun get_player_position_updated_all_properties(player_position_updated: &PlayerPositionUpdated): (address, Position) {
        (player_position_updated.player_id, player_position_updated.position)
    }

    public(friend) fun new_player_position_updated(
        player_position: &PlayerPosition,
        position: Position,
    ): PlayerPositionUpdated {
        PlayerPositionUpdated {
            player_id: player_id(player_position),
            version: version(player_position),
            position,
        }
    }


    public(friend) fun create_player_position(
        player_id: address,
        position: Position,
    ): PlayerPosition acquires Tables {
        asset_player_position_not_exists(player_id);
        let player_position = new_player_position(
            player_id,
            position,
        );
        player_position
    }

    public(friend) fun asset_player_position_not_exists(
        player_id: address,
    ) acquires Tables {
        assert!(exists<Tables>(genesis_account::resource_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resource_account_address());
        assert!(!table::contains(&tables.player_position_table, player_id), EIdAlreadyExists);
    }

    public(friend) fun update_version_and_add(player_position: PlayerPosition) acquires Tables {
        player_position.version = player_position.version + 1;
        //assert!(player_position.version != 0, EInappropriateVersion);
        private_add_player_position(player_position);
    }

    public(friend) fun add_player_position(player_position: PlayerPosition) acquires Tables {
        assert!(player_position.version == 0, EInappropriateVersion);
        private_add_player_position(player_position);
    }

    public(friend) fun remove_player_position(player_id: address): PlayerPosition acquires Tables {
        assert!(exists<Tables>(genesis_account::resource_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resource_account_address());
        table::remove(&mut tables.player_position_table, player_id)
    }

    fun private_add_player_position(player_position: PlayerPosition) acquires Tables {
        assert!(exists<Tables>(genesis_account::resource_account_address()), ENotInitialized);
        let tables = borrow_global_mut<Tables>(genesis_account::resource_account_address());
        table::add(&mut tables.player_position_table, player_position.player_id, player_position);
    }

    public fun get_all_porperties(player_id: address): Position acquires Tables {
        assert!(exists<Tables>(genesis_account::resource_account_address()), ENotInitialized);
        let tables = borrow_global<Tables>(genesis_account::resource_account_address());
        let player_position = table::borrow(&tables.player_position_table, player_id);
        all_porperties(player_position)
    }

    public fun all_porperties(player_position: &PlayerPosition): Position {
        player_position.position
    }

    public(friend) fun drop_player_position(player_position: PlayerPosition) {
        let PlayerPosition {
            version: _version,
            player_id: _player_id,
            position: _position,
        } = player_position;
    }

    public fun contains_player_position(player_id: address): bool acquires Tables {
        let tables = borrow_global<Tables>(genesis_account::resource_account_address());
        table::contains(&tables.player_position_table, player_id)
    }

    public(friend) fun emit_player_position_created(player_position_created: PlayerPositionCreated) acquires Events {
        assert!(exists<Events>(genesis_account::resource_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resource_account_address());
        event::emit_event(&mut events.player_position_created_handle, player_position_created);
    }

    public(friend) fun emit_player_position_updated(player_position_updated: PlayerPositionUpdated) acquires Events {
        assert!(exists<Events>(genesis_account::resource_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resource_account_address());
        event::emit_event(&mut events.player_position_updated_handle, player_position_updated);
    }

}
