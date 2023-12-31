// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.aptos.contract.service.MovableEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullMovableEventsTaskService {

    @Autowired
    private MovableEventService movableEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.pull-movable-events.movable-created.fixed-delay:5000}")
    public void pullMovableCreatedEvents() {
        movableEventService.pullMovableCreatedEvents();
    }

}
