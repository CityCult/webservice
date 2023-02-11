package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author cpieloth
 */
public class GsonArraySerializer<T> extends AGsonSerializer<Collection<T>> {

    private static final Logger log = LoggerFactory.getLogger(GsonArraySerializer.class);

    protected final AGsonSerializer<T> serializer;

    public GsonArraySerializer(AGsonSerializer<T> serializer) {
        this.serializer = serializer;
    }

    @Override
    public JsonElement toJson(Collection<T> col) {
        if (col == null) {
            return null;
        }
        if (serializer == null) {
            log.error("Single gson serializer is null!");
            return null;
        }

        final JsonArray a = new JsonArray();
        JsonElement json;
        for (T obj : col) {
            json = serializer.toJson(obj);
            if (json != null && !json.isJsonNull())
                a.add(json);
        }
        return a;
    }
}
