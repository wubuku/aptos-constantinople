// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.obstruction;

import java.util.*;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.specialization.*;

public abstract class AbstractObstructionAggregate extends AbstractAggregate implements ObstructionAggregate {
    private ObstructionState.MutableObstructionState state;

    private List<Event> changes = new ArrayList<Event>();

    public AbstractObstructionAggregate(ObstructionState state) {
        this.state = (ObstructionState.MutableObstructionState)state;
    }

    public ObstructionState getState() {
        return this.state;
    }

    public List<Event> getChanges() {
        return this.changes;
    }

    public void throwOnInvalidStateTransition(Command c) {
        ObstructionCommand.throwOnInvalidStateTransition(this.state, c);
    }

    protected void apply(Event e) {
        onApplying(e);
        state.mutate(e);
        changes.add(e);
    }


    ////////////////////////

    public static class SimpleObstructionAggregate extends AbstractObstructionAggregate {
        public SimpleObstructionAggregate(ObstructionState state) {
            super(state);
        }

        @Override
        public void create(Boolean value, Long offChainVersion, String commandId, String requesterId, ObstructionCommands.Create c) {
            try {
                verifyCreate(value, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            Event e = newObstructionCreated(value, offChainVersion, commandId, requesterId);
            apply(e);
        }

        protected void verifyCreate(Boolean value, ObstructionCommands.Create c) {
            Boolean Value = value;

            ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.obstruction.CreateLogic",
                    "verify",
                    new Class[]{ObstructionState.class, Boolean.class, VerificationContext.class},
                    new Object[]{getState(), value, VerificationContext.forCommand(c)}
            );

//package org.test.aptosconstantinopledemo.domain.obstruction;
//
//public class CreateLogic {
//    public static void verify(ObstructionState obstructionState, Boolean value, VerificationContext verificationContext) {
//    }
//}

        }
           

        protected AbstractObstructionEvent.ObstructionCreated newObstructionCreated(Boolean value, Long offChainVersion, String commandId, String requesterId) {
            ObstructionEventId eventId = new ObstructionEventId(getState().getPosition(), null);
            AbstractObstructionEvent.ObstructionCreated e = new AbstractObstructionEvent.ObstructionCreated();

            e.setValue(value);
            e.setAptosEventVersion(null); // todo Need to update 'verify' method to return event properties.
            e.setAptosEventSequenceNumber(null); // todo Need to update 'verify' method to return event properties.
            e.setAptosEventType(null); // todo Need to update 'verify' method to return event properties.
            e.setAptosEventGuid(null); // todo Need to update 'verify' method to return event properties.
            e.setStatus(null); // todo Need to update 'verify' method to return event properties.

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setObstructionEventId(eventId);
            return e;
        }

    }

}

