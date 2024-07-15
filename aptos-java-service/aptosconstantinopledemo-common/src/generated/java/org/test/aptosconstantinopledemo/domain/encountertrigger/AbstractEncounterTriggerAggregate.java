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
            java.util.function.Supplier<EncounterTriggerEvent.EncounterTriggerCreated> eventFactory = () -> newEncounterTriggerCreated(value, offChainVersion, commandId, requesterId);
            EncounterTriggerEvent.EncounterTriggerCreated e;
            try {
                e = verifyCreate(eventFactory, value, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        protected EncounterTriggerEvent.EncounterTriggerCreated verifyCreate(java.util.function.Supplier<EncounterTriggerEvent.EncounterTriggerCreated> eventFactory, Boolean value, EncounterTriggerCommands.Create c) {
            Boolean Value = value;

            EncounterTriggerEvent.EncounterTriggerCreated e = (EncounterTriggerEvent.EncounterTriggerCreated) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.encountertrigger.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, EncounterTriggerState.class, Boolean.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), value, VerificationContext.forCommand(c)}
            );

//package org.test.aptosconstantinopledemo.domain.encountertrigger;
//
//public class CreateLogic {
//    public static EncounterTriggerEvent.EncounterTriggerCreated verify(java.util.function.Supplier<EncounterTriggerEvent.EncounterTriggerCreated> eventFactory, EncounterTriggerState encounterTriggerState, Boolean value, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected AbstractEncounterTriggerEvent.EncounterTriggerCreated newEncounterTriggerCreated(Boolean value, Long offChainVersion, String commandId, String requesterId) {
            EncounterTriggerEventId eventId = new EncounterTriggerEventId(getState().getPosition(), null);
            AbstractEncounterTriggerEvent.EncounterTriggerCreated e = new AbstractEncounterTriggerEvent.EncounterTriggerCreated();

            e.setValue(value);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setEncounterTriggerEventId(eventId);
            return e;
        }

    }

}

