package org.citycult.backend.io.serialization.json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.citycult.datastorage.entity.JpaEvent;
import org.citycult.datastorage.entity.JpaVenue;
import org.citycult.backend.io.Charset;
import org.citycult.backend.io.serialization.json.ConstantsJSON;

/**
 * @author cpieloth
 */
public class GsonEventSerializer extends AGsonSerializer<JpaEvent> {

    public static final String EVENT_UID = "eventUid";
    public static final String NAME = "name";
    public static final String START_DATE = "startDate";
    public static final String HAS_START_TIME = "hasStartTime";
    public static final String END_DATE = "endDate";
    public static final String HAS_END_TIME = "hasEndTime";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String CATEGORY_ID = "categoryId";
    public static final String VENUE = "venue";

    protected final AGsonSerializer<JpaVenue> venueSerializer;

    public GsonEventSerializer() {
        super();
        venueSerializer = new GsonVenueSerializer(true);
    }

    public GsonEventSerializer(boolean reduced) {
        super(reduced);
        venueSerializer = new GsonVenueSerializer(true);
    }

    public GsonEventSerializer(Charset encoding) {
        super(encoding);
        venueSerializer = new GsonVenueSerializer(true);
    }

    public GsonEventSerializer(Charset encoding, boolean reduced) {
        super(encoding, reduced);
        venueSerializer = new GsonVenueSerializer(true);
    }

    @Override
    public JsonElement toJson(JpaEvent obj) {
        if (obj == null)
            return null;

        JsonObject jObj = new JsonObject();
        jObj.addProperty(EVENT_UID, obj.getEventUid().toString());
        jObj.addProperty(NAME, obj.getName());
        final String sDate = ConstantsJSON.DATE_FORMAT.format(obj
                .getStartDate());
        jObj.addProperty(START_DATE, sDate);
        jObj.addProperty(HAS_START_TIME, obj.hasStartTime());
        if (obj.getEndDate() != null) {
            final String eDate = ConstantsJSON.DATE_FORMAT.format(obj
                    .getEndDate());
            jObj.addProperty(END_DATE, eDate);
        }
        jObj.addProperty(HAS_END_TIME, obj.hasEndTime());
        jObj.addProperty(PRICE, obj.getPrice());
        jObj.addProperty(CATEGORY_ID, obj.getCategory().id);

        JsonElement json = venueSerializer.toJson(obj.getVenue());
        if (json != null && !json.isJsonNull())
            jObj.add(VENUE, json);

        if (!reduced) {
            jObj.addProperty(DESCRIPTION, obj.getDescription());
        }

        return jObj;
    }

}
