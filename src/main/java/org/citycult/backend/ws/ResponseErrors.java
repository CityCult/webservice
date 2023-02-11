package org.citycult.backend.ws;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Common response errors.
 */
public interface ResponseErrors {

    public static final Response WRONG_ID_FORMAT = Response
            .status(Response.Status.BAD_REQUEST)
            .entity(ConstantsWS.ERROR_WRONG_ID_FORMAT)
            .type(MediaType.TEXT_PLAIN).build();

    public static final Response WRONG_DATE_FORMAT = Response
            .status(Response.Status.BAD_REQUEST)
            .entity(ConstantsWS.ERROR_WRONG_DATE_FORMAT)
            .type(MediaType.TEXT_PLAIN).build();

    public static final Response RANGE_UNKNOWN = Response
            .status(Response.Status.BAD_REQUEST)
            .entity(ConstantsWS.ERROR_RANGE_UNKNOWN).type(MediaType.TEXT_PLAIN)
            .build();

    public static final Response WRONG_ARGS = Response
            .status(Response.Status.BAD_REQUEST)
            .entity(ConstantsWS.ERROR_WRONG_ARGS).type(MediaType.TEXT_PLAIN)
            .build();

    public static final Response ENTITY_NOT_FOUND = Response
            .status(Response.Status.NOT_FOUND)
            .entity(ConstantsWS.ERROR_ENTITY_NOT_FOUND)
            .type(MediaType.TEXT_PLAIN).build();

    public static final Response INTERNAL_SERVER_ERROR = Response
            .serverError().entity(ConstantsWS.ERROR_UNKOWN)
            .type(MediaType.TEXT_PLAIN).build();

    public static final Response URL_DECODE_ERROR = Response.serverError()
            .entity(ConstantsWS.ERROR_URL_DECODE).type(MediaType.TEXT_PLAIN)
            .build();

    public static final Response NOT_IMPLEMENTED = Response
            .status(ConstantsWS.HTTP_NOT_IMPLEMENTED)
            .entity(ConstantsWS.ERROR_NOT_IMPLEMENTED)
            .type(MediaType.TEXT_PLAIN).build();
}
