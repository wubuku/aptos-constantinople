// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.obstruction;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;

public interface ObstructionStateQueryRepository {
    ObstructionState get(Position id);

    Iterable<ObstructionState> getAll(Integer firstResult, Integer maxResults);
    
    Iterable<ObstructionState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<ObstructionState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    ObstructionState getFirst(Iterable<Map.Entry<String, Object>> filter, List<String> orders);

    ObstructionState getFirst(Map.Entry<String, Object> keyValue, List<String> orders);

    Iterable<ObstructionState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

}

