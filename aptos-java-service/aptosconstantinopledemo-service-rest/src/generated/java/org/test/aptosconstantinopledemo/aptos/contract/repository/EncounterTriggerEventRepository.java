// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.repository;

import org.test.aptosconstantinopledemo.domain.encountertrigger.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface EncounterTriggerEventRepository extends JpaRepository<AbstractEncounterTriggerEvent, EncounterTriggerEventId> {

    List<AbstractEncounterTriggerEvent> findByStatusIsNull();

    AbstractEncounterTriggerEvent.EncounterTriggerCreated findFirstEncounterTriggerCreatedByOrderByAptosEventSequenceNumber();

}