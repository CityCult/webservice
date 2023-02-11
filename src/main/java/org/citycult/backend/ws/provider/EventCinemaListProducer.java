package org.citycult.backend.ws.provider;

import org.citycult.datastorage.entity.JpaEventCinema;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntitySerializerFactory;
import org.citycult.backend.ws.ConstantsWS;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

@Provider
@Produces(ConstantsWS.CONTENT_TYPE)
public class EventCinemaListProducer extends AJsonUtf8Producer<Collection<JpaEventCinema>> {

    public EventCinemaListProducer() {
        super(JSONEntitySerializerFactory.getInstance(Charset.UTF8).getEventCinemaCollectionSerializer());
    }

    @Override
    public long getSize(Collection<JpaEventCinema> t, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        // TODO(cpieloth): simpler?
        // taken from:
        // http://christopherhunt-software.blogspot.de/2010/08/messagebodywriter-iswriteable-method.html
        boolean isWritable;
        if (List.class.isAssignableFrom(type)
                && genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
            isWritable = (actualTypeArgs.length == 1 && actualTypeArgs[0]
                    .equals(JpaEventCinema.class));
        } else {
            isWritable = false;
        }

        return isWritable;
    }

}
