module aptos_constantinople_demo::rpg_service {
    use std::bcs;
    use std::signer;
    use std::vector;
    use aptos_std::aptos_hash::keccak256;
    use aptos_framework::util;
    use aptos_constantinople_demo::encounter;
    use aptos_constantinople_demo::encounter_aggregate;
    use aptos_constantinople_demo::encounter_trigger;
    use aptos_constantinople_demo::encounter_trigger_aggregate;
    use aptos_constantinople_demo::encounterable;
    use aptos_constantinople_demo::encounterable_aggregate;
    use aptos_constantinople_demo::genesis_account;
    use aptos_constantinople_demo::map;
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
    use aptos_constantinople_demo::position;

    const ENotInitialized: u64 = 110;

    const Caught: u8 = 0;
    const Fled: u8 = 1;
    const Missed: u8 = 2;

    /// error already register
    const EAlreadyRegister: u64 = 0;
    /// error constrain position to map size
    const EExceedingMapLimits: u64 = 1;
    /// error this space is obstructed
    const EObstaclesExist: u64 = 2;
    ///  error cannot move
    const ECannotMove: u64 = 3;
    /// error cannot move during an encounter
    const ECannotMoveInEncounter: u64 = 4;
    /// error not in encounter
    const ENotInEcounter: u64 = 5;
    /// error can only move to adjacent spaces
    const EOnlyMoveToAdjacentSpaces: u64 = 6;

    struct Events has key {
        catch_result_handle: event::EventHandle<CatchResult>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
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
        let player = signer::address_of(account);

        // error constrain position to map size
        let width = map::singleton_width();
        let height = map::singleton_height();
        assert!(x >= 0 && x <= width, EExceedingMapLimits);
        assert!(y >= 0 && y <= height, EExceedingMapLimits);

        // error already register
        assert!(!player::contains_player(player), EAlreadyRegister);

        let position = position::new(x, y);
        // error this space is obstructed
        assert!(!obstruction::contains_obstruction(position), EObstaclesExist);

        player_aggregate::create(account, player, true);
        player_position_aggregate::create(account, player, x, y);
        movable_aggregate::create(account, player, true);
        encounterable_aggregate::create(account, player, true);
    }

    public entry fun player_move(
        account: &signer,
        x: u64,
        y: u64,
    ) {
        let player = signer::address_of(account);

        // error constrain position to map size
        let width = map::singleton_width();
        let height = map::singleton_height();
        assert!(x >= 0 && x <= width, EExceedingMapLimits);
        assert!(y >= 0 && y <= height, EExceedingMapLimits);

        // error cannot move
        let movable = movable::get_movable(player);
        assert!(movable::value(pass_object::borrow(&movable)), ECannotMove);
        movable::return_movable(movable);

        // error cannot move during an encounter
        assert!(!encounter::contains_encounter(player), ECannotMoveInEncounter);

        let player_position = player_position::get_player_position(player);
        let position = player_position::position(pass_object::borrow(&player_position));
        let from_x = position::x(&position);
        let from_y = position::y(&position);
        player_position::return_player_position(player_position);

        // error can only move to adjacent spaces
        assert!(distance(from_x, from_y, x, y) == 1, EOnlyMoveToAdjacentSpaces);

        let position = position::new(x, y);
        // error this space is obstructed
        assert!(!obstruction::contains_obstruction(position), EObstaclesExist);

        player_position_aggregate::update(account, player, x, y);

        if (encounterable::contains_encounterable(player) && encounter_trigger::contains_encounter_trigger(position)) {
            let (random, monster) = random(account, player, position);
            if (random % 3 == 0) {
                start_encounter(account, player, monster);
            };
        };
    }

    fun start_encounter(account: &signer, player: address, monster: address) {
        let monster_type = bytes_to_u64(bcs::to_bytes(&monster)) % 3;
        monster_aggregate::create(account, monster, monster_type);
        encounter_aggregate::create(account, player, true, monster, 0);
    }

    fun random<T: drop>(account: &signer, player: address, value: T): (u64, address) {
        let random_seed = random_seed::singleton_value();
        random_seed_aggregate::update(account, random_seed + 1);
        let v = vector::empty<u8>();
        vector::append(&mut v, bcs::to_bytes(&player));
        vector::append(&mut v, bcs::to_bytes(&value));
        vector::append(&mut v, bcs::to_bytes(&random_seed));
        let hash = keccak256(v);
        (
            bytes_to_u64(hash),
            util::address_from_bytes(hash)
        )
    }

    fun bytes_to_u64(bytes: vector<u8>): u64 {
        let value = 0u64;
        let i = 0u64;
        while (i < 8) {
            value = value | ((*vector::borrow(&bytes, i) as u64) << ((8 * (7 - i)) as u8));
            i = i + 1;
        };
        return value
    }

    fun distance(from_x: u64, from_y: u64, to_x: u64, to_y: u64): u64 {
        let delta_x = if (from_x > to_x) { from_x - to_x } else { to_x - from_x };
        let delta_y = if (from_y > to_y) { from_y - to_y } else { to_y - from_y };
        delta_x + delta_y
    }

    public entry fun throw_ball(
        account: &signer,
    ) acquires Events {
        let player = signer::address_of(account);
        // error not in encounter
        assert!(encounter::contains_encounter(player), ENotInEcounter);

        let encounter = encounter::get_encounter(player);
        let monster = encounter::monster_id(pass_object::borrow(&encounter));
        let catch_attempts = encounter::catch_attempts(pass_object::borrow(&encounter));
        encounter::return_encounter(encounter);

        let (random, _) = random(account, player, monster);
        if (random % 2 == 0) {
            // 50% chance to catch monster
            // MonsterCatchAttempt.emitEphemeral(player, MonsterCatchResult.Caught);
            if (owned_monsters::contains_owned_monsters(player)) {
                // let owned_monsters = owned_monsters::get_owned_monsters(player);
                // let monsters = owned_monsters::monsters(pass_object::borrow(&owned_monsters));
                //vector::push_back(&mut monsters, monster);
                owned_monsters_aggregate::add_monster(account, player, monster);
            } else {
                owned_monsters_aggregate::create(account, player, vector[monster]);
            };
            encounter_aggregate::delete(account, player);
            emit_catch_result(new_catch_result(Caught, player));
        } else if (catch_attempts >= 2) {
            // Missed 2 times, monster escapes
            monster_aggregate::delete(account, monster);
            encounter_aggregate::delete(account, player);
            emit_catch_result(new_catch_result(Fled, player));
        } else {
            // Throw missed!
            encounter_aggregate::update(account, player, true, monster, catch_attempts + 1);
            emit_catch_result(new_catch_result(Missed, player));
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

    public(friend) fun emit_catch_result(catch_result: CatchResult) acquires Events {
        assert!(exists<Events>(genesis_account::resouce_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resouce_account_address());
        event::emit_event(&mut events.catch_result_handle, catch_result);
    }
}
