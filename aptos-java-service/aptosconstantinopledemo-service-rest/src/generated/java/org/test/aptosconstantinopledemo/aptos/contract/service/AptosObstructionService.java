// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.obstruction.*;
import org.test.aptosconstantinopledemo.aptos.contract.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosObstructionService {

    @Autowired
    private ObstructionStateRepository obstructionStateRepository;


    private AptosObstructionStateRetriever aptosObstructionStateRetriever;

    @Autowired
    public AptosObstructionService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosObstructionStateRetriever = new AptosObstructionStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                position -> {
                    ObstructionState.MutableObstructionState s = new AbstractObstructionState.SimpleObstructionState();
                    s.setPosition(position);
                    return s;
                }
        );
    }

    @Transactional
    public void updateObstructionState(Position position) {
        ObstructionState obstructionState = aptosObstructionStateRetriever.retrieveObstructionState(position);
        if (obstructionState == null) {
            return;
        }
        obstructionStateRepository.merge(obstructionState);
    }

}
