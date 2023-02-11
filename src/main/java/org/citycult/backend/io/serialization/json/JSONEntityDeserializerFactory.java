package org.citycult.backend.io.serialization.json;

import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.EntityDeserializerFactory;

import java.util.HashMap;

/**
 * @author cpieloth
 */
public class JSONEntityDeserializerFactory extends EntityDeserializerFactory {

    private static final HashMap<Charset, JSONEntityDeserializerFactory> instances = new HashMap<Charset, JSONEntityDeserializerFactory>();

    public static JSONEntityDeserializerFactory getInstance(Charset encoding) {
        JSONEntityDeserializerFactory instance = instances.get(encoding);
        if (instance == null) {
            instance = new JSONEntityDeserializerFactory(encoding);
            instances.put(encoding, instance);
        }
        return instance;
    }

    private final Charset encoding;

    private JSONEntityDeserializerFactory(Charset encoding) {
        this.encoding = encoding;
    }
}
