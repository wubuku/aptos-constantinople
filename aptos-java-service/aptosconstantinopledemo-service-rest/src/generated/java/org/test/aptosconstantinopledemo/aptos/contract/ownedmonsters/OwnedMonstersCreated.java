// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract.ownedmonsters;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.test.aptosconstantinopledemo.aptos.contract.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OwnedMonstersCreated {

    private String playerId;

    private String[] monsters;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String[] getMonsters() {
        return monsters;
    }

    public void setMonsters(String[] monsters) {
        this.monsters = monsters;
    }

    @Override
    public String toString() {
        return "OwnedMonstersCreated{" +
                "playerId=" + '\'' + playerId + '\'' +
                ", monsters=" + Arrays.toString(monsters) +
                '}';
    }

}