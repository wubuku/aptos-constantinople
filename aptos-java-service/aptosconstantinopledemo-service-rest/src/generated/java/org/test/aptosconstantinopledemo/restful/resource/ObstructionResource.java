// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.test.aptosconstantinopledemo.restful.resource;

import java.util.*;
import java.util.stream.*;
import javax.servlet.http.*;
import javax.validation.constraints.*;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.dddml.support.criterion.*;
import org.test.aptosconstantinopledemo.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.test.aptosconstantinopledemo.specialization.*;
import org.test.aptosconstantinopledemo.domain.obstruction.*;
import static org.test.aptosconstantinopledemo.domain.meta.M.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.dddml.support.criterion.TypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping(path = "Obstructions", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ObstructionResource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ObstructionApplicationService obstructionApplicationService;


    /**
     * Retrieve.
     * Retrieve Obstructions
     */
    @GetMapping
    @Transactional(readOnly = true)
    public ObstructionStateDto[] getAll( HttpServletRequest request,
                    @RequestParam(value = "sort", required = false) String sort,
                    @RequestParam(value = "fields", required = false) String fields,
                    @RequestParam(value = "firstResult", defaultValue = "0") Integer firstResult,
                    @RequestParam(value = "maxResults", defaultValue = "2147483647") Integer maxResults,
                    @RequestParam(value = "filter", required = false) String filter) {
        try {
        if (firstResult < 0) { firstResult = 0; }
        if (maxResults == null || maxResults < 1) { maxResults = Integer.MAX_VALUE; }

            Iterable<ObstructionState> states = null; 
            CriterionDto criterion = null;
            if (!StringHelper.isNullOrEmpty(filter)) {
                criterion = new ObjectMapper().readValue(filter, CriterionDto.class);
            } else {
                criterion = QueryParamUtils.getQueryCriterionDto(request.getParameterMap().entrySet().stream()
                    .filter(kv -> ObstructionResourceUtils.getFilterPropertyName(kv.getKey()) != null)
                    .collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue())));
            }
            Criterion c = CriterionDto.toSubclass(criterion, getCriterionTypeConverter(), getPropertyTypeResolver(), 
                n -> (ObstructionMetadata.aliasMap.containsKey(n) ? ObstructionMetadata.aliasMap.get(n) : n));
            states = obstructionApplicationService.get(
                c,
                ObstructionResourceUtils.getQuerySorts(request.getParameterMap()),
                firstResult, maxResults);

            ObstructionStateDto.DtoConverter dtoConverter = new ObstructionStateDto.DtoConverter();
            if (StringHelper.isNullOrEmpty(fields)) {
                dtoConverter.setAllFieldsReturned(true);
            } else {
                dtoConverter.setReturnedFieldsString(fields);
            }
            return dtoConverter.toObstructionStateDtoArray(states);

        } catch (Exception ex) { logger.info(ex.getMessage(), ex); throw DomainErrorUtils.convertException(ex); }
    }

    /**
     * Retrieve in pages.
     * Retrieve Obstructions in pages.
     */
    @GetMapping("_page")
    @Transactional(readOnly = true)
    public Page<ObstructionStateDto> getPage( HttpServletRequest request,
                    @RequestParam(value = "fields", required = false) String fields,
                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                    @RequestParam(value = "size", defaultValue = "20") Integer size,
                    @RequestParam(value = "filter", required = false) String filter) {
        try {
            Integer firstResult = (page == null ? 0 : page) * (size == null ? 20 : size);
            Integer maxResults = (size == null ? 20 : size);
            Iterable<ObstructionState> states = null; 
            CriterionDto criterion = null;
            if (!StringHelper.isNullOrEmpty(filter)) {
                criterion = new ObjectMapper().readValue(filter, CriterionDto.class);
            } else {
                criterion = QueryParamUtils.getQueryCriterionDto(request.getParameterMap().entrySet().stream()
                    .filter(kv -> ObstructionResourceUtils.getFilterPropertyName(kv.getKey()) != null)
                    .collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue())));
            }
            Criterion c = CriterionDto.toSubclass(criterion, getCriterionTypeConverter(), getPropertyTypeResolver(), 
                n -> (ObstructionMetadata.aliasMap.containsKey(n) ? ObstructionMetadata.aliasMap.get(n) : n));
            states = obstructionApplicationService.get(
                c,
                ObstructionResourceUtils.getQuerySorts(request.getParameterMap()),
                firstResult, maxResults);
            long count = obstructionApplicationService.getCount(c);

            ObstructionStateDto.DtoConverter dtoConverter = new ObstructionStateDto.DtoConverter();
            if (StringHelper.isNullOrEmpty(fields)) {
                dtoConverter.setAllFieldsReturned(true);
            } else {
                dtoConverter.setReturnedFieldsString(fields);
            }
            Page.PageImpl<ObstructionStateDto> statePage =  new Page.PageImpl<>(dtoConverter.toObstructionStateDtoList(states), count);
            statePage.setSize(size);
            statePage.setNumber(page);
            return statePage;

        } catch (Exception ex) { logger.info(ex.getMessage(), ex); throw DomainErrorUtils.convertException(ex); }
    }

    /**
     * Retrieve.
     * Retrieves Obstruction with the specified ID.
     */
    @GetMapping("{position}")
    @Transactional(readOnly = true)
    public ObstructionStateDto get(@PathVariable("position") String position, @RequestParam(value = "fields", required = false) String fields) {
        try {
            Position idObj = ObstructionResourceUtils.parseIdString(position);
            ObstructionState state = obstructionApplicationService.get(idObj);
            if (state == null) { return null; }

            ObstructionStateDto.DtoConverter dtoConverter = new ObstructionStateDto.DtoConverter();
            if (StringHelper.isNullOrEmpty(fields)) {
                dtoConverter.setAllFieldsReturned(true);
            } else {
                dtoConverter.setReturnedFieldsString(fields);
            }
            return dtoConverter.toObstructionStateDto(state);

        } catch (Exception ex) { logger.info(ex.getMessage(), ex); throw DomainErrorUtils.convertException(ex); }
    }

    @GetMapping("_count")
    @Transactional(readOnly = true)
    public long getCount( HttpServletRequest request,
                         @RequestParam(value = "filter", required = false) String filter) {
        try {
            long count = 0;
            CriterionDto criterion = null;
            if (!StringHelper.isNullOrEmpty(filter)) {
                criterion = new ObjectMapper().readValue(filter, CriterionDto.class);
            } else {
                criterion = QueryParamUtils.getQueryCriterionDto(request.getParameterMap());
            }
            Criterion c = CriterionDto.toSubclass(criterion,
                getCriterionTypeConverter(), 
                getPropertyTypeResolver(), 
                n -> (ObstructionMetadata.aliasMap.containsKey(n) ? ObstructionMetadata.aliasMap.get(n) : n));
            count = obstructionApplicationService.getCount(c);
            return count;

        } catch (Exception ex) { logger.info(ex.getMessage(), ex); throw DomainErrorUtils.convertException(ex); }
    }

    @GetMapping("_metadata/filteringFields")
    public List<PropertyMetadataDto> getMetadataFilteringFields() {
        try {

            List<PropertyMetadataDto> filtering = new ArrayList<>();
            ObstructionMetadata.propertyTypeMap.forEach((key, value) -> {
                filtering.add(new PropertyMetadataDto(key, value, true));
            });
            return filtering;

        } catch (Exception ex) { logger.info(ex.getMessage(), ex); throw DomainErrorUtils.convertException(ex); }
    }

    @GetMapping("{position}/_events/{version}")
    @Transactional(readOnly = true)
    public ObstructionEvent getEvent(@PathVariable("position") String position, @PathVariable("version") long version) {
        try {

            Position idObj = ObstructionResourceUtils.parseIdString(position);
            //ObstructionStateEventDtoConverter dtoConverter = getObstructionStateEventDtoConverter();
            return obstructionApplicationService.getEvent(idObj, version);

        } catch (Exception ex) { logger.info(ex.getMessage(), ex); throw DomainErrorUtils.convertException(ex); }
    }

    @GetMapping("{position}/_historyStates/{version}")
    @Transactional(readOnly = true)
    public ObstructionStateDto getHistoryState(@PathVariable("position") String position, @PathVariable("version") long version, @RequestParam(value = "fields", required = false) String fields) {
        try {

            Position idObj = ObstructionResourceUtils.parseIdString(position);
            ObstructionStateDto.DtoConverter dtoConverter = new ObstructionStateDto.DtoConverter();
            if (StringHelper.isNullOrEmpty(fields)) {
                dtoConverter.setAllFieldsReturned(true);
            } else {
                dtoConverter.setReturnedFieldsString(fields);
            }
            return dtoConverter.toObstructionStateDto(obstructionApplicationService.getHistoryState(idObj, version));

        } catch (Exception ex) { logger.info(ex.getMessage(), ex); throw DomainErrorUtils.convertException(ex); }
    }



    //protected  ObstructionStateEventDtoConverter getObstructionStateEventDtoConverter() {
    //    return new ObstructionStateEventDtoConverter();
    //}

    protected TypeConverter getCriterionTypeConverter() {
        return new DefaultTypeConverter();
    }

    protected PropertyTypeResolver getPropertyTypeResolver() {
        return new PropertyTypeResolver() {
            @Override
            public Class resolveTypeByPropertyName(String propertyName) {
                return ObstructionResourceUtils.getFilterPropertyType(propertyName);
            }
        };
    }

    // ////////////////////////////////
 
    public static class ObstructionResourceUtils {

        public static Position parseIdString(String idString) {
            TextFormatter<Position> formatter = ObstructionMetadata.URL_ID_TEXT_FORMATTER;
            return formatter.parse(idString);
        }

        public static void setNullIdOrThrowOnInconsistentIds(String position, org.test.aptosconstantinopledemo.domain.obstruction.ObstructionCommand value) {
            Position idObj = parseIdString(position);
            if (value.getPosition() == null) {
                value.setPosition(idObj);
            } else if (!value.getPosition().equals(idObj)) {
                throw DomainError.named("inconsistentId", "Argument Id %1$s NOT equals body Id %2$s", position, value.getPosition());
            }
        }
    
        public static List<String> getQueryOrders(String str, String separator) {
            return QueryParamUtils.getQueryOrders(str, separator, ObstructionMetadata.aliasMap);
        }

        public static List<String> getQuerySorts(Map<String, String[]> queryNameValuePairs) {
            String[] values = queryNameValuePairs.get("sort");
            return QueryParamUtils.getQuerySorts(values, ObstructionMetadata.aliasMap);
        }

        public static String getFilterPropertyName(String fieldName) {
            if ("sort".equalsIgnoreCase(fieldName)
                    || "firstResult".equalsIgnoreCase(fieldName)
                    || "maxResults".equalsIgnoreCase(fieldName)
                    || "fields".equalsIgnoreCase(fieldName)) {
                return null;
            }
            if (ObstructionMetadata.aliasMap.containsKey(fieldName)) {
                return ObstructionMetadata.aliasMap.get(fieldName);
            }
            return null;
        }

        public static Class getFilterPropertyType(String propertyName) {
            if (ObstructionMetadata.propertyTypeMap.containsKey(propertyName)) {
                String propertyType = ObstructionMetadata.propertyTypeMap.get(propertyName);
                if (!StringHelper.isNullOrEmpty(propertyType)) {
                    if (BoundedContextMetadata.CLASS_MAP.containsKey(propertyType)) {
                        return BoundedContextMetadata.CLASS_MAP.get(propertyType);
                    }
                }
            }
            return String.class;
        }

        public static Iterable<Map.Entry<String, Object>> getQueryFilterMap(Map<String, String[]> queryNameValuePairs) {
            Map<String, Object> filter = new HashMap<>();
            queryNameValuePairs.forEach((key, values) -> {
                if (values.length > 0) {
                    String pName = getFilterPropertyName(key);
                    if (!StringHelper.isNullOrEmpty(pName)) {
                        Class pClass = getFilterPropertyType(pName);
                        filter.put(pName, ApplicationContext.current.getTypeConverter().convertFromString(pClass, values[0]));
                    }
                }
            });
            return filter.entrySet();
        }

        public static ObstructionStateDto[] toObstructionStateDtoArray(Iterable<Position> ids) {
            List<ObstructionStateDto> states = new ArrayList<>();
            ids.forEach(i -> {
                ObstructionStateDto dto = new ObstructionStateDto();
                dto.setPosition(i);
                states.add(dto);
            });
            return states.toArray(new ObstructionStateDto[0]);
        }

    }

}

