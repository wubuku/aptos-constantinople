// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.domain.encounter;

import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.domain.*;
import org.test.aptosconstantinopledemo.specialization.*;


public class EncounterStateDto {

    private String playerId;

    public String getPlayerId()
    {
        return this.playerId;
    }

    public void setPlayerId(String playerId)
    {
        this.playerId = playerId;
    }

    private Boolean isExistent;

    public Boolean getIsExistent()
    {
        return this.isExistent;
    }

    public void setIsExistent(Boolean isExistent)
    {
        this.isExistent = isExistent;
    }

    private String monsterId;

    public String getMonsterId()
    {
        return this.monsterId;
    }

    public void setMonsterId(String monsterId)
    {
        this.monsterId = monsterId;
    }

    private BigInteger catchAttempts;

    public BigInteger getCatchAttempts()
    {
        return this.catchAttempts;
    }

    public void setCatchAttempts(BigInteger catchAttempts)
    {
        this.catchAttempts = catchAttempts;
    }

    private BigInteger version;

    public BigInteger getVersion()
    {
        return this.version;
    }

    public void setVersion(BigInteger version)
    {
        this.version = version;
    }

    private Boolean active;

    public Boolean getActive()
    {
        return this.active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    private Long offChainVersion;

    public Long getOffChainVersion()
    {
        return this.offChainVersion;
    }

    public void setOffChainVersion(Long offChainVersion)
    {
        this.offChainVersion = offChainVersion;
    }

    private String createdBy;

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    private Date createdAt;

    public Date getCreatedAt()
    {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    private String updatedBy;

    public String getUpdatedBy()
    {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy)
    {
        this.updatedBy = updatedBy;
    }

    private Date updatedAt;

    public Date getUpdatedAt()
    {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }


    public static class DtoConverter extends AbstractStateDtoConverter
    {
        public static Collection<String> collectionFieldNames = Arrays.asList(new String[]{});

        @Override
        protected boolean isCollectionField(String fieldName) {
            return CollectionUtils.collectionContainsIgnoringCase(collectionFieldNames, fieldName);
        }

        public EncounterStateDto[] toEncounterStateDtoArray(Iterable<EncounterState> states) {
            return toEncounterStateDtoList(states).toArray(new EncounterStateDto[0]);
        }

        public List<EncounterStateDto> toEncounterStateDtoList(Iterable<EncounterState> states) {
            ArrayList<EncounterStateDto> stateDtos = new ArrayList();
            for (EncounterState s : states) {
                EncounterStateDto dto = toEncounterStateDto(s);
                stateDtos.add(dto);
            }
            return stateDtos;
        }

        public EncounterStateDto toEncounterStateDto(EncounterState state)
        {
            if(state == null) {
                return null;
            }
            EncounterStateDto dto = new EncounterStateDto();
            if (returnedFieldsContains("PlayerId")) {
                dto.setPlayerId(state.getPlayerId());
            }
            if (returnedFieldsContains("IsExistent")) {
                dto.setIsExistent(state.getIsExistent());
            }
            if (returnedFieldsContains("MonsterId")) {
                dto.setMonsterId(state.getMonsterId());
            }
            if (returnedFieldsContains("CatchAttempts")) {
                dto.setCatchAttempts(state.getCatchAttempts());
            }
            if (returnedFieldsContains("Version")) {
                dto.setVersion(state.getVersion());
            }
            if (returnedFieldsContains("Active")) {
                dto.setActive(state.getActive());
            }
            if (returnedFieldsContains("OffChainVersion")) {
                dto.setOffChainVersion(state.getOffChainVersion());
            }
            if (returnedFieldsContains("CreatedBy")) {
                dto.setCreatedBy(state.getCreatedBy());
            }
            if (returnedFieldsContains("CreatedAt")) {
                dto.setCreatedAt(state.getCreatedAt());
            }
            if (returnedFieldsContains("UpdatedBy")) {
                dto.setUpdatedBy(state.getUpdatedBy());
            }
            if (returnedFieldsContains("UpdatedAt")) {
                dto.setUpdatedAt(state.getUpdatedAt());
            }
            return dto;
        }

    }
}

