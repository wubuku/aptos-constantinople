// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.randomseed;

import java.util.*;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;

public interface RandomSeedStateRepository {
    RandomSeedState get(String id, boolean nullAllowed);

    void save(RandomSeedState state);

    void merge(RandomSeedState detached);
}

