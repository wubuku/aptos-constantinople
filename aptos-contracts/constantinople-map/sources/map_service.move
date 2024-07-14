module aptos_constantinople_demo_map::map_service {
    use std::vector;
    use aptos_constantinople_demo_map::encounter_trigger_aggregate;
    use aptos_constantinople_demo_map::map;
    use aptos_constantinople_demo_map::obstruction_aggregate;

    public fun initialize(_store_account: &signer) {
    }

    public entry fun init_map(
        account: &signer,
        store_address: address,
    ) {
        //let map = pass_object::borrow(&map::get_map());
        let width = map::singleton_width(store_address);
        let height = map::singleton_height(store_address);
        let terrain = map::singleton_terrain(store_address);

        let y = 0;
        while (y < height) {
            let x = 0;
            while (x < width) {
                let value = *vector::borrow(vector::borrow(&terrain, y), x);
                if (value == 20) {
                    encounter_trigger_aggregate::create(account, store_address, x, y, true);
                };
                if (value >= 40) {
                    obstruction_aggregate::create(account, store_address, x, y, true);
                };
                x = x + 1;
            };
            y = y + 1;
        };
    }

}
