module aptos_constantinople_demo::encounter_create_logic {
    use aptos_constantinople_demo::encounter;

    friend aptos_constantinople_demo::encounter_aggregate;

    public(friend) fun verify(
        account: &signer,
        store_address: address,      
        player_id: address,
        is_existent: bool,
        monster_id: address,
        catch_attempts: u64,
    ): encounter::EncounterCreated {
        let _ = account;
        encounter::asset_encounter_not_exists(store_address, player_id);
        encounter::new_encounter_created(
            player_id,
            is_existent,
            monster_id,
            catch_attempts,
        )
    }

    public(friend) fun mutate(
        _account: &signer,
        store_address: address,
        encounter_created: &encounter::EncounterCreated,
    ): encounter::Encounter {
        let (player_id, is_existent, monster_id, catch_attempts) = encounter::get_encounter_created_all_properties(encounter_created);
        encounter::create_encounter(
            store_address,
            player_id,
            is_existent,
            monster_id,
            catch_attempts,
        )
    }

}
