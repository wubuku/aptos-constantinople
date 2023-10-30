// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.ownedmonsters;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;

public interface OwnedMonstersState extends VersionedAptosMoveObject
{
    Long VERSION_ZERO = 0L;

    Long VERSION_NULL = VERSION_ZERO - 1;

    String getPlayerId();

    Long getOffChainVersion();

    String getCreatedBy();

    Date getCreatedAt();

    String getUpdatedBy();

    Date getUpdatedAt();

    Boolean getActive();

    Boolean getDeleted();

    Set<String> getMonsters();

    interface MutableOwnedMonstersState extends OwnedMonstersState, VersionedAptosMoveObject.MutableVersionedAptosMoveObject {
        void setPlayerId(String playerId);

        void setOffChainVersion(Long offChainVersion);

        void setCreatedBy(String createdBy);

        void setCreatedAt(Date createdAt);

        void setUpdatedBy(String updatedBy);

        void setUpdatedAt(Date updatedAt);

        void setActive(Boolean active);

        void setDeleted(Boolean deleted);

        void setMonsters(Set<String> monsters);


        void mutate(Event e);

        //void when(OwnedMonstersEvent.OwnedMonstersStateCreated e);

        //void when(OwnedMonstersEvent.OwnedMonstersStateMergePatched e);

        //void when(OwnedMonstersEvent.OwnedMonstersStateDeleted e);
    }

    interface SqlOwnedMonstersState extends MutableOwnedMonstersState {

        boolean isStateUnsaved();

        boolean getForReapplying();
    }
}
