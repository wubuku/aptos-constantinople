// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract;

import java.math.*;

import com.github.wubuku.aptos.bean.Event;
import com.github.wubuku.aptos.bean.Option;
import org.test.aptosconstantinopledemo.domain.AptosEvent;
import org.test.aptosconstantinopledemo.domain.AptosEventGuid;
import org.test.aptosconstantinopledemo.domain.player.AbstractPlayerEvent;
import org.test.aptosconstantinopledemo.aptos.contract.player.PlayerCreated;
import org.test.aptosconstantinopledemo.domain.movable.AbstractMovableEvent;
import org.test.aptosconstantinopledemo.aptos.contract.movable.MovableCreated;
import org.test.aptosconstantinopledemo.domain.encounterable.AbstractEncounterableEvent;
import org.test.aptosconstantinopledemo.aptos.contract.encounterable.EncounterableCreated;
import org.test.aptosconstantinopledemo.domain.monster.AbstractMonsterEvent;
import org.test.aptosconstantinopledemo.aptos.contract.monster.MonsterCreated;
import org.test.aptosconstantinopledemo.aptos.contract.monster.MonsterDeleted;
import org.test.aptosconstantinopledemo.domain.playerposition.AbstractPlayerPositionEvent;
import org.test.aptosconstantinopledemo.aptos.contract.playerposition.PlayerPositionCreated;
import org.test.aptosconstantinopledemo.aptos.contract.playerposition.PlayerPositionUpdated;
import org.test.aptosconstantinopledemo.domain.encounter.AbstractEncounterEvent;
import org.test.aptosconstantinopledemo.aptos.contract.encounter.EncounterCreated;
import org.test.aptosconstantinopledemo.aptos.contract.encounter.EncounterUpdated;
import org.test.aptosconstantinopledemo.aptos.contract.encounter.EncounterDeleted;
import org.test.aptosconstantinopledemo.domain.ownedmonsters.AbstractOwnedMonstersEvent;
import org.test.aptosconstantinopledemo.aptos.contract.ownedmonsters.OwnedMonstersCreated;
import org.test.aptosconstantinopledemo.aptos.contract.ownedmonsters.MonsterAddedToPlayer;
import org.test.aptosconstantinopledemo.domain.obstruction.AbstractObstructionEvent;
import org.test.aptosconstantinopledemo.aptos.contract.obstruction.ObstructionCreated;
import org.test.aptosconstantinopledemo.domain.encountertrigger.AbstractEncounterTriggerEvent;
import org.test.aptosconstantinopledemo.aptos.contract.encountertrigger.EncounterTriggerCreated;
import org.test.aptosconstantinopledemo.domain.randomseed.AbstractRandomSeedEvent;
import org.test.aptosconstantinopledemo.aptos.contract.randomseed.RandomSeedInitialized;
import org.test.aptosconstantinopledemo.aptos.contract.randomseed.RandomSeedUpdated;
import org.test.aptosconstantinopledemo.domain.map.AbstractMapEvent;
import org.test.aptosconstantinopledemo.aptos.contract.map.MapInitialized;

/**
 * Utils that convert beans in the contract package to domain beans.
 */
public class DomainBeanUtils {
    private DomainBeanUtils() {
    }

    public static org.test.aptosconstantinopledemo.domain.Coin toCoin(Coin contractCoin) {
        if (contractCoin == null) {
            return null;
        }
        org.test.aptosconstantinopledemo.domain.Coin coin = new org.test.aptosconstantinopledemo.domain.Coin();
        coin.setValue(contractCoin.getValue());
        return coin;
    }

    public static org.test.aptosconstantinopledemo.domain.Position toPosition(Position contractPosition) {
        if (contractPosition == null) {
            return null;
        }
        org.test.aptosconstantinopledemo.domain.Position position = new org.test.aptosconstantinopledemo.domain.Position();
        position.setX(contractPosition.getX());
        position.setY(contractPosition.getY());
        return position;
    }


