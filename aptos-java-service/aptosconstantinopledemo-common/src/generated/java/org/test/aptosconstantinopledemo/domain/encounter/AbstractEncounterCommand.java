// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encounter;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.AbstractCommand;

public abstract class AbstractEncounterCommand extends AbstractCommand implements EncounterCommand {

    private String playerId;

    public String getPlayerId()
    {
        return this.playerId;
    }

    public void setPlayerId(String playerId)
    {
        this.playerId = playerId;
    }

    private Long offChainVersion;

    public Long getOffChainVersion()
    {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion)
    {
        this.offChainVersion = offChainVersion;
    }


}

