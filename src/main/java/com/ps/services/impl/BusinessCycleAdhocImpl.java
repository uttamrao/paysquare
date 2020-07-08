package com.ps.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.services.BusinessCycleCommand;
import com.ps.util.BusinessCycleUtils;
import com.ps.util.DateUtils;
import com.ps.util.LocalDateUtils;

@Service("BusinessCycleAdhocMonthlyImpl")
public class BusinessCycleAdhocImpl implements BusinessCycleCommand {

	Logger logger = Logger.getLogger(BusinessCycleAdhocImpl.class);
	
	@Override
	public List<BusinessCycle> create(BusinessCycleBean businessCycleBean) {			
		if(logger.isDebugEnabled())
			logger.debug("In cycle creation method");
		
		validate(businessCycleBean);
		
		BusinessCycleDefinition businessCycleDefinition = businessCycleBean.getBusinessCycleDefinition();  		
		BusinessYearDefinition businessYearDefinition = businessCycleDefinition.getBusinessYearDefinition(); 		
		FrequencyMaster frequencyMaster = businessCycleDefinition.getFrequencyMaster();
		 
		LocalDate businessYearFrom = LocalDateUtils.convert(businessYearDefinition.getFromDate(), ZoneId.systemDefault());	
		LocalDate businessYearTo = LocalDateUtils.convert(businessYearDefinition.getToDate(), ZoneId.systemDefault());	
		
		int noOfCycles = getTotalCycleCount(businessYearFrom, businessYearTo, frequencyMaster);
		int period = 1;
		boolean create = true;		
		List<BusinessCycle> cycleList = new ArrayList<BusinessCycle>();		
		int currentYear = 0;
		if(businessCycleBean.getLastCreatedYear() != 0)
			currentYear = businessCycleBean.getLastCreatedYear();
		else
			currentYear = LocalDate.now().getYear();		
		
		LocalDate nextCycleStartDate = LocalDate.of(currentYear, businessYearFrom.getMonth(), businessYearFrom.getDayOfMonth());
		
		while(create) {
			
			if(period >= noOfCycles)
				create = false;			
			//could get nextCycleStartDate from last added cycle's endDate from the cycleList but instead of
			// traversing through the entire list just to get a date its better to store it locally
			
			LocalDate startDate = nextCycleStartDate;
			LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
			
			BusinessCycle cycle = new BusinessCycle();			
			cycle.setFromDate(DateUtils.convert(startDate, ZoneId.systemDefault()));
			cycle.setToDate(DateUtils.convert(endDate, ZoneId.systemDefault()));
			cycle.setBusinessCycleDefinition(businessCycleBean.getBusinessCycleDefinition());
			cycle.setPeriodId(period);			
			cycle.setPeriodName(endDate.getMonth().name());
			cycleList.add(cycle);
			if(logger.isDebugEnabled())
				logger.debug("No of cycle for businessYearDefinitionId-> "+cycle.getBusinessCycleDefinition().getId()
						+" period-> "+cycle.getPeriodId()
						+" periodName-> "+cycle.getPeriodName()					
						+" start -> "+cycle.getFromDate()+", end-> "+cycle.getToDate());
			
			nextCycleStartDate = endDate.plusMonths(1).withDayOfMonth(1);
			period++;
			
			if(logger.isDebugEnabled())
				logger.debug("nextCycleStartDate-> "+nextCycleStartDate
						+" period-> "+period);
		}
		
		return null;
	}
	
	int getTotalCycleCount(LocalDate from, LocalDate to, FrequencyMaster frequencyMaster){
		if(logger.isDebugEnabled())
			logger.debug("Converting business year from and to dates to java8 LocalDate object, as all the operations will be performed using its methods");  
			
		if(to.getMonthValue() < from.getMonthValue())
			to = from.plusYears(1);
		
		Period duration = Period.between(from, to);		
		int noOfMonths = duration.getMonths()+1;	
		if(logger.isDebugEnabled())
			logger.debug("Duration of business year from-to is-> "+noOfMonths);
		int noOfCycles = BusinessCycleUtils.computeCycleCount(noOfMonths, 2,1);
		if(logger.isDebugEnabled())
			logger.debug("No of cycle for business year fromTime-> "+from+", toTime->"+to+" is-> "+noOfCycles);
		
		return noOfCycles;
	}
	
	void validate(BusinessCycleBean businessCycleBean){
		
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
