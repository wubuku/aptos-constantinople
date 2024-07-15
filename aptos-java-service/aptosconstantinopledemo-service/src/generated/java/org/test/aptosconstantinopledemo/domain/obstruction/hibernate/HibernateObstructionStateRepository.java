// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.obstruction.hibernate;

import java.util.*;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.SessionFactory;
import org.test.aptosconstantinopledemo.domain.obstruction.*;
import org.test.aptosconstantinopledemo.specialization.*;
import org.test.aptosconstantinopledemo.specialization.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

public class HibernateObstructionStateRepository implements ObstructionStateRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() { return this.sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    private static final Set<String> readOnlyPropertyPascalCaseNames = new HashSet<String>(Arrays.asList("Position", "Value", "Version", "OffChainVersion", "CreatedBy", "CreatedAt", "UpdatedBy", "UpdatedAt", "Active", "Deleted"));
    
    private ReadOnlyProxyGenerator readOnlyProxyGenerator;
    
    public ReadOnlyProxyGenerator getReadOnlyProxyGenerator() {
        return readOnlyProxyGenerator;
    }

    public void setReadOnlyProxyGenerator(ReadOnlyProxyGenerator readOnlyProxyGenerator) {
        this.readOnlyProxyGenerator = readOnlyProxyGenerator;
    }

    @Transactional(readOnly = true)
    public ObstructionState get(Position id, boolean nullAllowed) {
        ObstructionState.SqlObstructionState state = (ObstructionState.SqlObstructionState)getCurrentSession().get(AbstractObstructionState.SimpleObstructionState.class, id);
        if (!nullAllowed && state == null) {
            state = new AbstractObstructionState.SimpleObstructionState();
            state.setPosition(id);
        }
        if (getReadOnlyProxyGenerator() != null && state != null) {
            return (ObstructionState) getReadOnlyProxyGenerator().createProxy(state, new Class[]{ObstructionState.SqlObstructionState.class}, "getStateReadOnly", readOnlyPropertyPascalCaseNames);
        }
        return state;
    }

    public void save(ObstructionState state) {
        ObstructionState s = state;
        if (getReadOnlyProxyGenerator() != null) {
            s = (ObstructionState) getReadOnlyProxyGenerator().getTarget(state);
        }
        if(s.getOffChainVersion() == null) {
            getCurrentSession().save(s);
        } else {
            getCurrentSession().update(s);
        }

        if (s instanceof Saveable)
        {
            Saveable saveable = (Saveable) s;
            saveable.save();
        }
        getCurrentSession().flush();
    }

    public void merge(ObstructionState detached) {
        ObstructionState persistent = getCurrentSession().get(AbstractObstructionState.SimpleObstructionState.class, detached.getPosition());
        if (persistent != null) {
            merge(persistent, detached);
            getCurrentSession().save(persistent);
        } else {
            getCurrentSession().save(detached);
        }
        getCurrentSession().flush();
    }

    private void merge(ObstructionState persistent, ObstructionState detached) {
        ((AbstractObstructionState) persistent).merge(detached);
    }

}

