// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.player;

import java.util.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;

public class PlayerCommands {
    private PlayerCommands() {
    }

    public static class Create extends AbstractPlayerCommand implements PlayerCommand {

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
         * Value
         */
        private Boolean value;

        public Boolean getValue() {
            return this.value;
        }

        public void setValue(Boolean value) {
            this.value = value;
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
