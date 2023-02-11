package org.citycult.backend.ws.provider;

import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntitySerializerFactory;
import org.citycult.datastorage.entity.JpaVenue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class VenueProducer extends AJsonUtf8Producer<JpaVenue> {

    public VenueProducer() {
        super(JSONEntitySerializerFactory.getInstance(Charset.UTF8).getVenueSerializer());
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return JpaVenue.class.isAssignableFrom(type);
    }

}
