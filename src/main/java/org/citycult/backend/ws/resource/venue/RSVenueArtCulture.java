package org.citycult.backend.ws.resource.venue;

import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.ConstantsWS;
import org.citycult.datastorage.dao.JpaEntityDaoFactory;
import org.citycult.datastorage.dao.JpaEventArtCultureDao;
import org.citycult.datastorage.entity.Category;
import org.citycult.datastorage.entity.JpaEventArtCulture;
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
@Path("/venue/artculture")
public class RSVenueArtCulture extends ARSVenue<JpaEventArtCulture> {

    private static final Logger log = LoggerFactory.getLogger(RSVenueArtCulture.class);

    private static final JpaEventArtCultureDao eventDAO = JpaEntityDaoFactory.getInstance().getEventArtCultureDao();

    private static final Category cat = Category.ARTCULTURE;

    @Override
    protected List<JpaVenue> getAll() {
        return venueDAO.getForCategory(cat);
    }

    @Override
    protected List<JpaEventArtCulture> getEvents(JpaVenue venue, DateHelper.DateRange range) {
        return eventDAO.getForVenue(venue, range);
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected Response buildEventResponse(List<JpaEventArtCulture> entities) {
        if (entities != null) {
            final GenericEntity<List<JpaEventArtCulture>> genEntities = new GenericEntity<List<JpaEventArtCulture>>(
                    entities) {
            };
            return Response.ok(genEntities).build();
        } else {
            log.error(ConstantsWS.ERROR_UNKOWN);
            return ResponseErrors.INTERNAL_SERVER_ERROR;
        }
    }
}
