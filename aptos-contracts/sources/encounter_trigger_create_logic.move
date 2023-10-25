module aptos_constantinople_demo::encounter_trigger_create_logic {
    use aptos_constantinople_demo::encounter_trigger;
    use aptos_constantinople_demo::encounter_trigger_created;
    use aptos_constantinople_demo::position::Position;

    friend aptos_constantinople_demo::encounter_trigger_aggregate;

    public(friend) fun verify(
        account: &signer,
        position: Position,
        value: bool,
    ): encounter_trigger::EncounterTriggerCreated {
        let _ = account;
        encounter_trigger::asset_encounter_trigger_not_exists(position);
        encounter_trigger::new_encounter_trigger_created(
            position,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        encounter_trigger_created: &encounter_trigger::EncounterTriggerCreated,
    ): encounter_trigger::EncounterTrigger {
        let position = encounter_trigger_created::position(encounter_trigger_created);
        let value = encounter_trigger_created::value(encounter_trigger_created);
        encounter_trigger::create_encounter_trigger(
            position,
            value,
        )
    }

}
