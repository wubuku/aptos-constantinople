// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.domain.encountertrigger.AbstractEncounterTriggerEvent;
import org.test.aptosconstantinopledemo.aptos.contract.repository.*;
import org.test.aptosconstantinopledemo.aptos.contract.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateEncounterTriggerStateTaskService {

    @Autowired
    private AptosEncounterTriggerService aptosEncounterTriggerService;

    @Autowired
    private EncounterTriggerEventRepository encounterTriggerEventRepository;

    @Autowired
    private EncounterTriggerEventService encounterTriggerEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-encounter-trigger-states.fixed-delay:5000}")
    @Transactional
    public void updateEncounterTriggerStates() {
        AbstractEncounterTriggerEvent e = encounterTriggerEventRepository.findFirstByStatusIsNull();
        if (e != null) {
            aptosEncounterTriggerService.updateEncounterTriggerState(e.getPosition());
            encounterTriggerEventService.updateStatusToProcessed(e);
        }
    }

}
