package com.ps.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
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
import com.ps.services.BusinessCycleCommand;
import com.ps.util.BusinessCycleUtils;
import com.ps.util.DateUtils;
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
		businessCycleList = new ArrayList<>();

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
					noOfCycles, businessCycleBean.getBusinessYear());

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
	public List<BusinessCycle> update(List<BusinessCycle> oldCycleList, List<BusinessCycle> requestList) {
		if (logger.isDebugEnabled())
			logger.debug("In cycle update method");

		for (int i = 0; i < requestList.size(); i++) {

			// If to date is modified then update the values
			if (!requestList.get(i).getToDate().equals(oldCycleList.get(i).getToDate())) {

				// If business cycle is locked then can not update the cycle
				if (!oldCycleList.get(i).isLocked()) {
					if (logger.isDebugEnabled())
						logger.debug("To date is upadetd for cycle--> " + requestList.get(i).getToDate());

					if (!requestList.get(0).isAdjustedToNextCycle()) {
						if (logger.isDebugEnabled())
							logger.debug("Days are not adjusted to next cycle, so only updating to date--> "
									+ requestList.get(i).getToDate());
						BusinessCycle cycle = oldCycleList.get(i);
						cycle.setToDate(requestList.get(i).getToDate());
						cycle.setAdjustedToNextCycle(requestList.get(i).isAdjustedToNextCycle());
						cycle.setRemark(requestList.get(i).getRemark());

						int datediff = BusinessCycleUtils.calculateNoOfDays(requestList.get(i).getFromDate(),
								requestList.get(i).getToDate());
						cycle.setNoOfDays(datediff + 1);

						if (logger.isDebugEnabled())
							logger.debug("Number of days updated to --> " + datediff + 1);
						break;
					}
					if (requestList.get(0).isAdjustedToNextCycle()) {
						if (logger.isDebugEnabled())
							logger.debug("Days are adjusted to next cycle, so updating to date of current cycle--> "
									+ requestList.get(i).getToDate());
						BusinessCycle cycle = oldCycleList.get(i);
						cycle.setToDate(requestList.get(i).getToDate());
						cycle.setAdjustedToNextCycle(requestList.get(i).isAdjustedToNextCycle());
						cycle.setRemark(requestList.get(i).getRemark());

						int datediff = BusinessCycleUtils.calculateNoOfDays(requestList.get(i).getFromDate(),
								requestList.get(i).getToDate());
						cycle.setNoOfDays(datediff + 1);

						if (logger.isDebugEnabled())
							logger.debug("Number of days updated to --> " + datediff + 1);

						// updating successive from date=to date+1
						if (logger.isDebugEnabled())
							logger.debug(
									"Previous from date of next cycle --> " + requestList.get(i + 1).getFromDate());
						LocalDate fromLocalDate = DateUtils.convertDateToLocalDate(requestList.get(i).getToDate());
						fromLocalDate = fromLocalDate.plusDays(1);

						BusinessCycle nextCycle = oldCycleList.get(i + 1);
						nextCycle.setFromDate(DateUtils.convertLocalDateToDate(fromLocalDate));

						datediff = BusinessCycleUtils.calculateNoOfDays(nextCycle.getFromDate(), nextCycle.getToDate());
						nextCycle.setNoOfDays(datediff + 1);

						if (logger.isDebugEnabled())
							logger.debug("Number of days updated to --> " + datediff + 1);
						if (logger.isDebugEnabled())
							logger.debug("updated next cycle from date--> " + nextCycle.getFromDate());
						break;
					}
				} else {
					logger.error("Business Cycle Definition is locked and not able to update the cycle!");
					throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND,
							"Business Cycle Definition is locked and can not update!");
				}
			}
		}
		return oldCycleList;
	}

	@Override
	public List<BusinessCycle> forceToYearEnd(List<BusinessCycle> oldCycleList, List<BusinessCycle> requestList) {
		if (logger.isDebugEnabled())
			logger.debug("In cycle forceToYearEnd method");

		BusinessYearDefinition businessYearDefinition = oldCycleList.get(0).getBusinessCycleDefinition()
				.getBusinessYearDefinition();

		LocalDate businessYearTo = LocalDateUtils.convert(businessYearDefinition.getToDate(), ZoneId.systemDefault());

		for (int i = 0; i < requestList.size(); i++) {
			// If to date is modified then update the values
			if (!requestList.get(i).getToDate().equals(oldCycleList.get(i).getToDate())) {
				if (logger.isDebugEnabled())
					logger.debug("To date is updated for cycle--> " + requestList.get(i));

				// if last cycles to date is modified then update to date as it is
				if (i == requestList.size() - 1) {
					if (logger.isDebugEnabled())
						logger.debug("Last cycle to date is modified to--> " + requestList.get(i).getToDate());

					BusinessCycle cycle = updatingCurrentCycle(i, oldCycleList, requestList);

					if (logger.isDebugEnabled())
						logger.debug("Number of days updated to --> " + cycle.getNoOfDays());
					break;
				} else {

					if (!requestList.get(0).isAdjustedToNextCycle()) {
						if (logger.isDebugEnabled())
							logger.debug("Days are not adjusted to next cycle, so only updating to date--> "
									+ requestList.get(i).getToDate());

						BusinessCycle cycle = updatingCurrentCycle(i, oldCycleList, requestList);

						if (logger.isDebugEnabled())
							logger.debug("Number of days updated to --> " + cycle.getNoOfDays());

					}
					if (requestList.get(0).isAdjustedToNextCycle()) {
						if (logger.isDebugEnabled())
							logger.debug("Days are adjusted to next cycle, so updating to date of current cycle--> "
									+ requestList.get(i).getToDate());

						BusinessCycle cycle = updatingCurrentCycle(i, oldCycleList, requestList);

						if (logger.isDebugEnabled())
							logger.debug("Number of days updated to --> " + cycle.getNoOfDays());

						// updating successive from date=to date+1
						if (logger.isDebugEnabled())
							logger.debug(
									"Previous from date of next cycle --> " + requestList.get(i + 1).getFromDate());

						BusinessCycle nextCycle = updatingNextCycle(i, oldCycleList, requestList);

						if (logger.isDebugEnabled())
							logger.debug("Number of days updated to --> " + nextCycle.getNoOfDays());
						if (logger.isDebugEnabled())
							logger.debug("updated next cycle from date--> " + nextCycle.getFromDate());

					}
					// updating last cycle to date= end date of year
					BusinessCycle lastCycle = updateLastCycle(requestList.size() - 1, oldCycleList, requestList,
							businessYearTo);

					if (logger.isDebugEnabled())
						logger.debug("updated last cycle to date--> " + lastCycle.getToDate());
					if (logger.isDebugEnabled())
						logger.debug("Number of days updated to --> " + lastCycle.getNoOfDays());

					break;
				}
			}

			// if to date is not modified then modified last cycle to date= end date of year
			if (i == requestList.size() - 1) {
				if (logger.isDebugEnabled())
					logger.debug("To date is not modified hence modified last cycle date");

				BusinessCycle cycle = updateLastCycle(i, oldCycleList, requestList, businessYearTo);

				if (logger.isDebugEnabled())
					logger.debug("To date is not modified hence modified last cycle date--> " + cycle.getToDate());
				if (logger.isDebugEnabled())
					logger.debug("Number of days updated to --> " + cycle.getNoOfDays());
			}
		}
		return oldCycleList;
	}

	public BusinessCycle updatingCurrentCycle(int i, List<BusinessCycle> oldCycleList,
			List<BusinessCycle> requestList) {
		BusinessCycle cycle = oldCycleList.get(i);
		cycle.setToDate(requestList.get(i).getToDate());
		cycle.setAdjustedToNextCycle(requestList.get(i).isAdjustedToNextCycle());
		cycle.setRemark(requestList.get(i).getRemark());

		int datediff = BusinessCycleUtils.calculateNoOfDays(requestList.get(i).getFromDate(),
				requestList.get(i).getToDate());
		cycle.setNoOfDays(datediff + 1);
		return cycle;
	}

	public BusinessCycle updatingNextCycle(int i, List<BusinessCycle> oldCycleList, List<BusinessCycle> requestList) {
		LocalDate fromLocalDate = DateUtils.convertDateToLocalDate(requestList.get(i).getToDate());
		fromLocalDate = fromLocalDate.plusDays(1);

		BusinessCycle nextCycle = oldCycleList.get(i + 1);
		nextCycle.setFromDate(DateUtils.convertLocalDateToDate(fromLocalDate));

		int datediff = BusinessCycleUtils.calculateNoOfDays(nextCycle.getFromDate(), nextCycle.getToDate());
		nextCycle.setNoOfDays(datediff + 1);

		return nextCycle;
	}

	public BusinessCycle updateLastCycle(int i, List<BusinessCycle> oldCycleList, List<BusinessCycle> requestList,
			LocalDate businessYearTo) {
		BusinessCycle cycle = oldCycleList.get(i);
		LocalDate startDate = LocalDateUtils.convert(requestList.get(i).getFromDate(), ZoneId.systemDefault());

		LocalDate endDate = startDate.withDayOfMonth(businessYearTo.getDayOfMonth());
		cycle.setToDate(DateUtils.convert(endDate, ZoneId.systemDefault()));
		cycle.setRemark(requestList.get(i).getRemark());

		int datediff = BusinessCycleUtils.calculateNoOfDays(cycle.getFromDate(), cycle.getToDate());
		cycle.setNoOfDays(datediff + 1);

		return cycle;
	}

}
