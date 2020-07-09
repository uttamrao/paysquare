package com.ps.services.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.services.BusinessCycleCommand;
import com.ps.util.BusinessCycleUtils;
import com.ps.util.LocalDateUtils;

@Service("BusinessCycleSemiMonthlyImpl")
public class BusinessCycleSemiMonthlyImpl implements BusinessCycleCommand {

	Logger logger = Logger.getLogger(BusinessCycleSemiMonthlyImpl.class);
	
	List<BusinessCycle> businessCycleList = null;
	
	@Override
	public List<BusinessCycle> create(BusinessCycleBean businessCycleBean) {		
		if(logger.isDebugEnabled())
			logger.debug("In cycle creation method ");
		
		BusinessCycleUtils.validate(businessCycleBean);
		
		BusinessCycleDefinition businessCycleDefinition = businessCycleBean.getBusinessCycleDefinition();  		
		BusinessYearDefinition businessYearDefinition = businessCycleDefinition.getBusinessYearDefinition(); 		
		
		if(logger.isDebugEnabled())
			logger.debug("Converting business year from and to dates to java8 LocalDate object, as all the operations will be performed using its methods");  
		LocalDate businessYearFrom = LocalDateUtils.convert(businessYearDefinition.getFromDate(), ZoneId.systemDefault());	
		LocalDate businessYearTo = LocalDateUtils.convert(businessYearDefinition.getToDate(), ZoneId.systemDefault());	
		
		int currentYear = 0;
		if(businessCycleBean.getLastCreatedYear() != 0)
			currentYear = businessCycleBean.getLastCreatedYear();
		else
			currentYear = LocalDate.now().getYear();
		
		businessYearFrom = businessYearFrom.withYear(currentYear);
		businessYearTo = businessYearTo.withYear(currentYear);
		
		if(businessYearTo.compareTo(businessYearFrom) <= 0)
			businessYearTo = businessYearTo.plusYears(1);
		
		int duration = BusinessCycleUtils.getMonthsBetween(businessYearFrom, businessYearTo);
		int noOfCycles = BusinessCycleUtils.computeCycleCount(duration, 2,1);	
		businessCycleList =  new ArrayList<BusinessCycle>();
		
		for (int i = 0; i < businessCycleBean.getNoOfYears(); i++) {
			
			LocalDate lastCreateCycleDate = generateCycles(businessYearFrom, businessYearTo, noOfCycles, businessCycleDefinition);		
			if (logger.isDebugEnabled())
				logger.debug("lastCreateCycleDate-> " + lastCreateCycleDate);
		
			if(duration < 12)
				lastCreateCycleDate = lastCreateCycleDate.plusYears(1);
			else 
				lastCreateCycleDate = lastCreateCycleDate.plusDays(1);
			
			businessYearFrom = businessYearFrom.withYear(lastCreateCycleDate.getYear());
			businessYearTo = businessYearTo.plusYears(1);			
		}
		
		if (logger.isDebugEnabled())
			logger.debug("Total cycles created ->" +businessCycleList.size());
		return businessCycleList;
	}
	
	LocalDate generateCycles(LocalDate cycleStartDate, LocalDate cycleEndDate, int noOfCycles, BusinessCycleDefinition businessCycleDefinition) {

		int period = 1;
		boolean createByDate = false;
		LocalDate nextCycleStartDate = cycleStartDate;
		LocalDate monthFirstCycleStartDate = cycleStartDate;
		
		if(cycleStartDate.getDayOfMonth() != 1)
			createByDate = true;
		
		do {
			//could get nextCycleStartDate from last added cycle's endDate from the cycleList but instead of
			// traversing through the entire list just to get a date its better to store it locally
			LocalDate startDate = null ;	
			LocalDate endDate = null;			

			//As every 2nd cycle i.e every even cycle will be till the end of the month and each odd cycle will be 15days
			//Month's 1st cycle is odd
			if(period % 2 != 0) {
				startDate = nextCycleStartDate;
				monthFirstCycleStartDate = startDate;
				TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));
				endDate = startDate.with(temporalAdjuster);
				nextCycleStartDate = endDate;
			}
			else {
				startDate = nextCycleStartDate.plusDays(1);
				
				if(!createByDate) {
					endDate =  startDate.withDayOfMonth(startDate.lengthOfMonth());	
					nextCycleStartDate = endDate.plusMonths(1).withDayOfMonth(1);
				}else {
					endDate =  monthFirstCycleStartDate.plusMonths(1).minusDays(1);	
					nextCycleStartDate = endDate.plusDays(1);
				}
			}
			
			BusinessCycle cycle = BusinessCycleUtils.setCycle(startDate, endDate, businessCycleDefinition, period);
			businessCycleList.add(cycle);
			if(logger.isDebugEnabled())
				logger.debug("No of cycle for businessYearDefinitionId-> "+cycle.getBusinessCycleDefinition().getId()
						+" period-> "+cycle.getPeriodId()
						+" periodName-> "+cycle.getPeriodName()					
						+" start -> "+cycle.getFromDate()+", end-> "+cycle.getToDate());
	
			period++;
		} while (cycleEndDate.compareTo(nextCycleStartDate) >= 0);
		
		return nextCycleStartDate;
	}

}
