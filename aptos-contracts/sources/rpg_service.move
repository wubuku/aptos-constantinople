module aptos_constantinople_demo::rpg_service {
    use std::vector;
    use aptos_constantinople_demo::encounter;
    use aptos_constantinople_demo::encounter_aggregate;
    use aptos_constantinople_demo::encounter_trigger;
    use aptos_constantinople_demo::encounter_trigger_aggregate;
    use aptos_constantinople_demo::encounterable;
    use aptos_constantinople_demo::encounterable_aggregate;
    use aptos_constantinople_demo::genesis_account;
    use aptos_constantinople_demo::map;
    use aptos_constantinople_demo::map_aggregate;
    use aptos_constantinople_demo::monster;
    use aptos_constantinople_demo::monster_aggregate;
    use aptos_constantinople_demo::movable;
    use aptos_constantinople_demo::movable_aggregate;
    use aptos_constantinople_demo::obstruction;
    use aptos_constantinople_demo::obstruction_aggregate;
    use aptos_constantinople_demo::owned_monsters;
    use aptos_constantinople_demo::owned_monsters_aggregate;
    use aptos_constantinople_demo::pass_object;
    use aptos_constantinople_demo::player;
    use aptos_constantinople_demo::player_aggregate;
    use aptos_constantinople_demo::player_position;
    use aptos_constantinople_demo::player_position_aggregate;
    use aptos_constantinople_demo::random_seed;
    use aptos_constantinople_demo::random_seed_aggregate;
    use aptos_framework::account;
    use aptos_framework::event;

    const ENotInitialized: u64 = 110;

    struct Events has key {
        init_map_event_handle: event::EventHandle<InitMapEvent>,
        register_event_handle: event::EventHandle<RegisterEvent>,
        player_move_event_handle: event::EventHandle<PlayerMoveEvent>,
        catch_result_handle: event::EventHandle<CatchResult>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            init_map_event_handle: account::new_event_handle<InitMapEvent>(&res_account),
            register_event_handle: account::new_event_handle<RegisterEvent>(&res_account),
            player_move_event_handle: account::new_event_handle<PlayerMoveEvent>(&res_account),
            catch_result_handle: account::new_event_handle<CatchResult>(&res_account),
        });
    }

    public entry fun init_map(
        account: &signer,
    ) {
        //let map = pass_object::borrow(&map::get_map());
        let width = map::singleton_width();
        let height = map::singleton_height();
        let terrain = map::singleton_terrain();

        let y = 0;
        while (y < height) {
            let x = 0;
            while (x < width) {
                let value = *vector::borrow(vector::borrow(&terrain, y), x);
                if (value == 20) {
                    encounter_trigger_aggregate::create(account, x, y, true);
                };
                if (value >= 40) {
                    obstruction_aggregate::create(account, x, y, true);
                };
                x = x + 1;
            };
            y = y + 1;
        };
    }

    public entry fun register(
        account: &signer,
        x: u64,
        y: u64,
    ) {
        // todo ...
    }

    public entry fun player_move(
        account: &signer,
        x: u64,
        y: u64,
    ) {
        // todo ...
    }

    public entry fun throw_ball(
        account: &signer,
    ) {
        // todo ...
    }

    struct InitMapEvent has store, drop {
    }

    public(friend) fun new_init_map_event(
    ): InitMapEvent {
        InitMapEvent {
        }
    }

    struct RegisterEvent has store, drop {
    }

    public(friend) fun new_register_event(
    ): RegisterEvent {
        RegisterEvent {
        }
    }

    struct PlayerMoveEvent has store, drop {
    }

    public(friend) fun new_player_move_event(
    ): PlayerMoveEvent {
        PlayerMoveEvent {
        }
    }

    struct CatchResult has store, drop {
        value: u8,
        player_id: address,
    }

    public fun catch_result_value(catch_result: &CatchResult): u8 {
        catch_result.value
    }

    public fun catch_result_player_id(catch_result: &CatchResult): address {
        catch_result.player_id
    }

    public(friend) fun new_catch_result(
        value: u8,
        player_id: address,
    ): CatchResult {
        CatchResult {
            value,
            player_id,
        }
    }

    public(friend) fun emit_init_map_event(init_map_event: InitMapEvent) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.init_map_event_handle, init_map_event);
    }

    public(friend) fun emit_register_event(register_event: RegisterEvent) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.register_event_handle, register_event);
    }

    public(friend) fun emit_player_move_event(player_move_event: PlayerMoveEvent) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.player_move_event_handle, player_move_event);
    }

    public(friend) fun emit_catch_result(catch_result: CatchResult) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.catch_result_handle, catch_result);
    }

}
