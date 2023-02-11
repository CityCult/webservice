package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonStreamParser;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.AStringDeserializer;

/**
 * @author cpieloth
 */
public abstract class AGsonDeserializer<T> extends AStringDeserializer<T> {

    protected AGsonDeserializer() {
        super();
    }

    protected AGsonDeserializer(Charset encoding) {
        super(encoding);
    }

    @Override
    public T serialize(String str) {
        if (str == null)
            return null;
        JsonStreamParser streamParser = new JsonStreamParser(str);
        return fromJson(streamParser);
    }

    public abstract T fromJson(JsonStreamParser streamParser);
}
