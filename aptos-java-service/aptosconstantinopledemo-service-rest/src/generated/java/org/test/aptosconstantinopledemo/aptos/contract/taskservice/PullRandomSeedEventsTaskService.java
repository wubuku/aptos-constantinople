// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.aptos.contract.service.RandomSeedEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullRandomSeedEventsTaskService {

    @Autowired
    private RandomSeedEventService randomSeedEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.pull-random-seed-events.random-seed-updated.fixed-delay:5000}")
    public void pullRandomSeedUpdatedEvents() {
        randomSeedEventService.pullRandomSeedUpdatedEvents();
    }

}
