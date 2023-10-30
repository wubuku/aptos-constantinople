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
import org.test.aptosconstantinopledemo.domain.ownedmonsters.*;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.aptos.contract.OwnedMonsters;

import java.io.IOException;
import java.math.*;
import java.util.*;
import java.util.function.*;


public class AptosOwnedMonstersStateRetriever {

    private NodeApiClient aptosNodeApiClient;

    private String aptosContractAddress;

    private AptosAccountRepository aptosAccountRepository;

    private Function<String, OwnedMonstersState.MutableOwnedMonstersState> ownedMonstersStateFactory;


    public AptosOwnedMonstersStateRetriever(NodeApiClient aptosNodeApiClient,
                                    String aptosContractAddress,
                                    AptosAccountRepository aptosAccountRepository,
                                    Function<String, OwnedMonstersState.MutableOwnedMonstersState> ownedMonstersStateFactory
    ) {
        this.aptosNodeApiClient = aptosNodeApiClient;
        this.aptosContractAddress = aptosContractAddress;
        this.aptosAccountRepository = aptosAccountRepository;
        this.ownedMonstersStateFactory = ownedMonstersStateFactory;
    }

    public OwnedMonstersState retrieveOwnedMonstersState(String playerId) {
        String resourceAccountAddress = getResourceAccountAddress();
        AccountResource<OwnedMonsters.Tables> accountResource;
        try {
            accountResource = aptosNodeApiClient.getAccountResource(resourceAccountAddress,
                    this.aptosContractAddress + "::" + ContractConstants.OWNED_MONSTERS_MODULE_TABLES,
                    OwnedMonsters.Tables.class,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String tableHandle = accountResource.getData().getOwnedMonstersTable().getHandle();
        OwnedMonsters ownedMonsters;
        try {
            ownedMonsters = aptosNodeApiClient.getTableItem(
                    tableHandle,
                    ContractConstants.toNumericalAddressType(ContractConstants.OWNED_MONSTERS_ID_TYPE, this.aptosContractAddress),
                    this.aptosContractAddress + "::" + ContractConstants.OWNED_MONSTERS_MODULE_OWNED_MONSTERS,
                    playerId,
                    OwnedMonsters.class,
                    null
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return toOwnedMonstersState(ownedMonsters);
    }

    private OwnedMonstersState toOwnedMonstersState(OwnedMonsters ownedMonsters) {
        OwnedMonstersState.MutableOwnedMonstersState ownedMonstersState = ownedMonstersStateFactory.apply(ownedMonsters.getPlayerId());
        ownedMonstersState.setVersion(ownedMonsters.getVersion());
        ownedMonstersState.setMonsters(new HashSet<>(Arrays.asList(ownedMonsters.getMonsters())));
        return ownedMonstersState;
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }

}
