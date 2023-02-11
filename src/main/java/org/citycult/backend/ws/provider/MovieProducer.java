package org.citycult.backend.ws.provider;

import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntitySerializerFactory;
import org.citycult.backend.ws.ConstantsWS;
import org.citycult.datastorage.entity.JpaMovie;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(ConstantsWS.CONTENT_TYPE)
public class MovieProducer extends AJsonUtf8Producer<JpaMovie> {

    public MovieProducer() {
        super(JSONEntitySerializerFactory.getInstance(Charset.UTF8).getMovieSerializer());
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return JpaMovie.class.isAssignableFrom(type);
    }

}
