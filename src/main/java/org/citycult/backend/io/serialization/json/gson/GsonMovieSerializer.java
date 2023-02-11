package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.citycult.datastorage.entity.JpaMovie;
import org.citycult.backend.io.Charset;

/**
 * @author cpieloth
 */
public class GsonMovieSerializer extends AGsonSerializer<JpaMovie> {

    public static final String MOVIE_UID = "movieUid";
    public static final String TITLE = "title";
    public static final String GENRE = "genre";
    public static final String YEAR = "year";
    public static final String RUNTIME = "runtime";
    public static final String DESCRIPTION = "description";

    public GsonMovieSerializer() {
        super();
    }

    public GsonMovieSerializer(boolean reduced) {
        super(reduced);
    }

    public GsonMovieSerializer(Charset encoding) {
        super(encoding);
    }

    public GsonMovieSerializer(Charset encoding, boolean reduced) {
        super(encoding, reduced);
    }

    @Override
    public JsonElement toJson(JpaMovie obj) {
        if (obj == null)
            return null;

        JsonObject json = new JsonObject();
        json.addProperty(MOVIE_UID, obj.getMovieUid().toString());
        json.addProperty(TITLE, obj.getTitle());
        json.addProperty(YEAR, obj.getYear());
        json.addProperty(RUNTIME, obj.getRuntime());
        json.addProperty(GENRE, obj.getGenre());
        if (!reduced)
            json.addProperty(DESCRIPTION, obj.getDescription());
        return json;
    }
}
