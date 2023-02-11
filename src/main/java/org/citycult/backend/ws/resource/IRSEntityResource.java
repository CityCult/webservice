package org.citycult.backend.ws.resource;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author cpieloth
 */
public interface IRSEntityResource {

    public Response rsGetAll();

    public Response rsGetDetails(String strId);
}
