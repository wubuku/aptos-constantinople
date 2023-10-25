// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::player_position_aggregate {
    use aptos_constantinople_demo::player_position;
    use aptos_constantinople_demo::player_position_create_logic;
    use aptos_constantinople_demo::player_position_update_logic;
    use aptos_constantinople_demo::position::{Self, Position};

    public(friend) fun create(
        account: &signer,
        player_id: address,
        position_x: u64,
        position_y: u64,
    ) {
        let position: Position = position::new(
            position_x,
            position_y,
        );
        let player_position_created = player_position_create_logic::verify(
            account,
            player_id,
            position,
        );
        let player_position = player_position_create_logic::mutate(
            account,
            &player_position_created,
        );
        player_position::add_player_position(player_position);
        player_position::emit_player_position_created(player_position_created);
    }

    public(friend) fun update(
        account: &signer,
        player_id: address,
        position_x: u64,
        position_y: u64,
    ) {
        let position: Position = position::new(
            position_x,
            position_y,
        );
        let player_position = player_position::remove_player_position(player_id);
        let player_position_updated = player_position_update_logic::verify(
            account,
            position,
            &player_position,
        );
        let updated_player_position = player_position_update_logic::mutate(
            account,
            &player_position_updated,
            player_position,
        );
        player_position::update_version_and_add(updated_player_position);
        player_position::emit_player_position_updated(player_position_updated);
    }

}
