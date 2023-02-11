package org.citycult.backend.ws.provider;

import org.citycult.datastorage.entity.JpaEventCinema;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntitySerializerFactory;
import org.citycult.backend.ws.ConstantsWS;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(ConstantsWS.CONTENT_TYPE)
public class EventCinemaProducer extends AJsonUtf8Producer<JpaEventCinema> {

    public EventCinemaProducer() {
        super(JSONEntitySerializerFactory.getInstance(Charset.UTF8).getEventCinemaSerializer());
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return JpaEventCinema.class.isAssignableFrom(type);
    }

}
