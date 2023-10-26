module aptos_constantinople_demo::rpg_service {
    use std::vector;
    use aptos_constantinople_demo::pass_object;
    use aptos_constantinople_demo::map::{ Self, Map };
    use aptos_constantinople_demo::player_aggregate;
    use aptos_constantinople_demo::monster_aggregate;
    use aptos_constantinople_demo::map_aggregate;
    use aptos_constantinople_demo::random_seed_aggregate;
    use aptos_constantinople_demo::encounter_trigger_aggregate;
    use aptos_constantinople_demo::obstruction_aggregate;


    struct CatchResult has store, drop {
        value: u8,
        player_id: address,
    }

    public(friend) fun new_movable_updated(
        value: u8,
        player_id: address,
    ): CatchResult {
        CatchResult {
            value,
            player_id,
        }
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
        //...
    }

    public entry fun player_move(
        account: &signer,
        x: u64,
        y: u64,
    ) {
        //...
    }

    public entry fun throw_ball(
        account: &signer,
    ) {
        //...
    }
}