    public static AbstractPlayerEvent.PlayerCreated toPlayerCreated(Event<PlayerCreated> eventEnvelope) {
        PlayerCreated contractEvent = eventEnvelope.getData();

        AbstractPlayerEvent.PlayerCreated playerCreated = new AbstractPlayerEvent.PlayerCreated();
        playerCreated.setPlayerId(contractEvent.getPlayerId());
        playerCreated.setValue(contractEvent.getValue());
        playerCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(playerCreated, eventEnvelope);

        return playerCreated;
    }

    public static AbstractMovableEvent.MovableCreated toMovableCreated(Event<MovableCreated> eventEnvelope) {
        MovableCreated contractEvent = eventEnvelope.getData();

        AbstractMovableEvent.MovableCreated movableCreated = new AbstractMovableEvent.MovableCreated();
        movableCreated.setPlayerId(contractEvent.getPlayerId());
        movableCreated.setValue(contractEvent.getValue());
        movableCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(movableCreated, eventEnvelope);

        return movableCreated;
    }

    public static AbstractEncounterableEvent.EncounterableCreated toEncounterableCreated(Event<EncounterableCreated> eventEnvelope) {
        EncounterableCreated contractEvent = eventEnvelope.getData();

        AbstractEncounterableEvent.EncounterableCreated encounterableCreated = new AbstractEncounterableEvent.EncounterableCreated();
        encounterableCreated.setPlayerId(contractEvent.getPlayerId());
        encounterableCreated.setValue(contractEvent.getValue());
        encounterableCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(encounterableCreated, eventEnvelope);

        return encounterableCreated;
    }

    public static AbstractMonsterEvent.MonsterCreated toMonsterCreated(Event<MonsterCreated> eventEnvelope) {
        MonsterCreated contractEvent = eventEnvelope.getData();

        AbstractMonsterEvent.MonsterCreated monsterCreated = new AbstractMonsterEvent.MonsterCreated();
        monsterCreated.setMonsterId(contractEvent.getMonsterId());
        monsterCreated.setMonsterType(contractEvent.getMonsterType());
        monsterCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(monsterCreated, eventEnvelope);

        return monsterCreated;
    }

    public static AbstractMonsterEvent.MonsterDeleted toMonsterDeleted(Event<MonsterDeleted> eventEnvelope) {
        MonsterDeleted contractEvent = eventEnvelope.getData();

        AbstractMonsterEvent.MonsterDeleted monsterDeleted = new AbstractMonsterEvent.MonsterDeleted();
        monsterDeleted.setMonsterId(contractEvent.getMonsterId());
        monsterDeleted.setVersion(contractEvent.getVersion());

        setAptosEventProperties(monsterDeleted, eventEnvelope);

        return monsterDeleted;
    }

    public static AbstractPlayerPositionEvent.PlayerPositionCreated toPlayerPositionCreated(Event<PlayerPositionCreated> eventEnvelope) {
        PlayerPositionCreated contractEvent = eventEnvelope.getData();

        AbstractPlayerPositionEvent.PlayerPositionCreated playerPositionCreated = new AbstractPlayerPositionEvent.PlayerPositionCreated();
        playerPositionCreated.setPlayerId(contractEvent.getPlayerId());
        playerPositionCreated.setPosition(DomainBeanUtils.toPosition(contractEvent.getPosition()));
        playerPositionCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(playerPositionCreated, eventEnvelope);

        return playerPositionCreated;
    }

    public static AbstractPlayerPositionEvent.PlayerPositionUpdated toPlayerPositionUpdated(Event<PlayerPositionUpdated> eventEnvelope) {
        PlayerPositionUpdated contractEvent = eventEnvelope.getData();

        AbstractPlayerPositionEvent.PlayerPositionUpdated playerPositionUpdated = new AbstractPlayerPositionEvent.PlayerPositionUpdated();
        playerPositionUpdated.setPlayerId(contractEvent.getPlayerId());
        playerPositionUpdated.setPosition(DomainBeanUtils.toPosition(contractEvent.getPosition()));
        playerPositionUpdated.setVersion(contractEvent.getVersion());

        setAptosEventProperties(playerPositionUpdated, eventEnvelope);

        return playerPositionUpdated;
    }

