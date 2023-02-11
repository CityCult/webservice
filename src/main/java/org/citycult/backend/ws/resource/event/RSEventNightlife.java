//package org.citycult.backend.ws.resource.event;
//
//import org.citycult.backend.ws.ResponseErrors;
//import org.citycult.backend.ws.ConstantsWS;
//import org.citycult.datastorage.dao.JpaEventNightlifeDao;
//import org.citycult.datastorage.entity.JpaEventNightlife;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.ws.rs.Path;
//import javax.ws.rs.core.GenericEntity;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
//@Path("/event/nightlife")
//public class RSEventNightlife extends ARSEvent<JpaEventNightlife> {
//
//    private static final Logger log = LoggerFactory.getLogger(RSEventNightlife.class);
//
//    private static final JpaEventNightlifeDao dao = edf.getEventNightlifeDao();
//
//    public RSEventNightlife() {
//        super(dao, log);
//    }
//
//
//    @Override
//    public Response buildResponse(List<JpaEventNightlife> entities) {
//        if (entities != null) {
//            final GenericEntity<List<JpaEventNightlife>> genEntities = new GenericEntity<List<JpaEventNightlife>>(
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
//    public Response buildResponse(JpaEventNightlife entity) {
//        if (entity != null) {
//            return Response.ok(entity).build();
//        } else {
//            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
//            return ResponseErrors.ENTITY_NOT_FOUND;
//        }
//    }
//
//}
