package com.ps.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
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

@Service("BusinessCycleBiWeeklyImpl")
public class BusinessCycleBiWeeklyImpl implements BusinessCycleCommand {

	Logger logger = Logger.getLogger(BusinessCycleBiWeeklyImpl.class);
	List<BusinessCycle> businessCycleList = null;

	@Override
	public List<BusinessCycle> create(BusinessCycleBean businessCycleBean) {

		if (logger.isDebugEnabled())
			logger.debug("In cycle creation method");

		BusinessCycleUtils.validate(businessCycleBean);

		BusinessCycleDefinition businessCycleDefinition = businessCycleBean.getBusinessCycleDefinition();
		BusinessYearDefinition businessYearDefinition = businessCycleDefinition.getBusinessYearDefinition();

		if (logger.isDebugEnabled())
			logger.debug(
					"Converting business year from and to dates to java8 LocalDate object, as all the operations will be performed using its methods");
		LocalDate businessYearFrom = LocalDateUtils.convert(businessYearDefinition.getFromDate(),
				ZoneId.systemDefault());
		LocalDate businessYearTo = LocalDateUtils.convert(businessYearDefinition.getToDate(), ZoneId.systemDefault());

		int currentYear = 0;
		if (businessCycleBean.getLastCreatedYear() != 0)
			currentYear = businessCycleBean.getLastCreatedYear();
		else
			currentYear = LocalDate.now().getYear();

		businessYearFrom = businessYearFrom.withYear(currentYear);
		businessYearTo = businessYearTo.withYear(currentYear);

		if (businessYearTo.compareTo(businessYearFrom) <= 0)
			businessYearTo = businessYearTo.plusYears(1);

		int duration = BusinessCycleUtils.getWeeksBetween(businessYearFrom, businessYearTo);
		int noOfCycles = BusinessCycleUtils.computeCycleCount(duration, 1, 2);
		businessCycleList = new ArrayList<BusinessCycle>();

		LocalDate lastCreateCycleDate = generateCycles(businessYearFrom, businessYearTo, noOfCycles,
				businessCycleDefinition, businessCycleBean);
		if (logger.isDebugEnabled())
			logger.debug("lastCreateCycleDate-> " + lastCreateCycleDate);

		if (duration < 26)
			lastCreateCycleDate = lastCreateCycleDate.plusYears(1);
		else
			lastCreateCycleDate = lastCreateCycleDate.plusDays(1);

		businessYearFrom = businessYearFrom.withYear(lastCreateCycleDate.getYear());
		businessYearTo = businessYearTo.plusYears(1);

		if (logger.isDebugEnabled())
			logger.debug("Total cycles created ->" + businessCycleList.size());
		for (BusinessCycle businessCycle : businessCycleList) {
			businessCycle.setNoOfCycles(businessCycleList.size());
		}
		return businessCycleList;
	}

	LocalDate generateCycles(LocalDate cycleStartDate, LocalDate endCycleDate, int noOfCycles,
			BusinessCycleDefinition businessCycleDefinition, BusinessCycleBean businessCycleBean) {

		int period = 1;
		LocalDate nextCycleStartDate = cycleStartDate;
		DayOfWeek startOfWeek = cycleStartDate.getDayOfWeek();
		DayOfWeek endOfWeek = LocalDateUtils.enOftheWeek(startOfWeek);

		do {
			// could get nextCycleStartDate from last added cycle's endDate from the
			// cycleList but instead of
			// traversing through the entire list just to get a date its better to store it
			// locally
			LocalDate startDate = null;
			LocalDate endDate = null;

			if (logger.isDebugEnabled())
				logger.debug("generation cycle for peiod--> " + period);

			if (period == 1)
				startDate = nextCycleStartDate;
			else
				startDate = nextCycleStartDate.with(TemporalAdjusters.previousOrSame(startOfWeek));

			if (period == noOfCycles && businessCycleDefinition.isForceToBusinessYearEnd())
				endDate = startDate.withDayOfMonth(endCycleDate.getDayOfMonth());
			else
				endDate = startDate.with(TemporalAdjusters.nextOrSame(endOfWeek)).plusWeeks(1);

			if (logger.isDebugEnabled())
				logger.debug("period-> " + period + "  end-> " + endDate);

			BusinessCycle cycle = BusinessCycleUtils.setCycle(startDate, endDate, businessCycleDefinition, period,
					noOfCycles, businessCycleBean);

			businessCycleList.add(cycle);
			if (logger.isDebugEnabled())
				logger.debug("No of cycle for businessYearDefinitionId-> " + cycle.getBusinessCycleDefinition().getId()
						+ " period-> " + cycle.getPeriodId() + " periodName-> " + cycle.getPeriodName() + " start -> "
						+ cycle.getFromDate() + ", end-> " + cycle.getToDate());

			nextCycleStartDate = endDate.plusDays(1);
			period++;

			if (logger.isDebugEnabled())
				logger.debug("nextCycleStartDate-> " + nextCycleStartDate + " period-> " + period);
		} while (endCycleDate.compareTo(nextCycleStartDate) >= 0);

		return nextCycleStartDate;
	}

	@Override
	public List<BusinessCycle> update(List<BusinessCycle> requestList) {
		// TODO Auto-generated method stub
		return null;
	}

}
