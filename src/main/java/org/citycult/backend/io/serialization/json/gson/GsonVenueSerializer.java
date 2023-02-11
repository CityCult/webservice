package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.citycult.datastorage.entity.JpaVenue;
import org.citycult.backend.io.Charset;

/**
 * @author cpieloth
 */
public class GsonVenueSerializer extends AGsonSerializer<JpaVenue> {

    public static final String VENUE_UID = "venueUid";
    public static final String NAME = "name";
    public static final String STREET = "street";
    public static final String POSTCODE = "postcode";
    public static final String CITY = "city";
    public static final String DESCRIPTION = "description";
    public static final String WEBSITE = "website";

    public GsonVenueSerializer() {
        super();
    }

    public GsonVenueSerializer(boolean reduced) {
        super(reduced);
    }

    public GsonVenueSerializer(Charset encoding) {
        super(encoding);
    }

    public GsonVenueSerializer(Charset encoding, boolean reduced) {
        super(encoding, reduced);
    }

    @Override
    public JsonElement toJson(JpaVenue obj) {
        if (obj == null)
            return null;

        JsonObject json = new JsonObject();
        json.addProperty(VENUE_UID, obj.getVenueUid().toString());
        json.addProperty(NAME, obj.getName());
        json.addProperty(STREET, obj.getStreet());
        json.addProperty(POSTCODE, obj.getPostcode());
        json.addProperty(CITY, obj.getCity());
        json.addProperty(WEBSITE, obj.getWebsite());
        if (!reduced)
            json.addProperty(DESCRIPTION, obj.getDescription());
        return json;
    }
}
