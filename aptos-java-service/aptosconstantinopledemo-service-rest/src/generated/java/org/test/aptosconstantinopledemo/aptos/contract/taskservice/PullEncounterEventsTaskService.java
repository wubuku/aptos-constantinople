// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.aptos.contract.service.EncounterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PullEncounterEventsTaskService {

    @Autowired
    private EncounterEventService encounterEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.pull-encounter-events.encounter-created.fixed-delay:5000}")
    public void pullEncounterCreatedEvents() {
        encounterEventService.pullEncounterCreatedEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-encounter-events.encounter-updated.fixed-delay:5000}")
    public void pullEncounterUpdatedEvents() {
        encounterEventService.pullEncounterUpdatedEvents();
    }

    @Scheduled(fixedDelayString = "${aptos.contract.pull-encounter-events.encounter-deleted.fixed-delay:5000}")
    public void pullEncounterDeletedEvents() {
        encounterEventService.pullEncounterDeletedEvents();
    }

}
