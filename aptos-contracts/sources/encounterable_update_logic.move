module aptos_constantinople_demo::encounterable_update_logic {
    use aptos_constantinople_demo::encounterable;
    use aptos_constantinople_demo::encounterable_updated;

    friend aptos_constantinople_demo::encounterable_aggregate;

    public(friend) fun verify(
        account: &signer,
        value: bool,
        encounterable: &encounterable::Encounterable,
    ): encounterable::EncounterableUpdated {
        let _ = account;
        encounterable::new_encounterable_updated(
            encounterable,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        encounterable_updated: &encounterable::EncounterableUpdated,
        encounterable: encounterable::Encounterable,
    ): encounterable::Encounterable {
        let value = encounterable_updated::value(encounterable_updated);
        let player_id = encounterable::player_id(&encounterable);
        let _ = player_id;
        encounterable::set_value(&mut encounterable, value);
        encounterable
    }

}
