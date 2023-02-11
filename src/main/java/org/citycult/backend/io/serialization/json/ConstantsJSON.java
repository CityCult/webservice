package org.citycult.backend.io.serialization.json;

import org.citycult.datastorage.util.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author cpieloth
 */
public interface ConstantsJSON {

    SimpleDateFormat DATE_FORMAT =
        new SimpleDateFormat(DateHelper.DateFormat.YYYYMMDDTHHMMSS.getDatePattern(), Locale.US);

}
