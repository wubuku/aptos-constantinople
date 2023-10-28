// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::random_seed {
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

    public fun initialize(store_account: &signer) {
        move_to(store_account, Events {
            random_seed_updated_handle: account::new_event_handle<RandomSeedUpdated>(store_account),
        });

        let random_seed = new_random_seed();
        add_random_seed(store_account, random_seed);
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
        store_address: address,
        value: u64,
    ): RandomSeedUpdated acquires RandomSeed {
        RandomSeedUpdated {
            version: singleton_version(store_address, ),
            value,
        }
    }


    public(friend) fun update_version_and_add(store_account: &signer, random_seed: RandomSeed) {
        random_seed.version = random_seed.version + 1;
        //assert!(random_seed.version != 0, EInappropriateVersion);
        private_add_random_seed(store_account, random_seed);
    }

    public(friend) fun add_random_seed(store_account: &signer, random_seed: RandomSeed) {
        assert!(random_seed.version == 0, EInappropriateVersion);
        private_add_random_seed(store_account, random_seed);
    }

    public(friend) fun remove_random_seed(store_address: address, ): RandomSeed acquires RandomSeed {
        assert!(exists<RandomSeed>(store_address), ENotInitialized);
        move_from<RandomSeed>(store_address)
    }

    fun private_add_random_seed(store_account: &signer, random_seed: RandomSeed) {
        move_to(store_account, random_seed);
    }

    public fun singleton_version(store_address: address, ): u64 acquires RandomSeed {
        let random_seed = borrow_global<RandomSeed>(store_address);
        random_seed.version
    }

    public fun singleton_value(store_address: address, ): u64 acquires RandomSeed {
        let random_seed = borrow_global<RandomSeed>(store_address);
        random_seed.value
    }

    public fun get_all_porperties(store_address: address, ): u64 acquires RandomSeed {
        assert!(exists<RandomSeed>(store_address), ENotInitialized);
        let random_seed = borrow_global<RandomSeed>(store_address);
        all_porperties(random_seed)
    }

    public fun all_porperties(random_seed: &RandomSeed): u64 {
        random_seed.value
    }

    public(friend) fun set_all_porperties(store_address: address, value: u64) acquires RandomSeed {
        assert!(exists<RandomSeed>(store_address), ENotInitialized);
        let random_seed = borrow_global_mut<RandomSeed>(store_address);
        random_seed.version = random_seed.version + 1;
        random_seed.value = value;
    }

    public(friend) fun drop_random_seed(random_seed: RandomSeed) {
        let RandomSeed {
            version: _version,
            value: _value,
        } = random_seed;
    }

    public fun random_seed_exists(store_address: address, ): bool {
        exists<RandomSeed>(store_address)
    }

    public(friend) fun emit_random_seed_updated(store_address: address, random_seed_updated: RandomSeedUpdated) acquires Events {
        assert!(exists<Events>(store_address), ENotInitialized);
        let events = borrow_global_mut<Events>(store_address);
        event::emit_event(&mut events.random_seed_updated_handle, random_seed_updated);
    }

}
