package org.citycult.backend.io.serialization.json;

import org.citycult.datastorage.entity.*;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.EntitySerializerFactory;
import org.citycult.backend.io.serialization.IStringSerializer;
import org.citycult.backend.io.serialization.json.gson.*;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author cpieloth
 */
public class JSONEntitySerializerFactory extends EntitySerializerFactory {

    private static final HashMap<Charset, JSONEntitySerializerFactory> instances = new HashMap<Charset, JSONEntitySerializerFactory>();

    public static JSONEntitySerializerFactory getInstance(Charset encoding) {
        JSONEntitySerializerFactory instance = instances.get(encoding);
        if (instance == null) {
            instance = new JSONEntitySerializerFactory(encoding);
            instances.put(encoding, instance);
        }
        return instance;
    }

    private final Charset encoding;

    private JSONEntitySerializerFactory(Charset encoding) {
        this.encoding = encoding;
    }

    @Override
    public IStringSerializer<JpaEvent> getEventSerializer() {
        return new GsonEventSerializer(encoding);
    }

    @Override
    public IStringSerializer<Collection<JpaEvent>> getEventCollectionSerializer() {
        GsonEventSerializer s = new GsonEventSerializer(encoding, true);
        return new GsonArraySerializer<JpaEvent>(s);
    }

    @Override
    public IStringSerializer<JpaEventArtCulture> getEventArtCultureSerializer() {
        return new GsonEventGenericSerializer<JpaEventArtCulture>();
    }

    @Override
    public IStringSerializer<Collection<JpaEventArtCulture>> getEventArtCultureCollectionSerializer() {
        GsonEventGenericSerializer<JpaEventArtCulture> s = new GsonEventGenericSerializer<JpaEventArtCulture>(encoding, true);
        return new GsonArraySerializer<JpaEventArtCulture>(s);
    }

    @Override
    public IStringSerializer<JpaEventCinema> getEventCinemaSerializer() {
        return new GsonEventCinemaSerializer(encoding);
    }

    @Override
    public IStringSerializer<Collection<JpaEventCinema>> getEventCinemaCollectionSerializer() {
        GsonEventCinemaSerializer s = new GsonEventCinemaSerializer(encoding, true);
        return new GsonArraySerializer<JpaEventCinema>(s);
    }

    @Override
    public IStringSerializer<JpaEventLive> getEventLiveSerializer() {
        return new GsonEventGenericSerializer<JpaEventLive>();
    }

    @Override
    public IStringSerializer<Collection<JpaEventLive>> getEventLiveCollectionSerializer() {
        GsonEventGenericSerializer<JpaEventLive> s = new GsonEventGenericSerializer<JpaEventLive>(encoding, true);
        return new GsonArraySerializer<JpaEventLive>(s);
    }

    @Override
    public IStringSerializer<JpaEventNightlife> getEventNightlifeSerializer() {
        return new GsonEventGenericSerializer<JpaEventNightlife>();
    }

    @Override
    public IStringSerializer<Collection<JpaEventNightlife>> getEventNightlifeCollectionSerializer() {
        GsonEventGenericSerializer<JpaEventNightlife> s = new GsonEventGenericSerializer<JpaEventNightlife>(encoding, true);
        return new GsonArraySerializer<JpaEventNightlife>(s);
    }

    @Override
    public IStringSerializer<JpaMovie> getMovieSerializer() {
        return new GsonMovieSerializer(encoding);
    }

    @Override
    public IStringSerializer<Collection<JpaMovie>> getMovieCollectionSerializer() {
        GsonMovieSerializer s = new GsonMovieSerializer(encoding, true);
        return new GsonArraySerializer<JpaMovie>(s);
    }

    @Override
    public IStringSerializer<JpaVenue> getVenueSerializer() {
        return new GsonVenueSerializer(encoding);
    }

    @Override
    public IStringSerializer<Collection<JpaVenue>> getVenueCollectionSerializer() {
        GsonVenueSerializer s = new GsonVenueSerializer(encoding, true);
        return new GsonArraySerializer<JpaVenue>(s);
    }
}
