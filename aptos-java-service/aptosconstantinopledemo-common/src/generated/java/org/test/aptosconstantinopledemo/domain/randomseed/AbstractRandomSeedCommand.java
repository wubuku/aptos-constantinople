// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.randomseed;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.AbstractCommand;

public abstract class AbstractRandomSeedCommand extends AbstractCommand implements RandomSeedCommand {

    private String accountAddress;

    public String getAccountAddress()
    {
        return this.accountAddress;
    }

    public void setAccountAddress(String accountAddress)
    {
        this.accountAddress = accountAddress;
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

