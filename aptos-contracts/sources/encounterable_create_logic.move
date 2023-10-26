module aptos_constantinople_demo::encounterable_create_logic {
    use aptos_constantinople_demo::encounterable;

    friend aptos_constantinople_demo::encounterable_aggregate;

    public(friend) fun verify(
        account: &signer,
        player_id: address,
        value: bool,
    ): encounterable::EncounterableCreated {
        let _ = account;
        encounterable::asset_encounterable_not_exists(player_id);
        encounterable::new_encounterable_created(
            player_id,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        encounterable_created: &encounterable::EncounterableCreated,
    ): encounterable::Encounterable {
        let player_id = encounterable::encounterable_created_player_id(encounterable_created);
        let value = encounterable::encounterable_created_value(encounterable_created);
        encounterable::create_encounterable(
            player_id,
            value,
        )
    }

}
