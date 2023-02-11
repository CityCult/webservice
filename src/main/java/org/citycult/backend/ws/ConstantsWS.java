package org.citycult.backend.ws;

import java.text.SimpleDateFormat;

import javax.ws.rs.core.MediaType;

import org.citycult.backend.io.serialization.json.ConstantsJSON;

/**
 * Some definitions for Webservice API. Prefix naming conventions:
 * - HTTP_ ... Constants related to HTTP standard
 * - ERROR_ ... Error message for user
 * - VAL_ ... Constant for parameter value
 * - PARAM_ ... Constant for a parameter name
 *
 * @author Christof Pieloth
 */
public interface ConstantsWS {

    public static final int HTTP_NOT_IMPLEMENTED = 501;

    public static final String ERROR_NOT_IMPLEMENTED = "Not yet implemented!";

    public static final String ERROR_URL_DECODE = "Could not decode parameter!";

    public static final String ERROR_UNKOWN = "Unknown error!";

    public static final String ERROR_WRONG_ARGS = "Wrong or missing arguments!";

    public static final String ERROR_WRONG_DATE_FORMAT = "Wrong date format!";

    public static final String ERROR_WRONG_ID_FORMAT = "Wrong ID format!";

    public static final String ERROR_ENTITY_NOT_FOUND = "Entity not found!";

    public static final String ERROR_RANGE_UNKNOWN = "Unknown range!";

    public static final String PARAM_RANGE = "range";

    public static final String VAL_RANGE_TODAY = "today";

    public static final String VAL_RANGE_TOMORROW = "tomorrow";

    public static final String VAL_RANGE_WEEK = "week";

    public static final String VAL_RANGE_WEEKEND = "weekend";

    public static final String VAL_UNSPECIFIED = "UNSPECIFIED";

    public static final String PARAM_START_DATE = "start";

    public static final String PARAM_END_DATE = "end";

    public static final String PARAM_SEARCH = "search";

    public static final String PARAM_RANDOM = "random";

    public static final String PARAM_COUNT = "count";

    public static final String PARAM_ACCOUNT = "account";

    public static final String PARAM_VALIDATE_FORMAT = "format";

    public static final String PARAM_VALIDATE_FORMAT_CONTENT = "content";

    public static final SimpleDateFormat DATE_FORMAT = ConstantsJSON.DATE_FORMAT;

    public static final String CONTENT_TYPE = MediaType.APPLICATION_JSON + "; charset=UTF-8";

    public static final String PARAM_VERSION = "version";

    public static final String PARAM_OS = "os";

    public static final String VAL_OS_GENERIC = "generic";

    public static final String VAL_OS_TEST = "test";

    public static final String VAL_OS_ANDROID = "android";

    public static final String VAL_OS_WP8 = "wp8";

}
