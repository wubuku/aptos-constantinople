// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encountertrigger;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;

public interface EncounterTriggerStateQueryRepository {
    EncounterTriggerState get(Position id);

    Iterable<EncounterTriggerState> getAll(Integer firstResult, Integer maxResults);
    
    Iterable<EncounterTriggerState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<EncounterTriggerState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    EncounterTriggerState getFirst(Iterable<Map.Entry<String, Object>> filter, List<String> orders);

    EncounterTriggerState getFirst(Map.Entry<String, Object> keyValue, List<String> orders);

    Iterable<EncounterTriggerState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

}

