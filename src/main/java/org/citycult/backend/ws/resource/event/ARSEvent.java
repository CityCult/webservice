package org.citycult.backend.ws.resource.event;

import org.citycult.datastorage.dao.JpaEventDao;
import org.citycult.datastorage.entity.JpaEvent;
import org.citycult.datastorage.util.DateHelper;
import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.ConstantsWS;
import org.citycult.backend.ws.QueryParamHelper;
import org.citycult.backend.ws.resource.IRSEntityDateable;
import org.citycult.backend.ws.resource.IRSEntityResource;
import org.citycult.backend.ws.resource.IResponseBuilder;
import org.citycult.backend.ws.resource.helper.RSDateableHelper;
import org.citycult.datastorage.dao.JpaEntityDaoFactory;
import org.slf4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

public abstract class ARSEvent implements IResponseBuilder<JpaEvent>, IRSEntityResource, IRSEntityDateable {

    protected static final JpaEntityDaoFactory edf = JpaEntityDaoFactory.getInstance();

    private final Logger log;

    private final JpaEventDao dao;  // TODO JPAEventCategoryDao or sth
    private final RSDateableHelper dater;  // TODO use generic

    protected ARSEvent(JpaEventDao dao, Logger log) {
        this.dao = dao;
        this.log = log;
        this.dater = new RSDateableHelper(this, dao);
    }

    @GET
    @Path("/{uuid}")
    @Produces(ConstantsWS.CONTENT_TYPE)
    @Override
    public Response rsGetDetails(@PathParam("uuid") String strUuid) {
        UUID uid;
        try {
            uid = UUID.fromString(strUuid);
        } catch (Exception e) {
            log.error(ConstantsWS.ERROR_WRONG_ID_FORMAT);
            return ResponseErrors.WRONG_ID_FORMAT;
        }

        final JpaEvent entity = dao.get(uid);

        if (entity != null) {
            return buildResponse(entity);
        } else {
            log.error(ConstantsWS.ERROR_ENTITY_NOT_FOUND);
            return ResponseErrors.ENTITY_NOT_FOUND;
        }
    }

    @GET
    @Produces(ConstantsWS.CONTENT_TYPE)
    public Response rsGetParameters(
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_RANGE) String range,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_START_DATE) String start,
            @DefaultValue(ConstantsWS.VAL_UNSPECIFIED) @QueryParam(ConstantsWS.PARAM_END_DATE) String end) {
        if (QueryParamHelper.isSpecified(range)) {
            return rsGetByRange(range);
        }
        if (QueryParamHelper.isSpecified(start)) {
            return rsGetByDate(start, end);
        }

        return rsGetAll();
    }

    @Override
    public Response rsGetAll() {
        DateHelper.DateRange range = new DateHelper.DateRange(System.currentTimeMillis());
        final List<JpaEvent> entities = dao.getDate(range);
        return buildResponse(entities);
    }

    @Override
    public Response rsGetByRange(String range) {
        return dater.rsGetByRange(range);
    }

    @Override
    public Response rsGetByDate(String start, String end) {
        return dater.rsGetByDate(start, end);
    }
    }
