// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.map.*;
import org.test.aptosconstantinopledemo.aptos.contract.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosMapService {

    @Autowired
    private MapStateRepository mapStateRepository;


    private AptosMapStateRetriever aptosMapStateRetriever;

    @Autowired
    public AptosMapService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosMapStateRetriever = new AptosMapStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                accountAddress -> {
                    MapState.MutableMapState s = new AbstractMapState.SimpleMapState();
                    s.setAccountAddress(accountAddress);
                    return s;
                }
        );
    }

    @Transactional
    public void updateMapState(String accountAddress) {
        MapState mapState = aptosMapStateRetriever.retrieveMapState(accountAddress);
        if (mapState == null) {
            return;
        }
        mapStateRepository.merge(mapState);
    }

}

