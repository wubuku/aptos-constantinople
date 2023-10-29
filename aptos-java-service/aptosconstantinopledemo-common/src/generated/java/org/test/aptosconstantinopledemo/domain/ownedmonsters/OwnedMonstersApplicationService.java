// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.ownedmonsters;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.Event;
import org.test.aptosconstantinopledemo.domain.Command;

public interface OwnedMonstersApplicationService {
    void when(OwnedMonstersCommands.Create c);

    void when(OwnedMonstersCommands.AddMonster c);

    OwnedMonstersState get(String id);

    Iterable<OwnedMonstersState> getAll(Integer firstResult, Integer maxResults);

    Iterable<OwnedMonstersState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<OwnedMonstersState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<OwnedMonstersState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    OwnedMonstersEvent getEvent(String playerId, long version);

    OwnedMonstersState getHistoryState(String playerId, long version);

}

