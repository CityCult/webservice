package org.citycult.backend.ws.resource.venue;

import org.citycult.datastorage.entity.Category;
import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.ConstantsWS;
import org.citycult.datastorage.dao.JpaEntityDaoFactory;
import org.citycult.datastorage.dao.JpaEventCinemaDao;
import org.citycult.datastorage.entity.JpaEventCinema;
import org.citycult.datastorage.entity.JpaVenue;
import org.citycult.datastorage.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author cpieloth
 */
@Path("/venue/cinema")
public class RSVenueCinema extends ARSVenue<JpaEventCinema> {

    private static final Logger log = LoggerFactory.getLogger(RSVenueCinema.class);

    private static final JpaEventCinemaDao eventDAO = JpaEntityDaoFactory.getInstance().getEventCinemaDao();

    private static final Category cat = Category.CINEMA;

    @Override
    protected List<JpaVenue> getAll() {
        return venueDAO.getForCategory(cat);
    }

    @Override
    protected List<JpaEventCinema> getEvents(JpaVenue venue, DateHelper.DateRange range) {
        return eventDAO.getForVenue(venue, range);
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected Response buildEventResponse(List<JpaEventCinema> entities) {
        if (entities != null) {
            final GenericEntity<List<JpaEventCinema>> genEntities = new GenericEntity<List<JpaEventCinema>>(
                    entities) {
            };
            return Response.ok(genEntities).build();
        } else {
            log.error(ConstantsWS.ERROR_UNKOWN);
            return ResponseErrors.INTERNAL_SERVER_ERROR;
        }
    }
}
