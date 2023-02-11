package org.citycult.backend.ws.resource.helper;

import org.citycult.backend.ws.ConstantsWS;
import org.citycult.backend.ws.QueryParamHelper;
import org.citycult.backend.ws.ResponseErrors;
import org.citycult.backend.ws.resource.IRSEntityDateable;
import org.citycult.backend.ws.resource.IResponseBuilder;
import org.citycult.datastorage.dao.JpaEventDao;
import org.citycult.datastorage.entity.JpaEvent;
import org.citycult.datastorage.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author cpieloth
 */
public class RSDateableHelper implements IRSEntityDateable {

    private static final Logger log = LoggerFactory.getLogger(RSDateableHelper.class);

    private final IResponseBuilder<JpaEvent> resource;

    private final JpaEventDao dao;  // TODO JpaEventCategoryDao or sth

    public RSDateableHelper(IResponseBuilder<JpaEvent> resource, JpaEventDao dao) {
        this.resource = resource;
        this.dao = dao;
    }

    @Override
    public Response rsGetByRange(String strRange) {
        DateHelper.DateRange range = QueryParamHelper.getRangeDate(strRange);
        if (range == null) {
            log.error(ConstantsWS.ERROR_RANGE_UNKNOWN);
            return ResponseErrors.RANGE_UNKNOWN;
        }

        List<JpaEvent> entities = dao.getDate(range);
        return resource.buildResponse(entities);
    }

    @Override
    public Response rsGetByDate(String start, String end) {
        DateHelper.DateRange range = QueryParamHelper.getRangeDate(start, end);
        if (range == null) {
            log.error(ConstantsWS.ERROR_WRONG_DATE_FORMAT);
            return ResponseErrors.WRONG_DATE_FORMAT;
        }

        List<JpaEvent> entities = dao.getDate(range);
        return resource.buildResponse(entities);
    }
}
