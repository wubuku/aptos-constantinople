// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.taskservice;

import org.test.aptosconstantinopledemo.domain.ownedmonsters.AbstractOwnedMonstersEvent;
import org.test.aptosconstantinopledemo.aptos.contract.repository.*;
import org.test.aptosconstantinopledemo.aptos.contract.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateOwnedMonstersStateTaskService {

    @Autowired
    private AptosOwnedMonstersService aptosOwnedMonstersService;

    @Autowired
    private OwnedMonstersEventRepository ownedMonstersEventRepository;

    @Autowired
    private OwnedMonstersEventService ownedMonstersEventService;

    @Scheduled(fixedDelayString = "${aptos.contract.update-owned-monsters-states.fixed-delay:5000}")
    @Transactional
    public void updateOwnedMonstersStates() {
        AbstractOwnedMonstersEvent e = ownedMonstersEventRepository.findFirstByStatusIsNull();
        if (e != null) {
            aptosOwnedMonstersService.updateOwnedMonstersState(e.getPlayerId());
            ownedMonstersEventService.updateStatusToProcessed(e);
        }
    }

}
