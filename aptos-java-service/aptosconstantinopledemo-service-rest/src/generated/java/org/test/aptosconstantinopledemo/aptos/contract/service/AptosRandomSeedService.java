// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.randomseed.*;
import org.test.aptosconstantinopledemo.aptos.contract.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosRandomSeedService {

    @Autowired
    private RandomSeedStateRepository randomSeedStateRepository;


    private AptosRandomSeedStateRetriever aptosRandomSeedStateRetriever;

    @Autowired
    public AptosRandomSeedService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosRandomSeedStateRetriever = new AptosRandomSeedStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                accountAddress -> {
                    RandomSeedState.MutableRandomSeedState s = new AbstractRandomSeedState.SimpleRandomSeedState();
                    s.setAccountAddress(accountAddress);
                    return s;
                }
        );
    }

    @Transactional
    public void updateRandomSeedState(String accountAddress) {
        RandomSeedState randomSeedState = aptosRandomSeedStateRetriever.retrieveRandomSeedState(accountAddress);
        if (randomSeedState == null) {
            return;
        }
        randomSeedStateRepository.merge(randomSeedState);
    }

}

