// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;


import com.github.wubuku.aptos.bean.AccountResource;
import com.github.wubuku.aptos.utils.*;
import org.test.aptosconstantinopledemo.aptos.contract.AptosAccount;
import org.test.aptosconstantinopledemo.aptos.contract.ContractConstants;
import org.test.aptosconstantinopledemo.aptos.contract.DomainBeanUtils;
import org.test.aptosconstantinopledemo.aptos.contract.repository.AptosAccountRepository;
import org.test.aptosconstantinopledemo.domain.obstruction.*;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.aptos.contract.Obstruction;

import java.io.IOException;
import java.math.*;
import java.util.*;
import java.util.function.*;


public class AptosObstructionStateRetriever {

    private NodeApiClient aptosNodeApiClient;

    private String aptosContractAddress;

    private AptosAccountRepository aptosAccountRepository;

    private Function<Position, ObstructionState.MutableObstructionState> obstructionStateFactory;


    public AptosObstructionStateRetriever(NodeApiClient aptosNodeApiClient,
                                    String aptosContractAddress,
                                    AptosAccountRepository aptosAccountRepository,
                                    Function<Position, ObstructionState.MutableObstructionState> obstructionStateFactory
    ) {
        this.aptosNodeApiClient = aptosNodeApiClient;
        this.aptosContractAddress = aptosContractAddress;
        this.aptosAccountRepository = aptosAccountRepository;
        this.obstructionStateFactory = obstructionStateFactory;
    }

    public ObstructionState retrieveObstructionState(Position position) {
        String resourceAccountAddress = getResourceAccountAddress();
        AccountResource<Obstruction.Tables> accountResource;
        try {
            accountResource = aptosNodeApiClient.getAccountResource(resourceAccountAddress,
                    this.aptosContractAddress + "::" + ContractConstants.OBSTRUCTION_MODULE_TABLES,
                    Obstruction.Tables.class,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String tableHandle = accountResource.getData().getObstructionTable().getHandle();
        Obstruction obstruction;
        try {
            obstruction = aptosNodeApiClient.getTableItem(
                    tableHandle,
                    ContractConstants.toNumericalAddressType(ContractConstants.OBSTRUCTION_ID_TYPE, this.aptosContractAddress),
                    this.aptosContractAddress + "::" + ContractConstants.OBSTRUCTION_MODULE_OBSTRUCTION,
                    position,
                    Obstruction.class,
                    null
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return toObstructionState(obstruction);
    }

    private ObstructionState toObstructionState(Obstruction obstruction) {
        ObstructionState.MutableObstructionState obstructionState = obstructionStateFactory.apply(DomainBeanUtils.toPosition(obstruction.getPosition()));
        obstructionState.setVersion(obstruction.getVersion());
        obstructionState.setValue(obstruction.getValue());
        return obstructionState;
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }

}
