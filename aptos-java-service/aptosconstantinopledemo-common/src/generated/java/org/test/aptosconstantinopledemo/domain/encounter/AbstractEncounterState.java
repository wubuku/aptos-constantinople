// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encounter;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.*;
import org.test.aptosconstantinopledemo.domain.encounter.EncounterEvent.*;

public abstract class AbstractEncounterState implements EncounterState.SqlEncounterState {

    private String playerId;

    public String getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    private Boolean isExistent;

    public Boolean getIsExistent() {
        return this.isExistent;
    }

    public void setIsExistent(Boolean isExistent) {
        this.isExistent = isExistent;
    }

    private String monsterId;

    public String getMonsterId() {
        return this.monsterId;
    }

    public void setMonsterId(String monsterId) {
        this.monsterId = monsterId;
    }

    private BigInteger catchAttempts;

    public BigInteger getCatchAttempts() {
        return this.catchAttempts;
    }

    public void setCatchAttempts(BigInteger catchAttempts) {
        this.catchAttempts = catchAttempts;
    }

    private BigInteger version;

    public BigInteger getVersion() {
        return this.version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    private Long offChainVersion;

    public Long getOffChainVersion() {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion) {
        this.offChainVersion = offChainVersion;
    }

    private String createdBy;

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private String updatedBy;

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    private Date updatedAt;

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Boolean active;

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private Boolean deleted;

    public Boolean getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isStateUnsaved() {
        return this.getOffChainVersion() == null;
    }

    private Boolean stateReadOnly;

    public Boolean getStateReadOnly() { return this.stateReadOnly; }

    public void setStateReadOnly(Boolean readOnly) { this.stateReadOnly = readOnly; }

    private boolean forReapplying;

    public boolean getForReapplying() {
        return forReapplying;
    }

    public void setForReapplying(boolean forReapplying) {
        this.forReapplying = forReapplying;
    }

    public AbstractEncounterState(List<Event> events) {
        initializeForReapplying();
        if (events != null && events.size() > 0) {
            this.setPlayerId(((EncounterEvent.SqlEncounterEvent) events.get(0)).getEncounterEventId().getPlayerId());
            for (Event e : events) {
                mutate(e);
                this.setOffChainVersion((this.getOffChainVersion() == null ? EncounterState.VERSION_NULL : this.getOffChainVersion()) + 1);
            }
        }
    }


    public AbstractEncounterState() {
        initializeProperties();
    }

    protected void initializeForReapplying() {
        this.forReapplying = true;

        initializeProperties();
    }
    
    protected void initializeProperties() {
    }

    @Override
    public int hashCode() {
        return getPlayerId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj instanceof EncounterState) {
            return Objects.equals(this.getPlayerId(), ((EncounterState)obj).getPlayerId());
        }
        return false;
    }


    public void mutate(Event e) {
        setStateReadOnly(false);
        if (false) { 
            ;
        } else if (e instanceof AbstractEncounterEvent.EncounterCreated) {
            when((AbstractEncounterEvent.EncounterCreated)e);
        } else if (e instanceof AbstractEncounterEvent.EncounterUpdated) {
            when((AbstractEncounterEvent.EncounterUpdated)e);
        } else if (e instanceof AbstractEncounterEvent.EncounterDeleted) {
            when((AbstractEncounterEvent.EncounterDeleted)e);
        } else {
            throw new UnsupportedOperationException(String.format("Unsupported event type: %1$s", e.getClass().getName()));
        }
    }

    public void merge(EncounterState s) {
        if (s == this) {
            return;
        }
        this.setIsExistent(s.getIsExistent());
        this.setMonsterId(s.getMonsterId());
        this.setCatchAttempts(s.getCatchAttempts());
        this.setVersion(s.getVersion());
        this.setActive(s.getActive());
    }

