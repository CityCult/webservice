package org.citycult.backend.ws.provider;

import org.citycult.backend.io.serialization.IStringDeserializer;
import org.citycult.backend.ws.ConstantsWS;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Consumes(ConstantsWS.CONTENT_TYPE)
public abstract class AJsonUtf8Consumer<T> implements MessageBodyReader<T> {

    protected final IStringDeserializer<T> jsonizer;

    protected AJsonUtf8Consumer(IStringDeserializer<T> serializer) {
        this.jsonizer = serializer;
    }

    @Override
    public T readFrom(Class<T> type, Type genericType,
                      Annotation[] annotations, MediaType mediaType,
                      MultivaluedMap<String, String> httpHeaders, InputStream is)
            throws IOException, WebApplicationException {
        return jsonizer.serialize(is);
    }
}
