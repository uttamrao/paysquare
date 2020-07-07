package com.ps.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.jboss.logging.Logger;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
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

	public static BusinessCycle setCycle(LocalDate startDate, LocalDate endDate, BusinessCycleDefinition businessCycleDefinition,
			int periodId) {
		
		BusinessCycle cycle = new BusinessCycle();			
		cycle.setFromDate(DateUtils.convert(startDate, ZoneId.systemDefault()));
		cycle.setToDate(DateUtils.convert(endDate, ZoneId.systemDefault()));
		cycle.setBusinessCycleDefinition(businessCycleDefinition);
		cycle.setPeriodId(periodId);			
		cycle.setPeriodName(setPeriodName(startDate,endDate,businessCycleDefinition.getName()));
		
		return cycle;
	}
	
	public static String setPeriodName(LocalDate startDate, LocalDate endDate, String  cycleDefinitionName) {
		
		StringBuilder periodNameBuilder =  new StringBuilder();
		periodNameBuilder.append(cycleDefinitionName);
		periodNameBuilder.append("_"+String.valueOf(startDate.getDayOfMonth()));
		periodNameBuilder.append(startDate.getMonth().name().substring(0,3));
		periodNameBuilder.append(String.valueOf(startDate.getYear()).substring(2));
		periodNameBuilder.append("_"+String.valueOf(endDate.getDayOfMonth()));
		periodNameBuilder.append(endDate.getMonth().name().substring(0,3));
		periodNameBuilder.append(String.valueOf(endDate.getYear()).substring(2));
		
		return periodNameBuilder.toString();
	}
	
	public static void validate(BusinessCycleBean businessCycleBean){
		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessCycleBean before creating cycles");
		
		if(businessCycleBean == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business cycle details not found!");  
		
		if(businessCycleBean.getBusinessCycleDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business cycle definition not found!");  
		
		if(businessCycleBean.getBusinessCycleDefinition().getBusinessYearDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business year definition not found!");  
		
		if(businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency not found!");		
	
		if(logger.isDebugEnabled())
			logger.debug("Returning, businessCycleBean is valid");
	}
	
}
