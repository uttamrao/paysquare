package com.ps.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
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

@Service("BusinessCycleAdhocMonthlyImpl")
public class BusinessCycleAdhocImpl implements BusinessCycleCommand {

	Logger logger = Logger.getLogger(BusinessCycleAdhocImpl.class);
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

		businessCycleList = new ArrayList<BusinessCycle>();

		LocalDate lastCreateCycleDate = generateCycles(businessYearFrom, businessYearTo, businessCycleDefinition,
				businessCycleBean);
		if (logger.isDebugEnabled())
			logger.debug("lastCreateCycleDate-> " + lastCreateCycleDate);

		if (logger.isDebugEnabled())
			logger.debug("Total cycles created ->" + businessCycleList.size());
		return businessCycleList;
	}

	LocalDate generateCycles(LocalDate cycleStartDate, LocalDate endCycleDate,
			BusinessCycleDefinition businessCycleDefinition, BusinessCycleBean businessCycleBean) {

		int period = 1;
		LocalDate nextCycleStartDate = cycleStartDate;

		do {
			LocalDate startDate = null;
			LocalDate endDate = null;

			startDate = nextCycleStartDate;
			endDate = startDate.plusDays(businessCycleDefinition.getReoccuranceDays());

			BusinessCycle cycle = BusinessCycleUtils.setCycle(startDate, endDate, businessCycleDefinition, period, 1,
					businessCycleBean);

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

}
