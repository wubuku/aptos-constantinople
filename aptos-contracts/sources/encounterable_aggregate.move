// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::encounterable_aggregate {
    use aptos_constantinople_demo::encounterable;
    use aptos_constantinople_demo::encounterable_create_logic;

    friend aptos_constantinople_demo::rpg_service;

    public(friend) fun create(
        account: &signer,
        store: address,
        player_id: address,
        value: bool,
    ) {
        let encounterable_created = encounterable_create_logic::verify(
            account,
            store,
            player_id,
            value,
        );
        let encounterable = encounterable_create_logic::mutate(
            account,
            store,
            &encounterable_created,
        );
        encounterable::add_encounterable(store, encounterable);
        encounterable::emit_encounterable_created(store, encounterable_created);
    }

}
