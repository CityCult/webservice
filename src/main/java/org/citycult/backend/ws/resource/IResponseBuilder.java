package org.citycult.backend.ws.resource;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Builds a response with the data representation in the entity body. This
 * methods is required due to correct detection of javax.ws.rs.ext.Provider
 * for generic or derived types.
 *
 * @author cpieloth
 */
public interface IResponseBuilder<ENTITY> {

    /**
     * Builds a response with the data representation in the entity body. This
     * methods is required due to correct detection of javax.ws.rs.ext.Provider
     * for generic or derived types.
     *
     * @param entity
     *            Instance of an entity
     * @return 2xx or 404 response if entity is null
     */
    public Response buildResponse(ENTITY entity);

    /**
     * Builds a response with the data representation in the entity body. This
     * methods is required due to correct detection of javax.ws.rs.ext.Provider
     * for generic or derived types.
     *
     * @param entities
     *            List of entities
     * @return 2xx or 500 response if entities is null
     */
    public Response buildResponse(List<ENTITY> entities);

 }
