package org.citycult.backend.ws.provider;

import org.citycult.datastorage.entity.*;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntitySerializerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * @author cpieloth
 */
@Provider
public class EventListProducer extends AJsonUtf8Producer<Collection<JpaEvent>> {

    public EventListProducer() {
        super(JSONEntitySerializerFactory.getInstance(Charset.UTF8).getEventCollectionSerializer());
    }

    @Override
    public long getSize(Collection<JpaEvent> t, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        // TODO(cpieloth): simpler?
        // taken from:
        // http://christopherhunt-software.blogspot.de/2010/08/messagebodywriter-iswriteable-method.html
        boolean isWritable = false;
        if (List.class.isAssignableFrom(aClass)
                && type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArgs = (parameterizedType.getActualTypeArguments());
            if(actualTypeArgs.length == 1){
                isWritable = JpaEvent.class.equals(actualTypeArgs[0]);
                isWritable = isWritable || JpaEventArtCulture.class.equals(actualTypeArgs[0]);
                isWritable = isWritable || JpaEventLive.class.equals(actualTypeArgs[0]);
                isWritable = isWritable || JpaEventNightlife.class.equals(actualTypeArgs[0]);
                isWritable = isWritable || JpaEventSport.class.equals(actualTypeArgs[0]);
                isWritable = isWritable && !JpaEventCinema.class.equals(actualTypeArgs[0]);
            }
        }
        return isWritable;
    }
}
