// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.map;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.*;
import org.test.aptosconstantinopledemo.domain.map.MapEvent.*;

public abstract class AbstractMapState implements MapState.SqlMapState {

    private String accountAddress;

    public String getAccountAddress() {
        return this.accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    private BigInteger width;

    public BigInteger getWidth() {
        return this.width;
    }

    public void setWidth(BigInteger width) {
        this.width = width;
    }

    private BigInteger height;

    public BigInteger getHeight() {
        return this.height;
    }

    public void setHeight(BigInteger height) {
        this.height = height;
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

    private BigInteger version;

    public BigInteger getVersion() {
        return this.version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    private Set<String> terrain;

    public Set<String> getTerrain() {
        return this.terrain;
    }

    public void setTerrain(Set<String> terrain) {
        this.terrain = terrain;
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

    public AbstractMapState(List<Event> events) {
        initializeForReapplying();
        if (events != null && events.size() > 0) {
            this.setAccountAddress(((MapEvent.SqlMapEvent) events.get(0)).getMapEventId().getAccountAddress());
            for (Event e : events) {
                mutate(e);
                this.setOffChainVersion((this.getOffChainVersion() == null ? MapState.VERSION_NULL : this.getOffChainVersion()) + 1);
            }
        }
    }


    public AbstractMapState() {
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
        return getAccountAddress().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj instanceof MapState) {
            return Objects.equals(this.getAccountAddress(), ((MapState)obj).getAccountAddress());
        }
        return false;
    }


    public void mutate(Event e) {
        setStateReadOnly(false);
        if (false) { 
            ;
        } else if (e instanceof AbstractMapEvent.MapInitialized) {
            when((AbstractMapEvent.MapInitialized)e);
        } else {
            throw new UnsupportedOperationException(String.format("Unsupported event type: %1$s", e.getClass().getName()));
        }
    }

    protected void merge(MapState s) {
        if (s == this) {
            return;
        }
        this.setWidth(s.getWidth());
        this.setHeight(s.getHeight());
        this.setTerrain(s.getTerrain());
        this.setActive(s.getActive());
        this.setVersion(s.getVersion());
    }

    public void when(AbstractMapEvent.MapInitialized e) {
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

        MapState updatedMapState = (MapState) ReflectUtils.invokeStaticMethod(
                    "org.test.aptosconstantinopledemo.domain.map.__Init__Logic",
                    "mutate",
                    new Class[]{MapState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new Object[]{this, aptosEventVersion, aptosEventSequenceNumber, aptosEventType, aptosEventGuid, status, MutationContext.forEvent(e, s -> {if (s == this) {return this;} else {throw new UnsupportedOperationException();}})}
            );

//package org.test.aptosconstantinopledemo.domain.map;
//
//public class __Init__Logic {
//    public static MapState mutate(MapState mapState, BigInteger aptosEventVersion, BigInteger aptosEventSequenceNumber, String aptosEventType, AptosEventGuid aptosEventGuid, String status, MutationContext<MapState, MapState.MutableMapState> mutationContext) {
//    }
//}

        if (this != updatedMapState) { merge(updatedMapState); } //else do nothing

    }

    public void save() {
    }

    protected void throwOnWrongEvent(MapEvent event) {
        String stateEntityId = this.getAccountAddress(); // Aggregate Id
        String eventEntityId = ((MapEvent.SqlMapEvent)event).getMapEventId().getAccountAddress(); // EntityBase.Aggregate.GetEventIdPropertyIdName();
        if (!stateEntityId.equals(eventEntityId)) {
            throw DomainError.named("mutateWrongEntity", "Entity Id %1$s in state but entity id %2$s in event", stateEntityId, eventEntityId);
        }


        Long stateVersion = this.getOffChainVersion();

    }


    public static class SimpleMapState extends AbstractMapState {

        public SimpleMapState() {
        }

        public SimpleMapState(List<Event> events) {
            super(events);
        }

        public static SimpleMapState newForReapplying() {
            SimpleMapState s = new SimpleMapState();
            s.initializeForReapplying();
            return s;
        }

    }



}
