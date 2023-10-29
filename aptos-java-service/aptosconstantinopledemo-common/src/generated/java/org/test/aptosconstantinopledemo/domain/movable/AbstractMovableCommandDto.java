// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.movable;

import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.AbstractCommand;

public abstract class AbstractMovableCommandDto extends AbstractCommand {

    /**
     * Player Id
     */
    private String playerId;

    public String getPlayerId()
    {
        return this.playerId;
    }

    public void setPlayerId(String playerId)
    {
        this.playerId = playerId;
    }

    /**
     * Off Chain Version
     */
    private Long offChainVersion;

    public Long getOffChainVersion()
    {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion)
    {
        this.offChainVersion = offChainVersion;
    }


    public void copyTo(MovableCommand command) {
        command.setPlayerId(this.getPlayerId());
        command.setOffChainVersion(this.getOffChainVersion());
        
        command.setRequesterId(this.getRequesterId());
        command.setCommandId(this.getCommandId());
    }

}
