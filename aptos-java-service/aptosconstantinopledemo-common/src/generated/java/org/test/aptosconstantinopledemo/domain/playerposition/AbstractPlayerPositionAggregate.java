// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.playerposition;

import java.util.*;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.specialization.*;

public abstract class AbstractPlayerPositionAggregate extends AbstractAggregate implements PlayerPositionAggregate {
    private PlayerPositionState.MutablePlayerPositionState state;

    private List<Event> changes = new ArrayList<Event>();

    public AbstractPlayerPositionAggregate(PlayerPositionState state) {
        this.state = (PlayerPositionState.MutablePlayerPositionState)state;
    }

    public PlayerPositionState getState() {
        return this.state;
    }

    public List<Event> getChanges() {
        return this.changes;
    }

    public void throwOnInvalidStateTransition(Command c) {
        PlayerPositionCommand.throwOnInvalidStateTransition(this.state, c);
    }

    protected void apply(Event e) {
        onApplying(e);
        state.mutate(e);
        changes.add(e);
    }


    ////////////////////////

    public static class SimplePlayerPositionAggregate extends AbstractPlayerPositionAggregate {
        public SimplePlayerPositionAggregate(PlayerPositionState state) {
            super(state);
        }

        @Override
        public void create(Position position, Long offChainVersion, String commandId, String requesterId, PlayerPositionCommands.Create c) {
            java.util.function.Supplier<PlayerPositionEvent.PlayerPositionCreated> eventFactory = () -> newPlayerPositionCreated(position, offChainVersion, commandId, requesterId);
            PlayerPositionEvent.PlayerPositionCreated e;
            try {
                e = verifyCreate(eventFactory, position, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        @Override
        public void update(Position position, Long offChainVersion, String commandId, String requesterId, PlayerPositionCommands.Update c) {
            java.util.function.Supplier<PlayerPositionEvent.PlayerPositionUpdated> eventFactory = () -> newPlayerPositionUpdated(position, offChainVersion, commandId, requesterId);
            PlayerPositionEvent.PlayerPositionUpdated e;
            try {
                e = verifyUpdate(eventFactory, position, c);
            } catch (Exception ex) {
                throw new DomainError("VerificationFailed", ex);
            }

            apply(e);
        }

        protected PlayerPositionEvent.PlayerPositionCreated verifyCreate(java.util.function.Supplier<PlayerPositionEvent.PlayerPositionCreated> eventFactory, Position position, PlayerPositionCommands.Create c) {
            Position Position = position;

            PlayerPositionEvent.PlayerPositionCreated e = (PlayerPositionEvent.PlayerPositionCreated) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.playerposition.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlayerPositionState.class, Position.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), position, VerificationContext.forCommand(c)}
            );

//package org.test.aptosconstantinopledemo.domain.playerposition;
//
//public class CreateLogic {
//    public static PlayerPositionEvent.PlayerPositionCreated verify(java.util.function.Supplier<PlayerPositionEvent.PlayerPositionCreated> eventFactory, PlayerPositionState playerPositionState, Position position, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected PlayerPositionEvent.PlayerPositionUpdated verifyUpdate(java.util.function.Supplier<PlayerPositionEvent.PlayerPositionUpdated> eventFactory, Position position, PlayerPositionCommands.Update c) {
            Position Position = position;

            PlayerPositionEvent.PlayerPositionUpdated e = (PlayerPositionEvent.PlayerPositionUpdated) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.playerposition.UpdateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlayerPositionState.class, Position.class, VerificationContext.class},
                    new Object[]{eventFactory, getState(), position, VerificationContext.forCommand(c)}
            );

//package org.test.aptosconstantinopledemo.domain.playerposition;
//
//public class UpdateLogic {
//    public static PlayerPositionEvent.PlayerPositionUpdated verify(java.util.function.Supplier<PlayerPositionEvent.PlayerPositionUpdated> eventFactory, PlayerPositionState playerPositionState, Position position, VerificationContext verificationContext) {
//    }
//}

            return e;
        }
           

        protected AbstractPlayerPositionEvent.PlayerPositionCreated newPlayerPositionCreated(Position position, Long offChainVersion, String commandId, String requesterId) {
            PlayerPositionEventId eventId = new PlayerPositionEventId(getState().getPlayerId(), null);
            AbstractPlayerPositionEvent.PlayerPositionCreated e = new AbstractPlayerPositionEvent.PlayerPositionCreated();

            e.setPosition(position);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setPlayerPositionEventId(eventId);
            return e;
        }

        protected AbstractPlayerPositionEvent.PlayerPositionUpdated newPlayerPositionUpdated(Position position, Long offChainVersion, String commandId, String requesterId) {
            PlayerPositionEventId eventId = new PlayerPositionEventId(getState().getPlayerId(), null);
            AbstractPlayerPositionEvent.PlayerPositionUpdated e = new AbstractPlayerPositionEvent.PlayerPositionUpdated();

            e.setPosition(position);
            e.setAptosEventVersion(null);
            e.setAptosEventSequenceNumber(null);
            e.setAptosEventType(null);
            e.setAptosEventGuid(null);
            e.setStatus(null);

            e.setCommandId(commandId);
            e.setCreatedBy(requesterId);
            e.setCreatedAt((java.util.Date)ApplicationContext.current.getTimestampService().now(java.util.Date.class));

            e.setPlayerPositionEventId(eventId);
            return e;
        }

    }

}

