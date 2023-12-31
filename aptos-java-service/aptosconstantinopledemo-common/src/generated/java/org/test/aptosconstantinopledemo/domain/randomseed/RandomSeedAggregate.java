// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.randomseed;

import java.util.List;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;
import org.test.aptosconstantinopledemo.domain.Command;

public interface RandomSeedAggregate {
    RandomSeedState getState();

    List<Event> getChanges();

    void update(BigInteger value, Long offChainVersion, String commandId, String requesterId, RandomSeedCommands.Update c);

    void throwOnInvalidStateTransition(Command c);
}

