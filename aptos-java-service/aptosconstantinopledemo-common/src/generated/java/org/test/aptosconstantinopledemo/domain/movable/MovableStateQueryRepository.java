// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.movable;

import java.util.Map;
import java.util.List;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;

public interface MovableStateQueryRepository {
    MovableState get(String id);

    Iterable<MovableState> getAll(Integer firstResult, Integer maxResults);
    
    Iterable<MovableState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults);

    Iterable<MovableState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults);

    MovableState getFirst(Iterable<Map.Entry<String, Object>> filter, List<String> orders);

    MovableState getFirst(Map.Entry<String, Object> keyValue, List<String> orders);

    Iterable<MovableState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults);

    long getCount(Iterable<Map.Entry<String, Object>> filter);

    long getCount(Criterion filter);

}

