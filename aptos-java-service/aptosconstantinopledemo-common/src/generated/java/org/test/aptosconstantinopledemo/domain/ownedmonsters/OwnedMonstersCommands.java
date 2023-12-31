// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.ownedmonsters;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;

public class OwnedMonstersCommands {
    private OwnedMonstersCommands() {
    }

    public static class Create extends AbstractOwnedMonstersCommand implements OwnedMonstersCommand {

        public String getCommandType() {
            return "Create";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Player Id
         */
        private String playerId;

        public String getPlayerId() {
            return this.playerId;
        }

        public void setPlayerId(String playerId) {
            this.playerId = playerId;
        }

        /**
         * Monsters
         */
        private String[] monsters;

        public String[] getMonsters() {
            return this.monsters;
        }

        public void setMonsters(String[] monsters) {
            this.monsters = monsters;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

    public static class AddMonster extends AbstractOwnedMonstersCommand implements OwnedMonstersCommand {

        public String getCommandType() {
            return "AddMonster";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Player Id
         */
        private String playerId;

        public String getPlayerId() {
            return this.playerId;
        }

        public void setPlayerId(String playerId) {
            this.playerId = playerId;
        }

        /**
         * Monster Id
         */
        private String monsterId;

        public String getMonsterId() {
            return this.monsterId;
        }

        public void setMonsterId(String monsterId) {
            this.monsterId = monsterId;
        }

        /**
         * Off Chain Version
         */
        private Long offChainVersion;

        public Long getOffChainVersion() {
            return this.offChainVersion;
        }

        public void setOffChainVersion(Long offChainVersion) {
            this.offChainVersion = offChainVersion;
        }

    }

}

