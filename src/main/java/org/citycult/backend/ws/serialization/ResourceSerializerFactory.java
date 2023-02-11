package org.citycult.backend.ws.serialization;

import org.citycult.backend.io.Charset;
import org.citycult.backend.ws.serialization.json.JSONResourceSerializerFactory;

/**
 * @author cpieloth
 */
public abstract class ResourceSerializerFactory {
    public static ResourceSerializerFactory getJSONFactory(Charset encoding) {
        return JSONResourceSerializerFactory.getInstance(encoding);
    }
}
