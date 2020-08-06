package com.ps.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

public class DateUtils {

	static Logger logger = Logger.getLogger(DateUtils.class);
	
	static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static SimpleDateFormat dateMonthFormatter = new SimpleDateFormat("MM-dd");

	public static Date getDateTime(String inputDate){
		
		if(logger.isDebugEnabled()) logger.debug("In getDateTime utility method "
				+ "converting string to date inputDate-> "+inputDate);	
		
		Date date = null;
		try {			
			if(!StringUtils.isBlank(inputDate)) {
				if(logger.isDebugEnabled()) logger.debug("Parsing the input date-time string "+inputDate);
				date = dateTimeFormatter.parse(inputDate);
			}			
		} catch (Exception e) {
			logger.error("Exception occured while "
					+ "converting string to date-time "+e.getMessage(),e);			
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()) logger.debug("Returning date-time after conversion, "
				+ " date object-> "+date);	
		
		return date;
	}
	
	public static String getDateTimeString(Date inputDate){
		
		if(logger.isDebugEnabled()) logger.debug("In getDateTimeString utility method "
				+ "converting date-time to string inputDate-> "+inputDate);	
		
		String date = null;
		try {			
			if(logger.isDebugEnabled()) logger.debug("Formatting the input date object "+inputDate);
			date = dateTimeFormatter.format(inputDate);			
		} catch (Exception e) {
			logger.error("Exception occured while "
					+ "converting date-time to string "+e.getMessage(),e);			
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()) logger.debug("Returning string date-time after conversion, "
				+ " date String-> "+date);	
		
		return date;
	}

	public static Date getDateMonth(String inputDate){
		
		if(logger.isDebugEnabled()) logger.debug("In getDateMonth utility method "
				+ "converting string to date inputDate-> "+inputDate);	
		
		Date date = null;
		try {			
			if(!StringUtils.isBlank(inputDate)) {
				if(logger.isDebugEnabled()) logger.debug("Parsing the input date-month string "+inputDate);
				date = dateMonthFormatter.parse(inputDate);
			}			
		} catch (Exception e) {
			logger.error("Exception occured while "
					+ "converting string to date "+e.getMessage(),e);			
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()) logger.debug("Returning date-month after conversion, "
				+ " date object-> "+date);	
		
		return date;
	}
	
	public static String getDateMonthString(Date inputDate){
		
		if(logger.isDebugEnabled()) logger.debug("In getDateMonthString utility method "
				+ "converting date to string inputDate-> "+inputDate);	
		
		String date = null;
		try {			
			if(logger.isDebugEnabled()) logger.debug("Formatting the input date object "+inputDate);
			date = dateMonthFormatter.format(inputDate);			
		} catch (Exception e) {
			logger.error("Exception occured while "
					+ "converting date to string "+e.getMessage(),e);			
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()) logger.debug("Returning string date after conversion, "
				+ " date String-> "+date);	
		
		return date;
	}
	
	public static Date convert(LocalDate date, ZoneId zoneId){
		if(date == null)
			return null;
		else
			return	Date.from(date.atStartOfDay(zoneId).toInstant());
	}
}
