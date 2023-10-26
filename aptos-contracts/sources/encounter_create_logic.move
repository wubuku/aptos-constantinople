module aptos_constantinople_demo::encounter_create_logic {
    use aptos_constantinople_demo::encounter;

    friend aptos_constantinople_demo::encounter_aggregate;

    public(friend) fun verify(
        account: &signer,
        player_id: address,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    ): encounter::EncounterCreated {
        let _ = account;
        encounter::asset_encounter_not_exists(player_id);
        encounter::new_encounter_created(
            player_id,
            is_existent,
            monster_id,
            catch_attempts,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        encounter_created: &encounter::EncounterCreated,
    ): encounter::Encounter {
        let player_id = encounter::encounter_created_player_id(encounter_created);
        let is_existent = encounter::encounter_created_is_existent(encounter_created);
        let monster_id = encounter::encounter_created_monster_id(encounter_created);
        let catch_attempts = encounter::encounter_created_catch_attempts(encounter_created);
        encounter::create_encounter(
            player_id,
            is_existent,
            monster_id,
            catch_attempts,
        )
    }

}