    public void when(AbstractEncounterEvent.EncounterCreated e) {
        throwOnWrongEvent(e);

        Boolean isExistent = e.getIsExistent();
        Boolean IsExistent = isExistent;
        String monsterId = e.getMonsterId();
        String MonsterId = monsterId;
        BigInteger catchAttempts = e.getCatchAttempts();
        BigInteger CatchAttempts = catchAttempts;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        EncounterState updatedEncounterState = (EncounterState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.encounter.CreateLogic",
                    "mutate",
                    new Class[]{EncounterState.class, Boolean.class, String.class, BigInteger.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, isExistent, monsterId, catchAttempts, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosconstantinopledemo.domain.encounter;
//
//public class CreateLogic {
//    public static EncounterState mutate(EncounterState encounterState, Boolean isExistent, String monsterId, BigInteger catchAttempts, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<EncounterState, EncounterState.MutableEncounterState> mutationContext) {
//    }
//}

        if (this != updatedEncounterState) { merge(updatedEncounterState); } //else do nothing

    }

    public void when(AbstractEncounterEvent.EncounterUpdated e) {
        throwOnWrongEvent(e);

        Boolean isExistent = e.getIsExistent();
        Boolean IsExistent = isExistent;
        String monsterId = e.getMonsterId();
        String MonsterId = monsterId;
        BigInteger catchAttempts = e.getCatchAttempts();
        BigInteger CatchAttempts = catchAttempts;
        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        EncounterState updatedEncounterState = (EncounterState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.encounter.UpdateLogic",
                    "mutate",
                    new Class[]{EncounterState.class, Boolean.class, String.class, BigInteger.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, isExistent, monsterId, catchAttempts, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosconstantinopledemo.domain.encounter;
//
//public class UpdateLogic {
//    public static EncounterState mutate(EncounterState encounterState, Boolean isExistent, String monsterId, BigInteger catchAttempts, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<EncounterState, EncounterState.MutableEncounterState> mutationContext) {
//    }
//}

        if (this != updatedEncounterState) { merge(updatedEncounterState); } //else do nothing

    }

    public void when(AbstractEncounterEvent.EncounterDeleted e) {
        throwOnWrongEvent(e);

        BigInteger aptosEventVersion = e.getAptosEventVersion();
        BigInteger AptosEventVersion = aptosEventVersion;
        BigInteger aptosEventSequenceNumber = e.getAptosEventSequenceNumber();
        BigInteger AptosEventSequenceNumber = aptosEventSequenceNumber;
        String aptosEventType = e.getAptosEventType();
        String AptosEventType = aptosEventType;
        AptosEventGuid aptosEventGuid = e.getAptosEventGuid();
        AptosEventGuid AptosEventGuid = aptosEventGuid;
        String status = e.getStatus();
        String Status = status;

        if (this.getCreatedBy() == null){
            this.setCreatedBy(e.getCreatedBy());
        }
        if (this.getCreatedAt() == null){
            this.setCreatedAt(e.getCreatedAt());
        }
        this.setUpdatedBy(e.getCreatedBy());
        this.setUpdatedAt(e.getCreatedAt());

        EncounterState updatedEncounterState = (EncounterState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.encounter.DeleteLogic",
                    "mutate",
                    new Class[]{EncounterState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosconstantinopledemo.domain.encounter;
//
//public class DeleteLogic {
//    public static EncounterState mutate(EncounterState encounterState, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<EncounterState, EncounterState.MutableEncounterState> mutationContext) {
//    }
//}

        if (this != updatedEncounterState) { merge(updatedEncounterState); } //else do nothing

    }

    public void save() {
    }

    protected void throwOnWrongEvent(EncounterEvent event) {
        String stateEntityId = this.getPlayerId(); // Aggregate Id
        String eventEntityId = ((EncounterEvent.SqlEncounterEvent)event).getEncounterEventId().getPlayerId(); // EntityBase.Aggregate.GetEventIdPropertyIdName();
        if (!stateEntityId.equals(eventEntityId)) {
            throw DomainError.named("mutateWrongEntity", "Entity Id %1$s in state but entity id %2$s in event", stateEntityId, eventEntityId);
        }


        Long stateVersion = this.getOffChainVersion();

    }


    public static class SimpleEncounterState extends AbstractEncounterState {

        public SimpleEncounterState() {
        }

        public SimpleEncounterState(List<Event> events) {
            super(events);
        }

        public static SimpleEncounterState newForReapplying() {
            SimpleEncounterState s = new SimpleEncounterState();
            s.initializeForReapplying();
            return s;
        }

    }



}

