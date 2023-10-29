// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.obstruction;

import java.util.*;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;

public class ObstructionCommands {
    private ObstructionCommands() {
    }

    public static class Create extends AbstractObstructionCommand implements ObstructionCommand {

        public String getCommandType() {
            return "Create";
        }

        public void setCommandType(String commandType) {
            //do nothing
        }

        /**
         * Position
         */
        private Position position;

        public Position getPosition() {
            return this.position;
        }

        public void setPosition(Position position) {
            this.position = position;
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

