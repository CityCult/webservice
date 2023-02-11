package org.citycult.backend.ws.resource.event;

import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.ConstantsWS;
import org.citycult.datastorage.dao.JpaEventDao;
import org.citycult.datastorage.entity.JpaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/event")
public class RSEvent extends ARSEvent {

    private static final Logger log = LoggerFactory.getLogger(RSEvent.class);

    private static final JpaEventDao dao = edf.getEventDao();

    public RSEvent() {
        super(dao, log);
    }

    @Override
    public Response buildResponse(List<JpaEvent> entities) {
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

    @Override
    public Response buildResponse(JpaEvent entity) {
        if (entity != null) {
            return Response.ok(entity).build();
        } else {
            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }
    }
}
