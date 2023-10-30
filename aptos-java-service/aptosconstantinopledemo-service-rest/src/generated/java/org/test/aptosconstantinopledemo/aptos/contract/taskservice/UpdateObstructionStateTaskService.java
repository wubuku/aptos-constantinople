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
public class UpdateObstructionStateTaskService {

    @Autowired
    private AptosObstructionService aptosObstructionService;

    @Autowired
    private ObstructionEventRepository obstructionEventRepository;

    @Autowired
    private ObstructionEventService obstructionEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-obstruction-states.fixed-delay:5000}")
    @Transactional
    public void updateObstructionStates() {
        obstructionEventRepository.findByStatusIsNull().forEach(e -> {
            aptosObstructionService.updateObstructionState(e.getPosition());
            obstructionEventService.updateStatusToProcessed(e);
        });
    }
}