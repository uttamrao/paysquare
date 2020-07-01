package com.ps.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

public class LocalDateUtils {

	static Logger logger = Logger.getLogger(LocalDateUtils.class);
	
	static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public static LocalDate date(String inputDate){
		
		if(logger.isDebugEnabled()) logger.debug("In getDateTime utility method "
				+ "converting string to date inputDate-> "+inputDate);	
		
		LocalDate date = null;
		try {			
			if(!StringUtils.isBlank(inputDate)) {
				if(logger.isDebugEnabled()) logger.debug("Parsing the input date-time string "+inputDate);
				date = LocalDate.parse(inputDate, dateFormatter);
			}			
		} catch (Exception e) {
			logger.error("Exception occured while "
					+ "converting string to date "+e.getMessage(),e);			
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()) logger.debug("Returning date after conversion, "
				+ " date object-> "+date);	
		
		return date;
	}
	
	public static String dateString(LocalDate inputDate){
		
		if(logger.isDebugEnabled()) logger.debug("In getDateTimeString utility method "
				+ "converting date-time to string inputDate-> "+inputDate);	
		
		String date = null;
		try {			
			if(logger.isDebugEnabled()) logger.debug("Formatting the input date object "+inputDate);
			date = dateFormatter.format(inputDate);	
			
		} catch (Exception e) {
			logger.error("Exception occured while "
					+ "converting date to string "+e.getMessage(),e);			
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()) logger.debug("Returning string date-time after conversion, "
				+ " date String-> "+date);	
		
		return date;
	}
	
	public static LocalDate convert(Date date, ZoneId zoneId){
		
		if(date == null)
			return null;
		else
			return	Instant.ofEpochMilli(date.getTime()).atZone(zoneId).toLocalDate();
	}
	
	public static DayOfWeek startOftheWeek(Locale locale){
		
		if(locale == null)
			return null;
		else
			return	 WeekFields.of(Locale.US).getFirstDayOfWeek();
	}

	public static DayOfWeek startOftheWeek(int week){
		
			return	DayOfWeek.of(week);
	}
	
	public static DayOfWeek enOftheWeek(DayOfWeek startOfWeek){
		
		return	DayOfWeek.of(((startOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
	}
}
