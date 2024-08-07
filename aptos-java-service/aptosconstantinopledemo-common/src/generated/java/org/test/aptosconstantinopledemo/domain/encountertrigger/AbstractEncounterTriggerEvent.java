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
import org.test.aptosconstantinopledemo.domain.AbstractEvent;

public abstract class AbstractEncounterTriggerEvent extends AbstractEvent implements EncounterTriggerEvent.SqlEncounterTriggerEvent, AptosEvent.MutableAptosEvent, HasStatus.MutableHasStatus {
    private EncounterTriggerEventId encounterTriggerEventId = new EncounterTriggerEventId();

    public EncounterTriggerEventId getEncounterTriggerEventId() {
        return this.encounterTriggerEventId;
    }

    public void setEncounterTriggerEventId(EncounterTriggerEventId eventId) {
        this.encounterTriggerEventId = eventId;
    }
    
    public Position getPosition() {
        return getEncounterTriggerEventId().getPosition();
    }

    public void setPosition(Position position) {
        getEncounterTriggerEventId().setPosition(position);
    }

    private boolean eventReadOnly;

    public boolean getEventReadOnly() { return this.eventReadOnly; }

    public void setEventReadOnly(boolean readOnly) { this.eventReadOnly = readOnly; }

    public BigInteger getVersion() {
        return getEncounterTriggerEventId().getVersion();
    }
    
    public void setVersion(BigInteger version) {
        getEncounterTriggerEventId().setVersion(version);
    }

    private BigInteger aptosEventVersion;

    public BigInteger getAptosEventVersion() {
        return this.aptosEventVersion;
    }
    
    public void setAptosEventVersion(BigInteger aptosEventVersion) {
        this.aptosEventVersion = aptosEventVersion;
    }

    private BigInteger aptosEventSequenceNumber;

    public BigInteger getAptosEventSequenceNumber() {
        return this.aptosEventSequenceNumber;
    }
    
    public void setAptosEventSequenceNumber(BigInteger aptosEventSequenceNumber) {
        this.aptosEventSequenceNumber = aptosEventSequenceNumber;
    }

    private String aptosEventType;

    public String getAptosEventType() {
        return this.aptosEventType;
    }
    
    public void setAptosEventType(String aptosEventType) {
        this.aptosEventType = aptosEventType;
    }

    private AptosEventGuid aptosEventGuid;

    public AptosEventGuid getAptosEventGuid() {
        return this.aptosEventGuid;
    }
    
    public void setAptosEventGuid(AptosEventGuid aptosEventGuid) {
        this.aptosEventGuid = aptosEventGuid;
    }

    private String status;

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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


    private String commandId;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    private String commandType;

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    protected AbstractEncounterTriggerEvent() {
    }

    protected AbstractEncounterTriggerEvent(EncounterTriggerEventId eventId) {
        this.encounterTriggerEventId = eventId;
    }


    public abstract String getEventClass();

    public static class EncounterTriggerClobEvent extends AbstractEncounterTriggerEvent {

        protected Map<String, Object> getDynamicProperties() {
            return dynamicProperties;
        }

        protected void setDynamicProperties(Map<String, Object> dynamicProperties) {
            if (dynamicProperties == null) {
                throw new IllegalArgumentException("dynamicProperties is null.");
            }
            this.dynamicProperties = dynamicProperties;
        }

        private Map<String, Object> dynamicProperties = new HashMap<>();

        protected String getDynamicPropertiesLob() {
            return ApplicationContext.current.getClobConverter().toString(getDynamicProperties());
        }

        protected void setDynamicPropertiesLob(String text) {
            getDynamicProperties().clear();
            Map<String, Object> ps = ApplicationContext.current.getClobConverter().parseLobProperties(text);
            if (ps != null) {
                for (Map.Entry<String, Object> kv : ps.entrySet()) {
                    getDynamicProperties().put(kv.getKey(), kv.getValue());
                }
            }
        }

        @Override
        public String getEventClass() {
            return "EncounterTriggerClobEvent";
        }

    }

    public static class EncounterTriggerCreated extends EncounterTriggerClobEvent implements EncounterTriggerEvent.EncounterTriggerCreated {

        @Override
        public String getEventClass() {
            return "EncounterTriggerCreated";
        }

        public Boolean getValue() {
            Object val = getDynamicProperties().get("value");
            if (val instanceof Boolean) {
                return (Boolean) val;
            }
            return ApplicationContext.current.getTypeConverter().convertValue(val, Boolean.class);
        }

        public void setValue(Boolean value) {
            getDynamicProperties().put("value", value);
        }

    }


}

