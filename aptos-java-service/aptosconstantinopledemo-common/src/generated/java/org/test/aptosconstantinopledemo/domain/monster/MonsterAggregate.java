// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.monster;

import java.util.List;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;
import org.test.aptosconstantinopledemo.domain.Command;

public interface MonsterAggregate {
    MonsterState getState();

    List<Event> getChanges();

    void create(BigInteger monsterType, Long offChainVersion, String commandId, String requesterId, MonsterCommands.Create c);

    void delete(Long offChainVersion, String commandId, String requesterId, MonsterCommands.Delete c);

    void throwOnInvalidStateTransition(Command c);
}

