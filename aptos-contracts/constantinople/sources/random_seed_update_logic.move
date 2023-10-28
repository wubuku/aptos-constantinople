module aptos_constantinople_demo::random_seed_update_logic {
    use aptos_constantinople_demo::random_seed;

    friend aptos_constantinople_demo::random_seed_aggregate;

    public(friend) fun verify(
        account: &signer,
        store_address: address,      
        value: u64,
    ): random_seed::RandomSeedUpdated {
        let _ = account;
        random_seed::new_random_seed_updated(
            store_address,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store_address: address,
        random_seed_updated: &random_seed::RandomSeedUpdated,
    ) {
        let value = random_seed::get_random_seed_updated_all_properties(random_seed_updated);
        random_seed::set_all_porperties(store_address, value);
    }

}
