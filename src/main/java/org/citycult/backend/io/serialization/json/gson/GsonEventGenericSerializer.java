package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonElement;
import org.citycult.datastorage.entity.JpaEvent;
import org.citycult.backend.io.Charset;

/**
 * @author cpieloth
 */
public class GsonEventGenericSerializer<T extends JpaEvent> extends AGsonSerializer<T> {

    protected final AGsonSerializer<JpaEvent> eventSerializer;

    public GsonEventGenericSerializer() {
        super();
        eventSerializer = new GsonEventSerializer(reduced);
    }

    public GsonEventGenericSerializer(boolean reduced) {
        super(reduced);
        eventSerializer = new GsonEventSerializer(reduced);
    }

    public GsonEventGenericSerializer(Charset encoding) {
        super(encoding);
        eventSerializer = new GsonEventSerializer(reduced);
    }

    public GsonEventGenericSerializer(Charset encoding, boolean reduced) {
        super(encoding, reduced);
        eventSerializer = new GsonEventSerializer(reduced);
    }

    @Override
    public JsonElement toJson(T obj) {
        return eventSerializer.toJson(obj);
    }
}
