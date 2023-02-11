package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.citycult.datastorage.entity.*;
import org.citycult.backend.io.Charset;

/**
 * @author cpieloth
 */
public class GsonEventCinemaSerializer extends AGsonSerializer<JpaEventCinema> {

    public static final String MOVIE = "movie";

    protected final AGsonSerializer<JpaEvent> eventSerializer;
    protected final AGsonSerializer<JpaMovie> movieSerializer;

    public GsonEventCinemaSerializer() {
        super();
        eventSerializer = new GsonEventSerializer(reduced);
        movieSerializer = new GsonMovieSerializer(reduced);
    }

    public GsonEventCinemaSerializer(boolean reduced) {
        super(reduced);
        eventSerializer = new GsonEventSerializer(reduced);
        movieSerializer = new GsonMovieSerializer(reduced);
    }

    public GsonEventCinemaSerializer(Charset encoding) {
        super(encoding);
        eventSerializer = new GsonEventSerializer(reduced);
        movieSerializer = new GsonMovieSerializer(reduced);
    }

    public GsonEventCinemaSerializer(Charset encoding, boolean reduced) {
        super(encoding, reduced);
        eventSerializer = new GsonEventSerializer(reduced);
        movieSerializer = new GsonMovieSerializer(reduced);
    }

    @Override
    public JsonElement toJson(JpaEventCinema obj) {
        if (obj == null)
            return null;

        JsonElement json = eventSerializer.toJson(obj);
        if (json == null) {
            return null;
        }

        JsonObject jObj = json.getAsJsonObject();
        if (jObj == null) {
            return json;
        }

        json = movieSerializer.toJson(obj.getMovie());
        if (json != null && !json.isJsonNull())
            jObj.add(MOVIE, json);

        return jObj;
    }

}
