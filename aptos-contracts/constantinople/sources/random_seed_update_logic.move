module aptos_constantinople_demo::random_seed_update_logic {
    use aptos_constantinople_demo::random_seed;

    friend aptos_constantinople_demo::random_seed_aggregate;

    public(friend) fun verify(
        account: &signer,
        value: u64,
        random_seed: &random_seed::RandomSeed,
    ): random_seed::RandomSeedUpdated {
        let _ = account;
        random_seed::new_random_seed_updated(
            random_seed,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        random_seed_updated: &random_seed::RandomSeedUpdated,
        random_seed: random_seed::RandomSeed,
    ): random_seed::RandomSeed {
        let value = random_seed::get_random_seed_updated_all_properties(random_seed_updated);
        random_seed::set_value(&mut random_seed, value);
        random_seed
    }

}
