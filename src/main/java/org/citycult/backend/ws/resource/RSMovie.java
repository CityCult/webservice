package org.citycult.backend.ws.resource;

import org.citycult.backend.ws.ConstantsWS;
import org.citycult.backend.ws.QueryParamHelper;
import org.citycult.backend.ws.ResponseErrors;
import org.citycult.datastorage.dao.JpaEntityDaoFactory;
import org.citycult.datastorage.dao.JpaEventCinemaDao;
import org.citycult.datastorage.dao.JpaMovieDao;
import org.citycult.datastorage.dao.JpaVenueDao;
import org.citycult.datastorage.entity.JpaEventCinema;
import org.citycult.datastorage.entity.JpaMovie;
import org.citycult.datastorage.entity.JpaVenue;
import org.citycult.datastorage.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/movie")
public class RSMovie implements IResponseBuilder<JpaMovie>, IRSEntityResource {

    private static Logger log = LoggerFactory.getLogger(RSMovie.class);

    private static final JpaMovieDao movieDAO = JpaEntityDaoFactory.getInstance().getMovieDao();
    private static final JpaEventCinemaDao cinemaDAO = JpaEntityDaoFactory.getInstance().getEventCinemaDao();
    private static final JpaVenueDao venueDAO = JpaEntityDaoFactory.getInstance().getVenueDao();

    public RSMovie() {
    }

    @GET
    @Produces(ConstantsWS.CONTENT_TYPE)
    public Response rsGetParameters() {
        return rsGetAll();
    }

    @Override
    public Response rsGetAll() {
        List<JpaMovie> entities = movieDAO.getAll();
        return buildResponse(entities);
    }

    @GET
    @Path("/{movie_uid}")
    @Produces(ConstantsWS.CONTENT_TYPE)
    @Override
    public Response rsGetDetails(@PathParam("movie_uid") String strId) {
        UUID uid;
        try {
            uid = UUID.fromString(strId);
        } catch (Exception e) {
            log.error(ConstantsWS.ERROR_WRONG_ID_FORMAT);
            return ResponseErrors.WRONG_ID_FORMAT;
        }
        return buildResponse(movieDAO.get(uid));
    }

    @GET
    @Path("/{movie_uid}/venues")
    @Produces(ConstantsWS.CONTENT_TYPE)
    public Response getVenues(@PathParam("movie_uid") String movie_uid) {
        UUID uid;
        try {
            uid = UUID.fromString(movie_uid);
        } catch (Exception e) {
            log.error(ConstantsWS.ERROR_WRONG_ID_FORMAT);
            return ResponseErrors.WRONG_ID_FORMAT;
        }

        final JpaMovie movie = movieDAO.get(uid);
        if (movie == null) {
            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }

        final List<JpaVenue> venues = venueDAO.getForMovie(movie);

        final GenericEntity<List<JpaVenue>> gen = new GenericEntity<List<JpaVenue>>(
                venues) {
        };
        return Response.ok(gen).build();
    }

    @GET
    @Path("/{movie_uid}/events")
    @Produces(ConstantsWS.CONTENT_TYPE)
    public Response getEvents(
            @PathParam("movie_uid") String movie_uid,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_RANGE) String range,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_START_DATE) String start,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_END_DATE) String end) {
        UUID uid;
        try {
            uid = UUID.fromString(movie_uid);
        } catch (Exception e) {
            log.error(ConstantsWS.ERROR_WRONG_ID_FORMAT);
            return ResponseErrors.WRONG_ID_FORMAT;
        }

        final JpaMovie movie = movieDAO.get(uid);
        if (movie == null) {
            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }

        DateHelper.DateRange date;
        if (QueryParamHelper.isSpecified(range)) {
            date = QueryParamHelper.getRangeDate(range);
            if (date == null) {
                log.error(ConstantsWS.ERROR_WRONG_DATE_FORMAT);
                return ResponseErrors.WRONG_DATE_FORMAT;
            }
        } else if (QueryParamHelper.isSpecified(start)) {
            date = QueryParamHelper.getRangeDate(start, end);
            if (date == null) {
                log.error(ConstantsWS.ERROR_WRONG_DATE_FORMAT);
                return ResponseErrors.WRONG_DATE_FORMAT;
            }
        } else {
            date = new DateHelper.DateRange(DateHelper.getNow(), DateHelper.MAX_DATE);
        }

        final List<JpaEventCinema> events = cinemaDAO.getForMovie(movie, date);
        final GenericEntity<List<JpaEventCinema>> gen = new GenericEntity<List<JpaEventCinema>>(
                events) {
        };
        return Response.ok(gen).build();
    }

    @Override
    public Response buildResponse(List<JpaMovie> entities) {
        if (entities != null) {
            final GenericEntity<List<JpaMovie>> genEntities = new GenericEntity<List<JpaMovie>>(
                    entities) {
            };
            return Response.ok(genEntities).build();
        } else {
            log.error(ConstantsWS.ERROR_UNKOWN);
            return ResponseErrors.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Response buildResponse(JpaMovie entity) {
        if (entity != null) {
            return Response.ok(entity).build();
        } else {
            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }
    }
}
