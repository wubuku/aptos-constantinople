// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encounter.hibernate;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.hibernate.Session;
import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.SessionFactory;
import org.test.aptosconstantinopledemo.domain.encounter.*;
import org.test.aptosconstantinopledemo.specialization.*;
import org.test.aptosconstantinopledemo.specialization.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

public class HibernateEncounterStateRepository implements EncounterStateRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() { return this.sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    private static final Set<String> readOnlyPropertyPascalCaseNames = new HashSet<String>(Arrays.asList("PlayerId", "IsExistent", "MonsterId", "CatchAttempts", "Version", "OffChainVersion", "CreatedBy", "CreatedAt", "UpdatedBy", "UpdatedAt", "Active", "Deleted"));
    
    private ReadOnlyProxyGenerator readOnlyProxyGenerator;
    
    public ReadOnlyProxyGenerator getReadOnlyProxyGenerator() {
        return readOnlyProxyGenerator;
    }

    public void setReadOnlyProxyGenerator(ReadOnlyProxyGenerator readOnlyProxyGenerator) {
        this.readOnlyProxyGenerator = readOnlyProxyGenerator;
    }

    @Transactional(readOnly = true)
    public EncounterState get(String id, boolean nullAllowed) {
        EncounterState.SqlEncounterState state = (EncounterState.SqlEncounterState)getCurrentSession().get(AbstractEncounterState.SimpleEncounterState.class, id);
        if (!nullAllowed && state == null) {
            state = new AbstractEncounterState.SimpleEncounterState();
            state.setPlayerId(id);
        }
        if (getReadOnlyProxyGenerator() != null && state != null) {
            return (EncounterState) getReadOnlyProxyGenerator().createProxy(state, new Class[]{EncounterState.SqlEncounterState.class}, "getStateReadOnly", readOnlyPropertyPascalCaseNames);
        }
        return state;
    }

    public void save(EncounterState state) {
        EncounterState s = state;
        if (getReadOnlyProxyGenerator() != null) {
            s = (EncounterState) getReadOnlyProxyGenerator().getTarget(state);
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

    public void merge(EncounterState detached) {
        EncounterState persistent = getCurrentSession().get(AbstractEncounterState.SimpleEncounterState.class, detached.getPlayerId());
        if (persistent != null) {
            merge(persistent, detached);
            getCurrentSession().save(persistent);
        } else {
            getCurrentSession().save(detached);
        }
        getCurrentSession().flush();
    }

    private void merge(EncounterState persistent, EncounterState detached) {
        ((AbstractEncounterState) persistent).merge(detached);
    }

}

