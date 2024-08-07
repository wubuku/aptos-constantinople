// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.domain.randomseed.AbstractRandomSeedEvent;
import org.test.aptosconstantinopledemo.aptos.contract.repository.*;
import org.test.aptosconstantinopledemo.aptos.contract.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateRandomSeedStateTaskService {

    @Autowired
    private AptosRandomSeedService aptosRandomSeedService;

    @Autowired
    private RandomSeedEventRepository randomSeedEventRepository;

    @Autowired
    private RandomSeedEventService randomSeedEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-random-seed-states.fixed-delay:5000}")
    @Transactional
    public void updateRandomSeedStates() {
        AbstractRandomSeedEvent e = randomSeedEventRepository.findFirstByStatusIsNull();
        if (e != null) {
            aptosRandomSeedService.updateRandomSeedState(e.getAccountAddress());
            randomSeedEventService.updateStatusToProcessed(e);
        }
    }

}
