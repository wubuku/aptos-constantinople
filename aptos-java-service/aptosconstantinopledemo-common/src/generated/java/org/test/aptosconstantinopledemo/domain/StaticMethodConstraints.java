// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain;

import org.test.aptosconstantinopledemo.specialization.ReflectUtils;
import org.test.aptosconstantinopledemo.specialization.MutationContext;
import org.test.aptosconstantinopledemo.specialization.VerificationContext;
import org.test.aptosconstantinopledemo.domain.player.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.domain.movable.*;
import org.test.aptosconstantinopledemo.domain.encounterable.*;
import org.test.aptosconstantinopledemo.domain.monster.*;
import org.test.aptosconstantinopledemo.domain.playerposition.*;
import org.test.aptosconstantinopledemo.domain.encounter.*;
import org.test.aptosconstantinopledemo.domain.ownedmonsters.*;
import org.test.aptosconstantinopledemo.domain.obstruction.*;
import org.test.aptosconstantinopledemo.domain.encountertrigger.*;
import org.test.aptosconstantinopledemo.domain.randomseed.*;
import org.test.aptosconstantinopledemo.domain.map.*;

public class StaticMethodConstraints {

    public static void assertStaticVerificationAndMutationMethods() {

        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.player.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlayerState.class, Boolean.class, VerificationContext.class},
                    new String[]{"_", "_", "value"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.movable.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, MovableState.class, Boolean.class, VerificationContext.class},
                    new String[]{"_", "_", "value"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounterable.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, EncounterableState.class, Boolean.class, VerificationContext.class},
                    new String[]{"_", "_", "value"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.monster.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, MonsterState.class, BigInteger.class, VerificationContext.class},
                    new String[]{"_", "_", "monsterType"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.monster.DeleteLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, MonsterState.class, VerificationContext.class},
                    new String[]{"_", "_"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.playerposition.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlayerPositionState.class, Position.class, VerificationContext.class},
                    new String[]{"_", "_", "position"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.playerposition.UpdateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, PlayerPositionState.class, Position.class, VerificationContext.class},
                    new String[]{"_", "_", "position"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounter.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, EncounterState.class, Boolean.class, String.class, BigInteger.class, VerificationContext.class},
                    new String[]{"_", "_", "isExistent", "monsterId", "catchAttempts"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounter.UpdateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, EncounterState.class, Boolean.class, String.class, BigInteger.class, VerificationContext.class},
                    new String[]{"_", "_", "isExistent", "monsterId", "catchAttempts"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounter.DeleteLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, EncounterState.class, VerificationContext.class},
                    new String[]{"_", "_"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.ownedmonsters.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, OwnedMonstersState.class, String[].class, VerificationContext.class},
                    new String[]{"_", "_", "monsters"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.ownedmonsters.AddMonsterLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, OwnedMonstersState.class, String.class, VerificationContext.class},
                    new String[]{"_", "_", "monsterId"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.obstruction.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, ObstructionState.class, Boolean.class, VerificationContext.class},
                    new String[]{"_", "_", "value"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encountertrigger.CreateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, EncounterTriggerState.class, Boolean.class, VerificationContext.class},
                    new String[]{"_", "_", "value"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.randomseed.__Init__Logic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, RandomSeedState.class, VerificationContext.class},
                    new String[]{"_", "_"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.randomseed.UpdateLogic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, RandomSeedState.class, BigInteger.class, VerificationContext.class},
                    new String[]{"_", "_", "value"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.map.__Init__Logic",
                    "verify",
                    new Class[]{java.util.function.Supplier.class, MapState.class, VerificationContext.class},
                    new String[]{"_", "_"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.player.CreateLogic",
                    "mutate",
                    new Class[]{PlayerState.class, Boolean.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "value", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.movable.CreateLogic",
                    "mutate",
                    new Class[]{MovableState.class, Boolean.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "value", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounterable.CreateLogic",
                    "mutate",
                    new Class[]{EncounterableState.class, Boolean.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "value", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.monster.CreateLogic",
                    "mutate",
                    new Class[]{MonsterState.class, BigInteger.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "monsterType", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.monster.DeleteLogic",
                    "mutate",
                    new Class[]{MonsterState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.playerposition.CreateLogic",
                    "mutate",
                    new Class[]{PlayerPositionState.class, Position.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "position", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.playerposition.UpdateLogic",
                    "mutate",
                    new Class[]{PlayerPositionState.class, Position.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "position", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounter.CreateLogic",
                    "mutate",
                    new Class[]{EncounterState.class, Boolean.class, String.class, BigInteger.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "isExistent", "monsterId", "catchAttempts", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounter.UpdateLogic",
                    "mutate",
                    new Class[]{EncounterState.class, Boolean.class, String.class, BigInteger.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "isExistent", "monsterId", "catchAttempts", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encounter.DeleteLogic",
                    "mutate",
                    new Class[]{EncounterState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.ownedmonsters.CreateLogic",
                    "mutate",
                    new Class[]{OwnedMonstersState.class, String[].class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "monsters", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.ownedmonsters.AddMonsterLogic",
                    "mutate",
                    new Class[]{OwnedMonstersState.class, String.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "monsterId", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.obstruction.CreateLogic",
                    "mutate",
                    new Class[]{ObstructionState.class, Boolean.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "value", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.encountertrigger.CreateLogic",
                    "mutate",
                    new Class[]{EncounterTriggerState.class, Boolean.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "value", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.randomseed.__Init__Logic",
                    "mutate",
                    new Class[]{RandomSeedState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.randomseed.UpdateLogic",
                    "mutate",
                    new Class[]{RandomSeedState.class, BigInteger.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "value", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );


        ReflectUtils.assertStaticMethodIfClassExists(
                    "org.test.aptosconstantinopledemo.domain.map.__Init__Logic",
                    "mutate",
                    new Class[]{MapState.class, BigInteger.class, BigInteger.class, String.class, AptosEventGuid.class, String.class, MutationContext.class},
                    new String[]{"_", "aptosEventVersion", "aptosEventSequenceNumber", "aptosEventType", "aptosEventGuid", "status"}
            );



    }

}


