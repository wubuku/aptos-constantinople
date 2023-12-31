// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encounter;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;

public class EncounterCommands {
    private EncounterCommands() {
    }

    public static class Create extends AbstractEncounterCommand implements EncounterCommand {

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
         * Is Existent
         */
        private Boolean isExistent;

        public Boolean getIsExistent() {
            return this.isExistent;
        }

        public void setIsExistent(Boolean isExistent) {
            this.isExistent = isExistent;
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
         * Catch Attempts
         */
        private BigInteger catchAttempts;

        public BigInteger getCatchAttempts() {
            return this.catchAttempts;
        }

        public void setCatchAttempts(BigInteger catchAttempts) {
            this.catchAttempts = catchAttempts;
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

    public static class Update extends AbstractEncounterCommand implements EncounterCommand {

        public String getCommandType() {
            return "Update";
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
         * Is Existent
         */
        private Boolean isExistent;

        public Boolean getIsExistent() {
            return this.isExistent;
        }

        public void setIsExistent(Boolean isExistent) {
            this.isExistent = isExistent;
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
         * Catch Attempts
         */
        private BigInteger catchAttempts;

        public BigInteger getCatchAttempts() {
            return this.catchAttempts;
        }

        public void setCatchAttempts(BigInteger catchAttempts) {
            this.catchAttempts = catchAttempts;
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

    public static class Delete extends AbstractEncounterCommand implements EncounterCommand {

        public String getCommandType() {
            return "Delete";
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

