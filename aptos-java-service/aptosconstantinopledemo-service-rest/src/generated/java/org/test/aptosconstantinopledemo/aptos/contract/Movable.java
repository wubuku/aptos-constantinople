// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.aptos.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.wubuku.aptos.bean.*;

import java.math.*;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Movable {

    private String playerId;

    private Long offChainVersion;

    private Boolean value;

    private BigInteger version;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Long getOffChainVersion() {
        return offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion) {
        this.offChainVersion = offChainVersion;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Movable{" +
                ", playerId=" + '\'' + playerId + '\'' +
                ", offChainVersion=" + offChainVersion +
                ", value=" + value +
                ", version=" + version +
                '}';
    }

    public static class Tables {
        @JsonProperty("movable_table")
        private Table movableTable;

        public Table getMovableTable() {
            return movableTable;
        }

        public void setMovableTable(Table movableTable) {
            this.movableTable = movableTable;
        }

        @Override
        public String toString() {
            return "Movable.Tables{" +
                    "movableTable=" + movableTable +
                    '}';
        }
    }

}
