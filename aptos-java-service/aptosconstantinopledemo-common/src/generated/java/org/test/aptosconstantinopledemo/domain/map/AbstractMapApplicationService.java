// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.map;

import java.util.*;
import java.util.function.Consumer;
import org.dddml.support.criterion.Criterion;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.*;

public abstract class AbstractMapApplicationService implements MapApplicationService {

    private EventStore eventStore;

    protected EventStore getEventStore()
    {
        return eventStore;
    }

    private MapStateRepository stateRepository;

    protected MapStateRepository getStateRepository() {
        return stateRepository;
    }

    private MapStateQueryRepository stateQueryRepository;

    protected MapStateQueryRepository getStateQueryRepository() {
        return stateQueryRepository;
    }

    private AggregateEventListener<MapAggregate, MapState> aggregateEventListener;

    public AggregateEventListener<MapAggregate, MapState> getAggregateEventListener() {
        return aggregateEventListener;
    }

    public void setAggregateEventListener(AggregateEventListener<MapAggregate, MapState> eventListener) {
        this.aggregateEventListener = eventListener;
    }

    public AbstractMapApplicationService(EventStore eventStore, MapStateRepository stateRepository, MapStateQueryRepository stateQueryRepository) {
        this.eventStore = eventStore;
        this.stateRepository = stateRepository;
        this.stateQueryRepository = stateQueryRepository;
    }

    public MapState get(String id) {
        MapState state = getStateRepository().get(id, true);
        return state;
    }

    public Iterable<MapState> getAll(Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getAll(firstResult, maxResults);
    }

    public Iterable<MapState> get(Iterable<Map.Entry<String, Object>> filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<MapState> get(Criterion filter, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().get(filter, orders, firstResult, maxResults);
    }

    public Iterable<MapState> getByProperty(String propertyName, Object propertyValue, List<String> orders, Integer firstResult, Integer maxResults) {
        return getStateQueryRepository().getByProperty(propertyName, propertyValue, orders, firstResult, maxResults);
    }

    public long getCount(Iterable<Map.Entry<String, Object>> filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public long getCount(Criterion filter) {
        return getStateQueryRepository().getCount(filter);
    }

    public MapEvent getEvent(String accountAddress, long version) {
        MapEvent e = (MapEvent)getEventStore().getEvent(toEventStoreAggregateId(accountAddress), version);
        if (e != null) {
            ((MapEvent.SqlMapEvent)e).setEventReadOnly(true); 
        } else if (version == -1) {
            return getEvent(accountAddress, 0);
        }
        return e;
    }

    public MapState getHistoryState(String accountAddress, long version) {
        EventStream eventStream = getEventStore().loadEventStream(AbstractMapEvent.class, toEventStoreAggregateId(accountAddress), version - 1);
        return new AbstractMapState.SimpleMapState(eventStream.getEvents());
    }


    public MapAggregate getMapAggregate(MapState state) {
        return new AbstractMapAggregate.SimpleMapAggregate(state);
    }

    public EventStoreAggregateId toEventStoreAggregateId(String aggregateId) {
        return new EventStoreAggregateId.SimpleEventStoreAggregateId(aggregateId);
    }

    protected void update(MapCommand c, Consumer<MapAggregate> action) {
        String aggregateId = c.getAccountAddress();
        EventStoreAggregateId eventStoreAggregateId = toEventStoreAggregateId(aggregateId);
        MapState state = getStateRepository().get(aggregateId, false);
        boolean duplicate = isDuplicateCommand(c, eventStoreAggregateId, state);
        if (duplicate) { return; }

        MapAggregate aggregate = getMapAggregate(state);
        aggregate.throwOnInvalidStateTransition(c);
        action.accept(aggregate);
        persist(eventStoreAggregateId, c.getOffChainVersion() == null ? MapState.VERSION_NULL : c.getOffChainVersion(), aggregate, state); // State version may be null!

    }

    private void persist(EventStoreAggregateId eventStoreAggregateId, long version, MapAggregate aggregate, MapState state) {
        getEventStore().appendEvents(eventStoreAggregateId, version, 
            aggregate.getChanges(), (events) -> { 
                getStateRepository().save(state); 
            });
        if (aggregateEventListener != null) {
            aggregateEventListener.eventAppended(new AggregateEvent<>(aggregate, state, aggregate.getChanges()));
        }
    }

    protected boolean isDuplicateCommand(MapCommand command, EventStoreAggregateId eventStoreAggregateId, MapState state) {
        boolean duplicate = false;
        if (command.getOffChainVersion() == null) { command.setOffChainVersion(MapState.VERSION_NULL); }
        if (state.getOffChainVersion() != null && state.getOffChainVersion() > command.getOffChainVersion()) {
            Event lastEvent = getEventStore().getEvent(AbstractMapEvent.class, eventStoreAggregateId, command.getOffChainVersion());
            if (lastEvent != null && lastEvent instanceof AbstractEvent
               && command.getCommandId() != null && command.getCommandId().equals(((AbstractEvent) lastEvent).getCommandId())) {
                duplicate = true;
            }
        }
        return duplicate;
    }

    public static class SimpleMapApplicationService extends AbstractMapApplicationService {
        public SimpleMapApplicationService(EventStore eventStore, MapStateRepository stateRepository, MapStateQueryRepository stateQueryRepository)
        {
            super(eventStore, stateRepository, stateQueryRepository);
        }
    }

}

