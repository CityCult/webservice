//package org.citycult.backend.ws.resource.event;
//
//import org.citycult.backend.ws.ResponseErrors;
//import org.citycult.backend.ws.ConstantsWS;
//import org.citycult.datastorage.dao.JpaEventLiveDao;
//import org.citycult.datastorage.entity.JpaEventLive;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.ws.rs.Path;
//import javax.ws.rs.core.GenericEntity;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
//@Path("/event/live")
//public class RSEventLive extends ARSEvent<JpaEventLive> {
//
//    private static final Logger log = LoggerFactory.getLogger(RSEventLive.class);
//
//    private static final JpaEventLiveDao dao = edf.getEventLiveDao();
//
//    public RSEventLive() {
//        super(dao, log);
//    }
//
//
//    @Override
//    public Response buildResponse(List<JpaEventLive> entities) {
//        if (entities != null) {
//            final GenericEntity<List<JpaEventLive>> genEntities = new GenericEntity<List<JpaEventLive>>(
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
//    public Response buildResponse(JpaEventLive entity) {
//        if (entity != null) {
//            return Response.ok(entity).build();
//        } else {
//            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
//            return ResponseErrors.ENTITY_NOT_FOUND;
//        }
//    }
//
//}
