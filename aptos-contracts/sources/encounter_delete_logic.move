module aptos_constantinople_demo::encounter_delete_logic {
    use aptos_constantinople_demo::encounter;

    friend aptos_constantinople_demo::encounter_aggregate;

    public(friend) fun verify(
        account: &signer,
        encounter: &encounter::Encounter,
    ): encounter::EncounterDeleted {
        let _ = account;
        encounter::new_encounter_deleted(
            encounter,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        encounter_deleted: &encounter::EncounterDeleted,
        encounter: encounter::Encounter,
    ): encounter::Encounter {
        let player_id = encounter::player_id(&encounter);
        let _ = player_id;
        let _ = encounter_deleted;
        encounter
    }

}
