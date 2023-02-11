//package org.citycult.backend.ws.resource.event;
//
//import org.citycult.backend.ws.ResponseErrors;
//import org.citycult.backend.ws.ConstantsWS;
//import org.citycult.datastorage.dao.JpaEntityDaoFactory;
//import org.citycult.datastorage.dao.JpaEventArtCultureDao;
//import org.citycult.datastorage.entity.JpaEventArtCulture;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.ws.rs.Path;
//import javax.ws.rs.core.GenericEntity;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
//@Path("/event/artculture")
//public class RSEventArtCulture extends ARSEvent<JpaEventArtCulture> {
//
//    private static final Logger log = LoggerFactory.getLogger(RSEventArtCulture.class);
//
//    private static final JpaEventArtCultureDao dao = JpaEntityDaoFactory.getInstance().getEventArtCultureDao();
//
//    public RSEventArtCulture() {
//        super(dao, log);
//    }
//
//    @Override
//    public Response buildResponse(List<JpaEventArtCulture> entities) {
//        if (entities != null) {
//            final GenericEntity<List<JpaEventArtCulture>> genEntities = new GenericEntity<List<JpaEventArtCulture>>(
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
//    public Response buildResponse(JpaEventArtCulture entity) {
//        if (entity != null) {
//            return Response.ok(entity).build();
//        } else {
//            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
//            return ResponseErrors.ENTITY_NOT_FOUND;
//        }
//    }
//
//
//
//}
