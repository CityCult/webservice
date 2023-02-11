package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonElement;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.AStringSerializer;

/**
 * @author cpieloth
 */
public abstract class AGsonSerializer<T> extends AStringSerializer<T> {

    protected final boolean reduced;

    protected AGsonSerializer() {
        this(false);
    }

    protected AGsonSerializer(boolean reduced) {
        super();
        this.reduced = reduced;
    }

    protected AGsonSerializer(Charset encoding) {
        this(encoding, false);
    }

    protected AGsonSerializer(Charset encoding, boolean reduced) {
        super(encoding);
        this.reduced = reduced;
    }

    @Override
    public String serialize(T obj) {
        JsonElement e = toJson(obj);
        if(e != null)
            return e.toString();
        else
            return null;
    }

    public abstract JsonElement toJson(T obj);

}
