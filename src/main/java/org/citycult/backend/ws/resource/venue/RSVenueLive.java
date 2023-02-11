package org.citycult.backend.ws.resource.venue;

import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.ConstantsWS;
import org.citycult.datastorage.dao.JpaEntityDaoFactory;
import org.citycult.datastorage.dao.JpaEventLiveDao;
import org.citycult.datastorage.entity.Category;
import org.citycult.datastorage.entity.JpaEventLive;
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
@Path("/venue/live")
public class RSVenueLive extends ARSVenue<JpaEventLive> {

    private static final Logger log = LoggerFactory.getLogger(RSVenueLive.class);

    private static final JpaEventLiveDao eventDAO = JpaEntityDaoFactory.getInstance().getEventLiveDao();

    private static final Category cat = Category.LIVE;

    @Override
    protected List<JpaVenue> getAll() {
        return venueDAO.getForCategory(cat);
    }

    @Override
    protected List<JpaEventLive> getEvents(JpaVenue venue, DateHelper.DateRange range) {
        return eventDAO.getForVenue(venue, range);
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected Response buildEventResponse(List<JpaEventLive> entities) {
        if (entities != null) {
            final GenericEntity<List<JpaEventLive>> genEntities = new GenericEntity<List<JpaEventLive>>(
                    entities) {
            };
            return Response.ok(genEntities).build();
        } else {
            log.error(ConstantsWS.ERROR_UNKOWN);
            return ResponseErrors.INTERNAL_SERVER_ERROR;
        }
    }
}
