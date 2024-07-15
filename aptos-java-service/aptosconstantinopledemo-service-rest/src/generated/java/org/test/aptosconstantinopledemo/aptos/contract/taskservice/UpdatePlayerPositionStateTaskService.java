// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.aptos.contract.repository.*;
import org.test.aptosconstantinopledemo.aptos.contract.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdatePlayerPositionStateTaskService {

    @Autowired
    private AptosPlayerPositionService aptosPlayerPositionService;

    @Autowired
    private PlayerPositionEventRepository playerPositionEventRepository;

    @Autowired
    private PlayerPositionEventService playerPositionEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-player-position-states.fixed-delay:5000}")
    @Transactional
    public void updatePlayerPositionStates() {
        playerPositionEventRepository.findByStatusIsNull().forEach(e -> {
            aptosPlayerPositionService.updatePlayerPositionState(e.getPlayerId());
            playerPositionEventService.updateStatusToProcessed(e);
        });
    }

}
