package com.ps.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.jboss.logging.Logger;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.FrequencyMaster;

public class BusinessCycleUtils {

	static Logger logger = Logger.getLogger(BusinessCycleUtils.class);

	public static int getMonths(LocalDate from, LocalDate to){
		
		if(to.compareTo(from) <= 0)
			to = to.plusYears(1);
		
		Period duration = Period.between(from, to);		
		int months = duration.getMonths()+1;	
		if(logger.isDebugEnabled())
			logger.debug("Duration of business year  between fromDate-> "+from+" and toDate-> "+to+" is noOfMonths-> "+months);
		
		return months;		
	}
	
	public static int getWeeks(LocalDate from, LocalDate to){
		
		if(to.compareTo(from) <= 0)
			to = to.plusYears(1);
		
		long weeks = ChronoUnit.WEEKS.between(from, to);

		if(logger.isDebugEnabled())
			logger.debug("No of weeks between fromDate-> "+from+" and toDate-> "+to+" is noOfweeks-> "+weeks);
		
		return (int)weeks;		
	}
	
	public static int computeCycleCount(int duration, FrequencyMaster frequencyMaster){
		
		if(duration < frequencyMaster.getPaymentFrequency())
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business year period is less than payment frequency!");  
		
		if(logger.isDebugEnabled())
			logger.debug("frequencyMaster PaymentCount "+frequencyMaster.getPaymentCount()+" frequencyMaster PaymentFrequency "+frequencyMaster.getPaymentFrequency());		
		
		double paymentCount = frequencyMaster.getPaymentCount();
		double paymentFrequency = frequencyMaster.getPaymentFrequency();
		
		double noOfCycles = (paymentCount / paymentFrequency) * duration;
		
		if(logger.isDebugEnabled())
			logger.debug("No of cycle for duration-> +"+duration+" is-> "+noOfCycles);
		
		return (int)noOfCycles;
	}

}
