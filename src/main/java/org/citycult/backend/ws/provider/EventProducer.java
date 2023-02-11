package org.citycult.backend.ws.provider;

import org.citycult.datastorage.entity.*;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntitySerializerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @author cpieloth
 */
@Provider
public class EventProducer extends AJsonUtf8Producer<JpaEvent> {

    public EventProducer() {
        super(JSONEntitySerializerFactory.getInstance(Charset.UTF8).getEventSerializer());
    }

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        // NOTE: We need to check for specialization of IEvent, if there are serializations for sub classes!
        boolean isAssignable = JpaEvent.class.isAssignableFrom(aClass);
        isAssignable = isAssignable || JpaEventArtCulture.class.isAssignableFrom(aClass);
        isAssignable = isAssignable || JpaEventLive.class.isAssignableFrom(aClass);
        isAssignable = isAssignable || JpaEventNightlife.class.isAssignableFrom(aClass);
        isAssignable = isAssignable || JpaEventSport.class.isAssignableFrom(aClass);
        return isAssignable && !JpaEventCinema.class.isAssignableFrom(aClass);
    }
}
