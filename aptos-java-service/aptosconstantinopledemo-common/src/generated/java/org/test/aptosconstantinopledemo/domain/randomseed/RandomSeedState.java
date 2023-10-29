// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.randomseed;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;

public interface RandomSeedState extends VersionedAptosMoveObject
{
    Long VERSION_ZERO = 0L;

    Long VERSION_NULL = VERSION_ZERO - 1;

    String getAccountAddress();

    BigInteger getValue();

    Long getOffChainVersion();

    String getCreatedBy();

    Date getCreatedAt();

    String getUpdatedBy();

    Date getUpdatedAt();

    Boolean getActive();

    Boolean getDeleted();

    interface MutableRandomSeedState extends RandomSeedState, VersionedAptosMoveObject.MutableVersionedAptosMoveObject {
        void setAccountAddress(String accountAddress);

        void setValue(BigInteger value);

        void setOffChainVersion(Long offChainVersion);

        void setCreatedBy(String createdBy);

        void setCreatedAt(Date createdAt);

        void setUpdatedBy(String updatedBy);

        void setUpdatedAt(Date updatedAt);

        void setActive(Boolean active);

        void setDeleted(Boolean deleted);


        void mutate(Event e);

        //void when(RandomSeedEvent.RandomSeedStateCreated e);

        //void when(RandomSeedEvent.RandomSeedStateMergePatched e);

        //void when(RandomSeedEvent.RandomSeedStateDeleted e);
    }

    interface SqlRandomSeedState extends MutableRandomSeedState {

        boolean isStateUnsaved();

        boolean getForReapplying();
    }
}

