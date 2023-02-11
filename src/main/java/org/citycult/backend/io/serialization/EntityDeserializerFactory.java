package org.citycult.backend.io.serialization;

import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntityDeserializerFactory;

/**
 * @author cpieloth
 */
public abstract class EntityDeserializerFactory {

    public static EntityDeserializerFactory getJSONFactory(Charset encoding) {
        return JSONEntityDeserializerFactory.getInstance(encoding);
    }
}
