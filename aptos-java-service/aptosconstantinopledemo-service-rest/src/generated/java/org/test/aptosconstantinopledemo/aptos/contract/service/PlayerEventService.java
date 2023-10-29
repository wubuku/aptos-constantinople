// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.service;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.utils.NodeApiClient;

import org.test.aptosconstantinopledemo.domain.player.AbstractPlayerEvent;
import org.test.aptosconstantinopledemo.aptos.contract.ContractConstants;
import org.test.aptosconstantinopledemo.aptos.contract.DomainBeanUtils;
import org.test.aptosconstantinopledemo.aptos.contract.AptosAccount;

import org.test.aptosconstantinopledemo.aptos.contract.player.PlayerCreated;
import org.test.aptosconstantinopledemo.aptos.contract.repository.PlayerEventRepository;
import org.test.aptosconstantinopledemo.aptos.contract.repository.AptosAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.*;
import java.util.*;


@Service
public class PlayerEventService {

    @Value("${aptos.contract.address}")
    private String aptosContractAddress;

    @Autowired
    private AptosAccountRepository aptosAccountRepository;

    @Autowired
    private NodeApiClient aptosNodeApiClient;

    @Autowired
    private PlayerEventRepository playerEventRepository;

    @Transactional
    public void updateStatusToProcessed(AbstractPlayerEvent event) {
        event.setStatus("D");
        playerEventRepository.save(event);
    }

    @Transactional
    public void pullPlayerCreatedEvents() {
        String resourceAccountAddress = getResourceAccountAddress();
        if (resourceAccountAddress == null) {
            return;
        }
        int limit = 1;
        BigInteger cursor = getPlayerCreatedEventNextCursor();
        if (cursor == null) {
            cursor = BigInteger.ZERO;
        }
        while (true) {
            List<Event<PlayerCreated>> eventPage;
            try {
                eventPage = aptosNodeApiClient.getEventsByEventHandle(
                        resourceAccountAddress,
                        this.aptosContractAddress + "::" + ContractConstants.PLAYER_MODULE_EVENTS,
                        ContractConstants.PLAYER_MODULE_PLAYER_CREATED_HANDLE_FIELD,
                        PlayerCreated.class,
                        cursor.longValue(),
                        limit
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (eventPage != null && eventPage.size() > 0) {
                cursor = cursor.add(BigInteger.ONE);
                for (Event<PlayerCreated> eventEnvelope : eventPage) {
                    savePlayerCreated(eventEnvelope);
                }
            } else {
                break;
            }
        }
    }

    private BigInteger getPlayerCreatedEventNextCursor() {
        AbstractPlayerEvent.PlayerCreated lastEvent = playerEventRepository.findFirstPlayerCreatedByOrderByAptosEventSequenceNumber();
        return lastEvent != null ? lastEvent.getAptosEventSequenceNumber() : null;
    }

    private void savePlayerCreated(Event<PlayerCreated> eventEnvelope) {
        AbstractPlayerEvent.PlayerCreated playerCreated = DomainBeanUtils.toPlayerCreated(eventEnvelope);
        if (playerEventRepository.findById(playerCreated.getPlayerEventId()).isPresent()) {
            return;
        }
        playerEventRepository.save(playerCreated);
    }

    private String getResourceAccountAddress() {
        return aptosAccountRepository.findById(ContractConstants.RESOURCE_ACCOUNT_ADDRESS)
                .map(AptosAccount::getAddress).orElse(null);
    }
}
