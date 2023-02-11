package org.citycult.backend.ws.resource.venue;

import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.ConstantsWS;
import org.citycult.datastorage.dao.JpaEntityDaoFactory;
import org.citycult.datastorage.dao.JpaEventDao;
import org.citycult.datastorage.entity.JpaEvent;
import org.citycult.datastorage.entity.JpaVenue;
import org.citycult.datastorage.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/venue")
public class RSVenue extends ARSVenue<JpaEvent> {

    private static final Logger log = LoggerFactory.getLogger(RSVenue.class);

    private static final JpaEventDao eventDAO = JpaEntityDaoFactory.getInstance().getEventDao();

    @Override
    protected List<JpaVenue> getAll() {
        return venueDAO.getAll();
    }

    @Override
    protected List<JpaEvent> getEvents(JpaVenue venue, DateHelper.DateRange range) {
        return eventDAO.getForVenue(venue, range);
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected Response buildEventResponse(List<JpaEvent> entities) {
        if (entities != null) {
            final GenericEntity<List<JpaEvent>> genEntities = new GenericEntity<List<JpaEvent>>(
                    entities) {
            };
            return Response.ok(genEntities).build();
        } else {
            log.error(ConstantsWS.ERROR_UNKOWN);
            return ResponseErrors.INTERNAL_SERVER_ERROR;
        }
    }

}
