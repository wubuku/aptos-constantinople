// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.playerposition;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.specialization.Event;
import org.test.aptosconstantinopledemo.domain.Command;

public interface PlayerPositionApplicationService {
    void when(PlayerPositionCommands.Create c);

    void when(PlayerPositionCommands.Update c);

    PlayerPositionState get(String id);

    Iterable<PlayerPositionState> getAll(Integer firstResult, Integer maxResults);

    Iterable<PlayerPositionState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<PlayerPositionState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<PlayerPositionState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

    PlayerPositionEvent getEvent(String playerId, long version);

    PlayerPositionState getHistoryState(String playerId, long version);

}