    public static AbstractEncounterEvent.EncounterCreated toEncounterCreated(Event<EncounterCreated> eventEnvelope) {
        EncounterCreated contractEvent = eventEnvelope.getData();

        AbstractEncounterEvent.EncounterCreated encounterCreated = new AbstractEncounterEvent.EncounterCreated();
        encounterCreated.setPlayerId(contractEvent.getPlayerId());
        encounterCreated.setIsExistent(contractEvent.getIsExistent());
        encounterCreated.setMonsterId(contractEvent.getMonsterId());
        encounterCreated.setCatchAttempts(contractEvent.getCatchAttempts());
        encounterCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(encounterCreated, eventEnvelope);

        return encounterCreated;
    }

    public static AbstractEncounterEvent.EncounterUpdated toEncounterUpdated(Event<EncounterUpdated> eventEnvelope) {
        EncounterUpdated contractEvent = eventEnvelope.getData();

        AbstractEncounterEvent.EncounterUpdated encounterUpdated = new AbstractEncounterEvent.EncounterUpdated();
        encounterUpdated.setPlayerId(contractEvent.getPlayerId());
        encounterUpdated.setIsExistent(contractEvent.getIsExistent());
        encounterUpdated.setMonsterId(contractEvent.getMonsterId());
        encounterUpdated.setCatchAttempts(contractEvent.getCatchAttempts());
        encounterUpdated.setVersion(contractEvent.getVersion());

        setAptosEventProperties(encounterUpdated, eventEnvelope);

        return encounterUpdated;
    }

    public static AbstractEncounterEvent.EncounterDeleted toEncounterDeleted(Event<EncounterDeleted> eventEnvelope) {
        EncounterDeleted contractEvent = eventEnvelope.getData();

        AbstractEncounterEvent.EncounterDeleted encounterDeleted = new AbstractEncounterEvent.EncounterDeleted();
        encounterDeleted.setPlayerId(contractEvent.getPlayerId());
        encounterDeleted.setVersion(contractEvent.getVersion());

        setAptosEventProperties(encounterDeleted, eventEnvelope);

        return encounterDeleted;
    }

    public static AbstractOwnedMonstersEvent.OwnedMonstersCreated toOwnedMonstersCreated(Event<OwnedMonstersCreated> eventEnvelope) {
        OwnedMonstersCreated contractEvent = eventEnvelope.getData();

        AbstractOwnedMonstersEvent.OwnedMonstersCreated ownedMonstersCreated = new AbstractOwnedMonstersEvent.OwnedMonstersCreated();
        ownedMonstersCreated.setPlayerId(contractEvent.getPlayerId());
        ownedMonstersCreated.setMonsters(contractEvent.getMonsters());
        ownedMonstersCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(ownedMonstersCreated, eventEnvelope);

        return ownedMonstersCreated;
    }

    public static AbstractOwnedMonstersEvent.MonsterAddedToPlayer toMonsterAddedToPlayer(Event<MonsterAddedToPlayer> eventEnvelope) {
        MonsterAddedToPlayer contractEvent = eventEnvelope.getData();

        AbstractOwnedMonstersEvent.MonsterAddedToPlayer monsterAddedToPlayer = new AbstractOwnedMonstersEvent.MonsterAddedToPlayer();
        monsterAddedToPlayer.setPlayerId(contractEvent.getPlayerId());
        monsterAddedToPlayer.setMonsterId(contractEvent.getMonsterId());
        monsterAddedToPlayer.setVersion(contractEvent.getVersion());

        setAptosEventProperties(monsterAddedToPlayer, eventEnvelope);

        return monsterAddedToPlayer;
    }

