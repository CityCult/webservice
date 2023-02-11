package org.citycult.backend.ws.resource.venue;

import org.citycult.backend.ws.ConstantsWS;
import org.citycult.backend.ws.QueryParamHelper;
import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.resource.IRSEntityResource;
import org.citycult.backend.ws.resource.IResponseBuilder;
import org.citycult.datastorage.dao.JpaEntityDaoFactory;
import org.citycult.datastorage.dao.JpaVenueDao;
import org.citycult.datastorage.entity.JpaEvent;
import org.citycult.datastorage.entity.JpaVenue;
import org.citycult.datastorage.util.DateHelper;
import org.slf4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author cpieloth
 */
public abstract class ARSVenue<EVENT extends JpaEvent> implements IResponseBuilder<JpaVenue>, IRSEntityResource {

    protected static final JpaVenueDao venueDAO = JpaEntityDaoFactory.getInstance().getVenueDao();

    protected abstract List<JpaVenue> getAll();

    protected abstract List<EVENT> getEvents(JpaVenue venue, DateHelper.DateRange range);

    protected abstract Logger getLogger();

    protected abstract Response buildEventResponse(List<EVENT> entities);

    @GET
    @Path("/{venue_uid}")
    @Produces(ConstantsWS.CONTENT_TYPE)
    @Override
    public Response rsGetDetails(@PathParam("venue_uid") String strUuid) {
        UUID uid;
        try {
            uid = UUID.fromString(strUuid);
        } catch (Exception e) {
            getLogger().error(ConstantsWS.ERROR_WRONG_ID_FORMAT);
            return ResponseErrors.WRONG_ID_FORMAT;
        }

        final JpaVenue entity = venueDAO.get(uid);

        if (entity != null) {
            return buildResponse(entity);
        } else {
            getLogger().error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }
    }

    @GET
    @Produces(ConstantsWS.CONTENT_TYPE)
    public Response rsGetParameters() {
        return rsGetAll();
    }

    @Override
    public Response rsGetAll() {
        final List<JpaVenue> entities = getAll();
        return buildResponse(entities);
    }

    @GET
    @Path("/{venue_uid}/events")
    @Produces(ConstantsWS.CONTENT_TYPE)
    public Response rsGetVenueEvents(
            @PathParam("venue_uid") String strUuid,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_RANGE) String range,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_START_DATE) String start,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_END_DATE) String end) {
        UUID uid;
        try {
            uid = UUID.fromString(strUuid);
        } catch (Exception e) {
            getLogger().error(ConstantsWS.ERROR_WRONG_ID_FORMAT);
            return ResponseErrors.WRONG_ID_FORMAT;
        }

        if (QueryParamHelper.isSpecified(range)) {
            return getEventsByRangeAsResponse(uid, range);
        } else if (QueryParamHelper.isSpecified(start)
                && QueryParamHelper.isSpecified(end)) {
            return getEventsByDateAsResponse(uid, start, end);
        } else
            return getEventsAsResponse(uid);
    }

    @Override
    public Response buildResponse(JpaVenue entity) {
        if (entity != null) {
            return Response.ok(entity).type(MediaType.APPLICATION_JSON).build();
        } else {
            getLogger().error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }
    }

    @Override
    public Response buildResponse(List<JpaVenue> entities) {
        if (entities != null) {
            final GenericEntity<List<JpaVenue>> genEntities = new GenericEntity<List<JpaVenue>>(
                    entities) {
            };
            return Response.ok(genEntities).build();
        } else {
            getLogger().error(ConstantsWS.ERROR_UNKOWN);
            return ResponseErrors.INTERNAL_SERVER_ERROR;
        }
    }

    private Response getEventsByDateAsResponse(UUID uid, Date startDate,
                                               Date endDate) {
        if (startDate == null || endDate == null) {
            getLogger().error(ConstantsWS.ERROR_WRONG_DATE_FORMAT);
            return ResponseErrors.WRONG_DATE_FORMAT;
        }
        DateHelper.DateRange range = new DateHelper.DateRange(startDate,
                endDate);

        JpaVenue venue = venueDAO.get(uid);
        if (venue == null) {
            getLogger().error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }

        final List<EVENT> entities = getEvents(venue, range);
        if (entities != null) {
            return buildEventResponse(entities);
        } else {
            getLogger().error(ConstantsWS.ERROR_NOT_IMPLEMENTED);
            return ResponseErrors.NOT_IMPLEMENTED;
        }
    }

    private Response getEventsAsResponse(UUID uid) {
        final Date start = new Date(System.currentTimeMillis());
        final Date end = DateHelper.MAX_DATE;
        return getEventsByDateAsResponse(uid, start, end);
    }

    private Response getEventsByDateAsResponse(UUID uid, String start,
                                               String end) {
        Date startDate = DateHelper.parse(start);
        Date endDate = DateHelper.parse(end);
        return getEventsByDateAsResponse(uid, startDate, endDate);
    }

    private Response getEventsByRangeAsResponse(UUID uid, String strRange) {
        DateHelper.DateRange range = QueryParamHelper.getRangeDate(strRange);
        if (range == null) {
            getLogger().error(ConstantsWS.ERROR_RANGE_UNKNOWN);
            return ResponseErrors.RANGE_UNKNOWN;
        }

        return getEventsByDateAsResponse(uid, range.getStart(), range.getEnd());
    }
}
