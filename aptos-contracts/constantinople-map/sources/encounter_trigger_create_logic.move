module aptos_constantinople_demo_map::encounter_trigger_create_logic {
    use aptos_constantinople_demo_map::encounter_trigger;
    use aptos_constantinople_demo_map::position::Position;

    friend aptos_constantinople_demo_map::encounter_trigger_aggregate;

    public(friend) fun verify(
        account: &signer,
        store_address: address,      
        position: Position,
        value: bool,
    ): encounter_trigger::EncounterTriggerCreated {
        let _ = account;
        encounter_trigger::asset_encounter_trigger_not_exists(store_address, position);
        encounter_trigger::new_encounter_trigger_created(
            position,
            value,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store_address: address,
        encounter_trigger_created: &encounter_trigger::EncounterTriggerCreated,
    ): encounter_trigger::EncounterTrigger {
        let (position, value) = encounter_trigger::get_encounter_trigger_created_all_properties(encounter_trigger_created);
        encounter_trigger::create_encounter_trigger(
            store_address,
            position,
            value,
        )
    }

}
