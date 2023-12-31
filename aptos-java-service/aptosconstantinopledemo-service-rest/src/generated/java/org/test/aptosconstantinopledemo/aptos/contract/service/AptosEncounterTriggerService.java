// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;

import com.github.wubuku.aptos.utils.NodeApiClient;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.encountertrigger.*;
import org.test.aptosconstantinopledemo.aptos.contract.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.*;
import java.util.*;
import java.math.*;

@Service
public class AptosEncounterTriggerService {

    @Autowired
    private EncounterTriggerStateRepository encounterTriggerStateRepository;


    private AptosEncounterTriggerStateRetriever aptosEncounterTriggerStateRetriever;

    @Autowired
    public AptosEncounterTriggerService(
        @Value("${aptos.contract.address}")
        String aptosContractAddress,
        NodeApiClient aptosNodeApiClient,
        AptosAccountRepository aptosAccountRepository
    ) {
        this.aptosEncounterTriggerStateRetriever = new AptosEncounterTriggerStateRetriever(
                aptosNodeApiClient,
                aptosContractAddress,
                aptosAccountRepository,
                position -> {
                    EncounterTriggerState.MutableEncounterTriggerState s = new AbstractEncounterTriggerState.SimpleEncounterTriggerState();
                    s.setPosition(position);
                    return s;
                }
        );
    }

    @Transactional
    public void updateEncounterTriggerState(Position position) {
        EncounterTriggerState encounterTriggerState = aptosEncounterTriggerStateRetriever.retrieveEncounterTriggerState(position);
        if (encounterTriggerState == null) {
            return;
        }
        encounterTriggerStateRepository.merge(encounterTriggerState);
    }

}

