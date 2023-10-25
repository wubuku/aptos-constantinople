// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::encounterable_aggregate {
    use aptos_constantinople_demo::encounterable;
    use aptos_constantinople_demo::encounterable_create_logic;
    use aptos_constantinople_demo::encounterable_update_logic;

    public(friend) fun create(
        account: &signer,
        player_id: address,
        value: bool,
    ) {
        let encounterable_created = encounterable_create_logic::verify(
            account,
            player_id,
            value,
        );
        let encounterable = encounterable_create_logic::mutate(
            account,
            &encounterable_created,
        );
        encounterable::add_encounterable(encounterable);
        encounterable::emit_encounterable_created(encounterable_created);
    }

    public(friend) fun update(
        account: &signer,
        player_id: address,
        value: bool,
    ) {
        let encounterable = encounterable::remove_encounterable(player_id);
        let encounterable_updated = encounterable_update_logic::verify(
            account,
            value,
            &encounterable,
        );
        let updated_encounterable = encounterable_update_logic::mutate(
            account,
            &encounterable_updated,
            encounterable,
        );
        encounterable::update_version_and_add(updated_encounterable);
        encounterable::emit_encounterable_updated(encounterable_updated);
    }

}
