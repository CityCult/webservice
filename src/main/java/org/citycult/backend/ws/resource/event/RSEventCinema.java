//package org.citycult.backend.ws.resource.event;
//
//import org.citycult.backend.ws.ResponseErrors;
//import org.citycult.backend.ws.ConstantsWS;
//import org.citycult.datastorage.dao.JpaEventCinemaDao;
//import org.citycult.datastorage.entity.JpaEventCinema;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.ws.rs.Path;
//import javax.ws.rs.core.GenericEntity;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
//@Path("/event/cinema")
//public class RSEventCinema extends ARSEvent<JpaEventCinema> {
//
//    private static final Logger log = LoggerFactory.getLogger(RSEventCinema.class);
//
//    private static final JpaEventCinemaDao dao = edf.getEventArtCultureDao();
//
//    public RSEventCinema() {
//        super(dao, log);
//    }
//
//    @Override
//    public Response buildResponse(List<JpaEventCinema> entities) {
//        if (entities != null) {
//            final GenericEntity<List<JpaEventCinema>> genEntities = new GenericEntity<List<JpaEventCinema>>(
//                    entities) {
//            };
//            return Response.ok(genEntities).build();
//        } else {
//            log.error(ConstantsWS.ERROR_UNKOWN);
//            return ResponseErrors.INTERNAL_SERVER_ERROR;
//        }
//    }
//
//    @Override
//    public Response buildResponse(JpaEventCinema entity) {
//        if (entity != null) {
//            return Response.ok(entity).build();
//        } else {
//            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
//            return ResponseErrors.ENTITY_NOT_FOUND;
//        }
//    }
//
//}
