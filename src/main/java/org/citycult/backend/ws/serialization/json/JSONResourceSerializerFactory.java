package org.citycult.backend.ws.serialization.json;

import org.citycult.backend.io.Charset;
import org.citycult.backend.ws.serialization.ResourceSerializerFactory;

import java.util.HashMap;

/**
 * @author cpieloth
 */
public class JSONResourceSerializerFactory extends ResourceSerializerFactory {
    private static final HashMap<Charset, JSONResourceSerializerFactory> instances =
            new HashMap<Charset, JSONResourceSerializerFactory>();

    public static JSONResourceSerializerFactory getInstance(Charset encoding) {
        JSONResourceSerializerFactory instance = instances.get(encoding);
        if (instance == null) {
            instance = new JSONResourceSerializerFactory(encoding);
            instances.put(encoding, instance);
        }
        return instance;
    }

    private final Charset encoding;

    private JSONResourceSerializerFactory(Charset encoding) {
        this.encoding = encoding;
    }
}
