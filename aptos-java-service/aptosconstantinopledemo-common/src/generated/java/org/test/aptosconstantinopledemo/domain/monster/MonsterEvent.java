// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.monster;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;

public interface MonsterEvent extends Event, AptosEvent, HasStatus {

    interface SqlMonsterEvent extends MonsterEvent {
        MonsterEventId getMonsterEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface MonsterCreated extends MonsterEvent {
        BigInteger getMonsterType();

        void setMonsterType(BigInteger value);

    }

    interface MonsterDeleted extends MonsterEvent {
    }

    String getMonsterId();

    //void setMonsterId(String monsterId);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

