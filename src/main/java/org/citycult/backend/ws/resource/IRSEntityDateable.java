package org.citycult.backend.ws.resource;

import javax.ws.rs.core.Response;

/**
 * @author cpieloth
 */
public interface IRSEntityDateable {

    public Response rsGetByRange(String range);

    public Response rsGetByDate(String start, String end);

}
