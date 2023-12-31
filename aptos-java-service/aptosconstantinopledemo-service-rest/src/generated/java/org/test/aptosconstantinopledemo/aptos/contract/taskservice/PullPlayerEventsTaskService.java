// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.aptos.contract.service.PlayerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullPlayerEventsTaskService {

    @Autowired
    private PlayerEventService playerEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.pull-player-events.player-created.fixed-delay:5000}")
    public void pullPlayerCreatedEvents() {
        playerEventService.pullPlayerCreatedEvents();
    }

}
