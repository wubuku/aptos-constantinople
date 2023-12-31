// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.utils.NodeApiClient;

import org.test.aptosconstantinopledemo.domain.encountertrigger.AbstractEncounterTriggerEvent;
import org.test.aptosconstantinopledemo.aptos.contract.ContractConstants;
import org.test.aptosconstantinopledemo.aptos.contract.DomainBeanUtils;
import org.test.aptosconstantinopledemo.aptos.contract.AptosAccount;

import org.test.aptosconstantinopledemo.aptos.contract.encountertrigger.EncounterTriggerCreated;
import org.test.aptosconstantinopledemo.aptos.contract.repository.EncounterTriggerEventRepository;
import org.test.aptosconstantinopledemo.aptos.contract.repository.AptosAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.*;
import java.util.*;


@Service
public class EncounterTriggerEventService {

    @Value("${aptos.contract.address}")
    private String aptosContractAddress;

    @Autowired
    private AptosAccountRepository aptosAccountRepository;

    @Autowired
    private NodeApiClient aptosNodeApiClient;

    @Autowired
    private EncounterTriggerEventRepository encounterTriggerEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractEncounterTriggerEvent event) {
        event.setStatus("D");
        encounterTriggerEventRepository.save(event);
    }

    @Transactional
    public void pullEncounterTriggerCreatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getEncounterTriggerCreatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<EncounterTriggerCreated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ENCOUNTER_TRIGGER_MODULE_EVENTS,
                        ContractConstants.ENCOUNTER_TRIGGER_MODULE_ENCOUNTER_TRIGGER_CREATED_HANDLE_FIELD,
                        EncounterTriggerCreated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<EncounterTriggerCreated> eventEnvelope : eventPage) {
                    saveEncounterTriggerCreated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getEncounterTriggerCreatedEventNextCursor() {
        AbstractEncounterTriggerEvent.EncounterTriggerCreated lastEvent = encounterTriggerEventRepository.findFirstEncounterTriggerCreatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveEncounterTriggerCreated(Event<EncounterTriggerCreated> eventEnvelope) {
        AbstractEncounterTriggerEvent.EncounterTriggerCreated encounterTriggerCreated = DomainBeanUtils.toEncounterTriggerCreated(eventEnvelope);
        if (encounterTriggerEventRepository.findById(encounterTriggerCreated.getEncounterTriggerEventId()).isPresent()) {
            return;
        }
        encounterTriggerEventRepository.save(encounterTriggerCreated);
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }
}
