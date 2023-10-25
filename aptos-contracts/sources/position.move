// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module aptos_constantinople_demo::position {
    const EDataTooLong: u64 = 102;

    struct Position has store, drop, copy {
        x: u64,
        y: u64,
    }

    public fun new(
        x: u64,
        y: u64,
    ): Position {
        let position = Position {
            x,
            y,
        };
        validate(&position);
        position
    }

    fun validate(position: &Position) {
        let _ = position;
    }

    public fun x(position: &Position): u64 {
        position.x
    }

    public fun y(position: &Position): u64 {
        position.y
    }

}
