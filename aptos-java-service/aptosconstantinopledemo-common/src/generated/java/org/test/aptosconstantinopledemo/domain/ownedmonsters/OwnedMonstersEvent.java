// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.ownedmonsters;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;

public interface OwnedMonstersEvent extends Event, AptosEvent, HasStatus {

    interface SqlOwnedMonstersEvent extends OwnedMonstersEvent {
        OwnedMonstersEventId getOwnedMonstersEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface OwnedMonstersCreated extends OwnedMonstersEvent {
        String[] getMonsters();

        void setMonsters(String[] value);

    }

    interface MonsterAddedToPlayer extends OwnedMonstersEvent {
        String getMonsterId();

        void setMonsterId(String value);

    }

    String getPlayerId();

    //void setPlayerId(String playerId);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

