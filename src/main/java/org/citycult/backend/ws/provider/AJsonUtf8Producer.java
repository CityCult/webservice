package org.citycult.backend.ws.provider;

import org.citycult.backend.io.serialization.IStringSerializer;
import org.citycult.backend.ws.ConstantsWS;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author cpieloth
 */
@Produces(ConstantsWS.CONTENT_TYPE)
public abstract class AJsonUtf8Producer<T> implements MessageBodyWriter<T> {

    protected final IStringSerializer<T> jsonizer;

    protected AJsonUtf8Producer(IStringSerializer<T> serializer) {
        this.jsonizer = serializer;
    }

    @Override
    public long getSize(T t, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        if (t == null) {
            return -1;
        }

        final String s = jsonizer.serialize(t);
        if (s == null)
            return 0;

        try {
            byte[] b = s.getBytes(jsonizer.getEncoding().canonicalName());
            return b.length;
        } catch (UnsupportedEncodingException e) {
            return -1;
        }
    }

    @Override
    public void writeTo(T t, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        if (t != null)
            jsonizer.serialize(outputStream, t);
    }

}