    public static AbstractObstructionEvent.ObstructionCreated toObstructionCreated(Event<ObstructionCreated> eventEnvelope) {
        ObstructionCreated contractEvent = eventEnvelope.getData();

        AbstractObstructionEvent.ObstructionCreated obstructionCreated = new AbstractObstructionEvent.ObstructionCreated();
        obstructionCreated.setPosition(DomainBeanUtils.toPosition(contractEvent.getPosition()));
        obstructionCreated.setValue(contractEvent.getValue());
        obstructionCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(obstructionCreated, eventEnvelope);

        return obstructionCreated;
    }

    public static AbstractEncounterTriggerEvent.EncounterTriggerCreated toEncounterTriggerCreated(Event<EncounterTriggerCreated> eventEnvelope) {
        EncounterTriggerCreated contractEvent = eventEnvelope.getData();

        AbstractEncounterTriggerEvent.EncounterTriggerCreated encounterTriggerCreated = new AbstractEncounterTriggerEvent.EncounterTriggerCreated();
        encounterTriggerCreated.setPosition(DomainBeanUtils.toPosition(contractEvent.getPosition()));
        encounterTriggerCreated.setValue(contractEvent.getValue());
        encounterTriggerCreated.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(encounterTriggerCreated, eventEnvelope);

        return encounterTriggerCreated;
    }

    public static AbstractRandomSeedEvent.RandomSeedInitialized toRandomSeedInitialized(Event<RandomSeedInitialized> eventEnvelope) {
        RandomSeedInitialized contractEvent = eventEnvelope.getData();

        AbstractRandomSeedEvent.RandomSeedInitialized randomSeedInitialized = new AbstractRandomSeedEvent.RandomSeedInitialized();
        randomSeedInitialized.setAccountAddress(contractEvent.getAccountAddress());
        randomSeedInitialized.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(randomSeedInitialized, eventEnvelope);

        return randomSeedInitialized;
    }

    public static AbstractRandomSeedEvent.RandomSeedUpdated toRandomSeedUpdated(Event<RandomSeedUpdated> eventEnvelope) {
        RandomSeedUpdated contractEvent = eventEnvelope.getData();

        AbstractRandomSeedEvent.RandomSeedUpdated randomSeedUpdated = new AbstractRandomSeedEvent.RandomSeedUpdated();
        randomSeedUpdated.setAccountAddress(contractEvent.getAccountAddress());
        randomSeedUpdated.setValue(contractEvent.getValue());
        randomSeedUpdated.setVersion(contractEvent.getVersion());

        setAptosEventProperties(randomSeedUpdated, eventEnvelope);

        return randomSeedUpdated;
    }

    public static AbstractMapEvent.MapInitialized toMapInitialized(Event<MapInitialized> eventEnvelope) {
        MapInitialized contractEvent = eventEnvelope.getData();

        AbstractMapEvent.MapInitialized mapInitialized = new AbstractMapEvent.MapInitialized();
        mapInitialized.setAccountAddress(contractEvent.getAccountAddress());
        mapInitialized.setVersion(BigInteger.valueOf(-1));

        setAptosEventProperties(mapInitialized, eventEnvelope);

        return mapInitialized;
    }

    public static void setAptosEventProperties(AptosEvent.MutableAptosEvent domainAptosEvent, Event<?> eventEnvelope) {
        domainAptosEvent.setAptosEventGuid(toAptosEventGuid(eventEnvelope.getGuid()));
        domainAptosEvent.setAptosEventType(eventEnvelope.getType());
        domainAptosEvent.setAptosEventSequenceNumber(new BigInteger(eventEnvelope.getSequenceNumber()));
        domainAptosEvent.setAptosEventVersion(new BigInteger(eventEnvelope.getVersion()));
    }

    public static AptosEventGuid toAptosEventGuid(com.github.wubuku.aptos.bean.Event.Guid eventGuid) {
        return new AptosEventGuid(new BigInteger(eventGuid.getCreationNumber()), eventGuid.getAccountAddress());
    }

    private static <T> T extractOptionalValue(Option<T> optionView) {
        return optionView == null ? null
                : (optionView.getVec() == null || optionView.getVec().size() == 0) ? null
                : optionView.getVec().get(0);
    }
}
