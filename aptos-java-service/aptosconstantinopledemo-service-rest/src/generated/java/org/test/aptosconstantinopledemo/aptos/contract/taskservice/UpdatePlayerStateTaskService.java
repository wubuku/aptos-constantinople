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
public class UpdatePlayerStateTaskService {

    @Autowired
    private AptosPlayerService aptosPlayerService;

    @Autowired
    private PlayerEventRepository playerEventRepository;

    @Autowired
    private PlayerEventService playerEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-player-states.fixed-delay:5000}")
    @Transactional
    public void updatePlayerStates() {
        playerEventRepository.findByStatusIsNull().forEach(e -> {
            aptosPlayerService.updatePlayerState(e.getPlayerId());
            playerEventService.updateStatusToProcessed(e);
        });
    }
}
