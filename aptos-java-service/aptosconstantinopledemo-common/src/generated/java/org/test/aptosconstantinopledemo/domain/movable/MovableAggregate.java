// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.movable;

import java.util.List;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;
import org.test.aptosconstantinopledemo.domain.Command;

public interface MovableAggregate {
    MovableState getState();

    List<Event> getChanges();

    void create(Boolean value, Long offChainVersion, String commandId, String requesterId, MovableCommands.Create c);

    void throwOnInvalidStateTransition(Command c);
}

