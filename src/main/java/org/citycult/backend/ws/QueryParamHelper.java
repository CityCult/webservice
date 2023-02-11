package org.citycult.backend.ws;

import java.util.Date;

import org.citycult.datastorage.util.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryParamHelper {

	private static Logger log = LoggerFactory.getLogger(QueryParamHelper.class);

	public static DateHelper.DateRange getRangeDate(String range) {
		DateHelper.DateRange date;

		if (ConstantsWS.VAL_RANGE_TODAY.equals(range)) {
			Date start = DateHelper.getToday();
			Date end = new Date(start.getTime() + DateHelper.DAY_MILLISECONDS);
			date = new DateHelper.DateRange(start, end);
		} else if (ConstantsWS.VAL_RANGE_TOMORROW.equals(range)) {
			Date start = DateHelper.getToday();
			start.setTime(start.getTime() + DateHelper.DAY_MILLISECONDS);
			Date end = new Date(start.getTime() + DateHelper.DAY_MILLISECONDS);
			date = new DateHelper.DateRange(start, end);
		} else if (ConstantsWS.VAL_RANGE_WEEKEND.equals(range)) {
			// TODO getRangeDate
			log.warn("Not yet implemented! Using " + ConstantsWS.VAL_RANGE_WEEK
					+ " instead.");
			date = getRangeDate(ConstantsWS.VAL_RANGE_WEEK);
		} else if (ConstantsWS.VAL_RANGE_WEEK.equals(range)) {
			Date start = DateHelper.getToday();
			Date end = new Date(start.getTime() + DateHelper.WEEK_MILLISECONDS);
			date = new DateHelper.DateRange(start, end);
		} else {
			log.error("Unknown range value!");
			date = null;
		}
		return date;
	}

    public static DateHelper.DateRange getRangeDate(String start, String end) {
        Date startDate = DateHelper.parse(start);
        Date endDate = DateHelper.parse(end);

        if (startDate == null) {
            log.error(ConstantsWS.ERROR_WRONG_DATE_FORMAT);
            return null;
        }
        if (endDate == null) {
            log.warn("End date:" + ConstantsWS.ERROR_WRONG_DATE_FORMAT + " Using infinite end date instead.");
            endDate = DateHelper.MAX_DATE;
        }
        return new DateHelper.DateRange(startDate, endDate);
    }

   public static boolean isSpecified(String arg) {
       return !ConstantsWS.VAL_UNSPECIFIED.equals(arg);
   }

}
