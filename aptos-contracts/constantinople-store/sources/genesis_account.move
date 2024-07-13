// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo_store::genesis_account {
    use aptos_constantinople_demo_store::resource_account;
    use aptos_framework::account;
    use aptos_framework::event;
    use std::error;
    use std::signer;


    const ENotGenesisAccount: u64 = 100;

    // struct Events has key {
    //     resource_account_created_handle: event::EventHandle<ResourceAccountCreated>,
    // }

    struct ResourceAccountCreated has store, drop {
        address: address,
    }

    public fun initialize(account: &signer) {
        assert_genesis_account(account);

        // move_to(account, Events {
        //     resource_account_created_handle: account::new_event_handle<ResourceAccountCreated>(account),
        // });

        resource_account::initialize(account);

        // let events = borrow_global_mut<Events>(signer::address_of(account));
        // event::emit_event(&mut events.resource_account_created_handle, ResourceAccountCreated {
        //     address: resource_account_address(),
        // });
    }

    public fun assert_genesis_account(account: &signer) {
        assert!(signer::address_of(account) == @aptos_constantinople_demo_store, error::invalid_argument(ENotGenesisAccount));
    }

    public(friend) fun resource_account_signer(): signer {
        resource_account::resource_account_signer(@aptos_constantinople_demo_store)
    }

    public fun resource_account_signer_for_genesis_account(account: &signer): signer {
        assert_genesis_account(account);
        resource_account_signer()
    }

    public fun resource_account_address(): address {
        let res_account = resource_account::resource_account_signer(@aptos_constantinople_demo_store);
        signer::address_of(&res_account)
    }

}

