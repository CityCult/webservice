package org.citycult.backend.io.serialization;

import org.citycult.datastorage.entity.*;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.JSONEntitySerializerFactory;

import java.util.Collection;

/**
 * @author cpieloth
 */
public abstract class EntitySerializerFactory {

    public static EntitySerializerFactory getJSONFactory(Charset encoding) {
        return JSONEntitySerializerFactory.getInstance(encoding);
    }

    public abstract IStringSerializer<JpaEvent> getEventSerializer();

    public abstract IStringSerializer<Collection<JpaEvent>> getEventCollectionSerializer();

    public abstract IStringSerializer<JpaEventArtCulture> getEventArtCultureSerializer();

    public abstract IStringSerializer<Collection<JpaEventArtCulture>> getEventArtCultureCollectionSerializer();

    public abstract IStringSerializer<JpaEventCinema> getEventCinemaSerializer();

    public abstract IStringSerializer<Collection<JpaEventCinema>> getEventCinemaCollectionSerializer();

    public abstract IStringSerializer<JpaEventLive> getEventLiveSerializer();

    public abstract IStringSerializer<Collection<JpaEventLive>> getEventLiveCollectionSerializer();

    public abstract IStringSerializer<JpaEventNightlife> getEventNightlifeSerializer();

    public abstract IStringSerializer<Collection<JpaEventNightlife>> getEventNightlifeCollectionSerializer();

    public abstract IStringSerializer<JpaMovie> getMovieSerializer();

    public abstract IStringSerializer<Collection<JpaMovie>> getMovieCollectionSerializer();

    public abstract IStringSerializer<JpaVenue> getVenueSerializer();

    public abstract IStringSerializer<Collection<JpaVenue>> getVenueCollectionSerializer();
}
