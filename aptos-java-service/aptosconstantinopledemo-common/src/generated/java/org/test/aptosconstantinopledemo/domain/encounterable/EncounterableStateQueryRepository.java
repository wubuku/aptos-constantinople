// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encounterable;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;

public interface EncounterableStateQueryRepository {
    EncounterableState get(String id);

    Iterable<EncounterableState> getAll(Integer firstResult, Integer maxResults);
    
    Iterable<EncounterableState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<EncounterableState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    EncounterableState getFirst(Iterable<Map.Entry<String, Object>> filter, List<String> orders);

    EncounterableState getFirst(Map.Entry<String, Object> keyValue, List<String> orders);

    Iterable<EncounterableState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

}

