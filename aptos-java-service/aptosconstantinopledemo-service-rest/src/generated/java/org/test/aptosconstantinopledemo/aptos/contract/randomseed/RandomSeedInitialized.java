// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.randomseed;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.test.aptosconstantinopledemo.aptos.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RandomSeedInitialized {

    private String accountAddress;

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    @Override
    public String toString() {
        return "RandomSeedInitialized{" +
                "accountAddress=" + '\'' + accountAddress + '\'' +
                '}';
    }

}