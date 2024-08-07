// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::random_seed {
    use aptos_constantinople_demo::genesis_account;
    use aptos_framework::account;
    use aptos_framework::event;
    friend aptos_constantinople_demo::random_seed_update_logic;
    friend aptos_constantinople_demo::random_seed_aggregate;

    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const ENotInitialized: u64 = 110;

    struct Events has key {
        random_seed_updated_handle: event::EventHandle<RandomSeedUpdated>,
    }

    public fun initialize(account: &signer) {
        genesis_account::assert_genesis_account(account);

        let res_account = genesis_account::resource_account_signer();
        move_to(&res_account, Events {
            random_seed_updated_handle: account::new_event_handle<RandomSeedUpdated>(&res_account),
        });

        let random_seed = new_random_seed();
        add_random_seed(random_seed);
    }

    struct RandomSeed has key, store {
        version: u64,
        value: u64,
    }

    public fun version(random_seed: &RandomSeed): u64 {
        random_seed.version
    }

    public(friend) fun set_value(random_seed: &mut RandomSeed, value: u64) {
        random_seed.value = value;
    }

    public(friend) fun new_random_seed(
    ): RandomSeed {
        RandomSeed {
            version: 0,
            value: 0,
        }
    }

    struct RandomSeedInitialized has store, drop {
    }

    public(friend) fun new_random_seed_initialized(
        random_seed: &RandomSeed,
    ): RandomSeedInitialized {
        RandomSeedInitialized {
        }
    }

    struct RandomSeedUpdated has store, drop {
        version: u64,
        value: u64,
    }

    public fun get_random_seed_updated_all_properties(random_seed_updated: &RandomSeedUpdated): u64 {
        random_seed_updated.value
    }

    public(friend) fun new_random_seed_updated(
        random_seed: &RandomSeed,
        value: u64,
    ): RandomSeedUpdated {
        RandomSeedUpdated {
            version: version(random_seed),
            value,
        }
    }


    public(friend) fun update_version_and_add(random_seed: RandomSeed) {
        random_seed.version = random_seed.version + 1;
        //assert!(random_seed.version != 0, EInappropriateVersion);
        private_add_random_seed(random_seed);
    }

    public(friend) fun add_random_seed(random_seed: RandomSeed) {
        assert!(random_seed.version == 0, EInappropriateVersion);
        private_add_random_seed(random_seed);
    }

    public(friend) fun remove_random_seed(): RandomSeed acquires RandomSeed {
        assert!(exists<RandomSeed>(genesis_account::resource_account_address()), ENotInitialized);
        move_from<RandomSeed>(genesis_account::resource_account_address())
    }

    fun private_add_random_seed(random_seed: RandomSeed) {
        move_to(&genesis_account::resource_account_signer(), random_seed);
    }

    public fun singleton_value(): u64 acquires RandomSeed {
        let random_seed = borrow_global<RandomSeed>(genesis_account::resource_account_address());
        random_seed.value
    }

    public fun get_all_porperties(): u64 acquires RandomSeed {
        assert!(exists<RandomSeed>(genesis_account::resource_account_address()), ENotInitialized);
        let random_seed = borrow_global<RandomSeed>(genesis_account::resource_account_address());
        all_porperties(random_seed)
    }

    public fun all_porperties(random_seed: &RandomSeed): u64 {
        random_seed.value
    }

    public(friend) fun drop_random_seed(random_seed: RandomSeed) {
        let RandomSeed {
            version: _version,
            value: _value,
        } = random_seed;
    }

    public fun random_seed_exists(): bool {
        exists<RandomSeed>(genesis_account::resource_account_address())
    }

    public(friend) fun emit_random_seed_updated(random_seed_updated: RandomSeedUpdated) acquires Events {
        assert!(exists<Events>(genesis_account::resource_account_address()), ENotInitialized);
        let events = borrow_global_mut<Events>(genesis_account::resource_account_address());
        event::emit_event(&mut events.random_seed_updated_handle, random_seed_updated);
    }

}
