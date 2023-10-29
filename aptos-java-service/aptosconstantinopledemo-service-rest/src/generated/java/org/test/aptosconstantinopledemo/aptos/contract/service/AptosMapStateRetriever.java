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
import org.test.aptosconstantinopledemo.domain.map.*;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.aptos.contract.Map;

import java.io.IOException;
import java.math.*;
import java.util.*;
import java.util.function.*;


public class AptosMapStateRetriever {

    private NodeApiClient aptosNodeApiClient;

    private String aptosContractAddress;

    private AptosAccountRepository aptosAccountRepository;

    private Function<String, MapState.MutableMapState> mapStateFactory;


    public AptosMapStateRetriever(NodeApiClient aptosNodeApiClient,
                                    String aptosContractAddress,
                                    AptosAccountRepository aptosAccountRepository,
                                    Function<String, MapState.MutableMapState> mapStateFactory
    ) {
        this.aptosNodeApiClient = aptosNodeApiClient;
        this.aptosContractAddress = aptosContractAddress;
        this.aptosAccountRepository = aptosAccountRepository;
        this.mapStateFactory = mapStateFactory;
    }

    public MapState retrieveMapState(String accountAddress) {
        String resourceAccountAddress = getResourceAccountAddress();
        AccountResource<Map> accountResource;
        try {
            accountResource = aptosNodeApiClient.getAccountResource(resourceAccountAddress,
                    this.aptosContractAddress + "::" + ContractConstants.MAP_MODULE_MAP,
                    Map.class,
                    null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map map = accountResource.getData();
        map.setAccountAddress(resourceAccountAddress);
        return toMapState(map);
    }

    private MapState toMapState(Map map) {
        MapState.MutableMapState mapState = mapStateFactory.apply(map.getAccountAddress());
        mapState.setVersion(map.getVersion());
        mapState.setWidth(map.getWidth());
        mapState.setHeight(map.getHeight());
        mapState.setTerrain(new HashSet<>(Arrays.asList(map.getTerrain())));
        return mapState;
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }

}

