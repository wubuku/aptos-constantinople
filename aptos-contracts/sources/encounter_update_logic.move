module aptos_constantinople_demo::encounter_update_logic {
    use aptos_constantinople_demo::encounter;

    friend aptos_constantinople_demo::encounter_aggregate;

    public(friend) fun verify(
        account: &signer,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
        encounter: &encounter::Encounter,
    ): encounter::EncounterUpdated {
        let _ = account;
        encounter::new_encounter_updated(
            encounter,
            is_existent,
            monster_id,
            catch_attempts,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        encounter_updated: &encounter::EncounterUpdated,
        encounter: encounter::Encounter,
    ): encounter::Encounter {
        let is_existent = encounter::encounter_updated_is_existent(encounter_updated);
        let monster_id = encounter::encounter_updated_monster_id(encounter_updated);
        let catch_attempts = encounter::encounter_updated_catch_attempts(encounter_updated);
        let player_id = encounter::player_id(&encounter);
        let _ = player_id;
        encounter::set_is_existent(&mut encounter, is_existent);
        encounter::set_monster_id(&mut encounter, monster_id);
        encounter::set_catch_attempts(&mut encounter, catch_attempts);
        encounter
    }

}
