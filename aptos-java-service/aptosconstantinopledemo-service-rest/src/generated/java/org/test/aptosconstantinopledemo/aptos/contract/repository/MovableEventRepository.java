// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.repository;

import org.test.aptosconstantinopledemo.domain.movable.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MovableEventRepository extends JpaRepository<AbstractMovableEvent, MovableEventId> {

    AbstractMovableEvent findFirstByStatusIsNull();

    List<AbstractMovableEvent> findByStatusIsNull();

    AbstractMovableEvent.MovableCreated findFirstMovableCreatedByOrderByAptosEventSequenceNumber();

}
