// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encountertrigger;

import java.util.*;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.specialization.*;

public abstract class AbstractEncounterTriggerAggregate extends AbstractAggregate implements EncounterTriggerAggregate {
    private EncounterTriggerState.MutableEncounterTriggerState state;

    private List<Event> changes = new ArrayList<Event>();

    public AbstractEncounterTriggerAggregate(EncounterTriggerState state) {
        this.state = (EncounterTriggerState.MutableEncounterTriggerState)state;
    }

    public EncounterTriggerState getState() {
        return this.state;
    }

    public List<Event> getChanges() {
        return this.changes;
    }

    public void throwOnInvalidStateTransition(Command c) {
        EncounterTriggerCommand.throwOnInvalidStateTransition(this.state, c);
    }

    protected void apply(Event e) {
        onApplying(e);
        state.mutate(e);
        changes.add(e);
    }


    ////////////////////////

    public static class SimpleEncounterTriggerAggregate extends AbstractEncounterTriggerAggregate {
        public SimpleEncounterTriggerAggregate(EncounterTriggerState state) {
            super(state);
        }

        @Override
        public void create(Boolean value, Long offChainVersion, String commandId, String requesterId, EncounterTriggerCommands.Create c) {
            try {
                verifyCreate(value, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            Event e = newEncounterTriggerCreated(value, offChainVersion, commandId, requesterId);
            apply(e);
        }

        protected void verifyCreate(Boolean value, EncounterTriggerCommands.Create c) {
            Boolean Value = value;

            ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.encountertrigger.CreateLogic",
                    "verify",
                    new Class[]{EncounterTriggerState.class, Boolean.class, VerificationContext.class},
                    new Object[]{getState(), value, VerificationContext.forCommand(c)}
            );

//package org.test.aptosconstantinopledemo.domain.encountertrigger;
//
//public class CreateLogic {
//    public static void verify(EncounterTriggerState encounterTriggerState, Boolean value, VerificationContext verificationContext) {
//    }
//}

        }
           

        protected AbstractEncounterTriggerEvent.EncounterTriggerCreated newEncounterTriggerCreated(Boolean value, Long offChainVersion, String commandId, String requesterId) {
            EncounterTriggerEventId eventId = new EncounterTriggerEventId(getState().getPosition(), null);
            AbstractEncounterTriggerEvent.EncounterTriggerCreated e = new AbstractEncounterTriggerEvent.EncounterTriggerCreated();

            e.setValue(value);
            e.setAptosEventVersion(null); // todo Need to update 'verify' method to return event properties.
            e.setAptosEventSequenceNumber(null); // todo Need to update 'verify' method to return event properties.
            e.setAptosEventType(null); // todo Need to update 'verify' method to return event properties.
            e.setAptosEventGuid(null); // todo Need to update 'verify' method to return event properties.
            e.setStatus(null); // todo Need to update 'verify' method to return event properties.

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setEncounterTriggerEventId(eventId);
            return e;
        }

    }

}

