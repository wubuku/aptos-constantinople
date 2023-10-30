// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.ownedmonsters.hibernate;

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
import org.test.aptosconstantinopledemo.domain.ownedmonsters.*;
import org.test.aptosconstantinopledemo.specialization.*;
import org.test.aptosconstantinopledemo.specialization.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

public class HibernateOwnedMonstersStateRepository implements OwnedMonstersStateRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() { return this.sessionFactory; }

    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    private static final Set<String> readOnlyPropertyPascalCaseNames = new HashSet<String>(Arrays.asList("PlayerId", "Monsters", "Version", "OffChainVersion", "CreatedBy", "CreatedAt", "UpdatedBy", "UpdatedAt", "Active", "Deleted"));
    
    private ReadOnlyProxyGenerator readOnlyProxyGenerator;
    
    public ReadOnlyProxyGenerator getReadOnlyProxyGenerator() {
        return readOnlyProxyGenerator;
    }

    public void setReadOnlyProxyGenerator(ReadOnlyProxyGenerator readOnlyProxyGenerator) {
        this.readOnlyProxyGenerator = readOnlyProxyGenerator;
    }

    @Transactional(readOnly = true)
    public OwnedMonstersState get(String id, boolean nullAllowed) {
        OwnedMonstersState.SqlOwnedMonstersState state = (OwnedMonstersState.SqlOwnedMonstersState)getCurrentSession().get(AbstractOwnedMonstersState.SimpleOwnedMonstersState.class, id);
        if (!nullAllowed && state == null) {
            state = new AbstractOwnedMonstersState.SimpleOwnedMonstersState();
            state.setPlayerId(id);
        }
        if (getReadOnlyProxyGenerator() != null && state != null) {
            return (OwnedMonstersState) getReadOnlyProxyGenerator().createProxy(state, new Class[]{OwnedMonstersState.SqlOwnedMonstersState.class}, "getStateReadOnly", readOnlyPropertyPascalCaseNames);
        }
        return state;
    }

    public void save(OwnedMonstersState state) {
        OwnedMonstersState s = state;
        if (getReadOnlyProxyGenerator() != null) {
            s = (OwnedMonstersState) getReadOnlyProxyGenerator().getTarget(state);
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

    public void merge(OwnedMonstersState detached) {
        OwnedMonstersState persistent = getCurrentSession().get(AbstractOwnedMonstersState.SimpleOwnedMonstersState.class, detached.getPlayerId());
        if (persistent != null) {
            merge(persistent, detached);
            getCurrentSession().merge(detached);
        } else {
            getCurrentSession().save(detached);
        }
        getCurrentSession().flush();
    }

    private void merge(OwnedMonstersState persistent, OwnedMonstersState detached) {
        ((OwnedMonstersState.MutableOwnedMonstersState) detached).setOffChainVersion(persistent.getOffChainVersion());
    }

}
