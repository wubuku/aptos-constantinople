// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.utils.NodeApiClient;

import org.test.aptosconstantinopledemo.domain.encounterable.AbstractEncounterableEvent;
import org.test.aptosconstantinopledemo.aptos.contract.ContractConstants;
import org.test.aptosconstantinopledemo.aptos.contract.DomainBeanUtils;
import org.test.aptosconstantinopledemo.aptos.contract.AptosAccount;

import org.test.aptosconstantinopledemo.aptos.contract.encounterable.EncounterableCreated;
import org.test.aptosconstantinopledemo.aptos.contract.repository.EncounterableEventRepository;
import org.test.aptosconstantinopledemo.aptos.contract.repository.AptosAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.*;
import java.util.*;


@Service
public class EncounterableEventService {

    @Value("${aptos.contract.address}")
    private String aptosContractAddress;

    @Autowired
    private AptosAccountRepository aptosAccountRepository;

    @Autowired
    private NodeApiClient aptosNodeApiClient;

    @Autowired
    private EncounterableEventRepository encounterableEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractEncounterableEvent event) {
        event.setStatus("D");
        encounterableEventRepository.save(event);
    }

    @Transactional
    public void pullEncounterableCreatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getEncounterableCreatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<EncounterableCreated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.ENCOUNTERABLE_MODULE_EVENTS,
                        ContractConstants.ENCOUNTERABLE_MODULE_ENCOUNTERABLE_CREATED_HANDLE_FIELD,
                        EncounterableCreated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<EncounterableCreated> eventEnvelope : eventPage) {
                    saveEncounterableCreated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getEncounterableCreatedEventNextCursor() {
        AbstractEncounterableEvent.EncounterableCreated lastEvent = encounterableEventRepository.findFirstEncounterableCreatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void saveEncounterableCreated(Event<EncounterableCreated> eventEnvelope) {
        AbstractEncounterableEvent.EncounterableCreated encounterableCreated = DomainBeanUtils.toEncounterableCreated(eventEnvelope);
        if (encounterableEventRepository.findById(encounterableCreated.getEncounterableEventId()).isPresent()) {
            return;
        }
        encounterableEventRepository.save(encounterableCreated);
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }
}
