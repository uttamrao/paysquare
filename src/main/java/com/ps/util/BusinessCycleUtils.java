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

public class BusinessCycleUtils {

	static Logger logger = Logger.getLogger(BusinessCycleUtils.class);

	public static int getMonthsBetween(LocalDate from, LocalDate to) {

		if (to.compareTo(from) <= 0)
			to = to.plusYears(1);

		Period duration = Period.between(from, to);
		int months = duration.getMonths() + 1;
		if (logger.isDebugEnabled())
			logger.debug("Duration of business year  between fromDate-> " + from + " and toDate-> " + to
					+ " is noOfMonths-> " + months);

		return months;
	}

	public static int getDaysBetween(LocalDate from, LocalDate to) {

		if (to.compareTo(from) <= 0)
			to = to.plusYears(1);

		long days = (int) ChronoUnit.DAYS.between(from, to);

		if (logger.isDebugEnabled())
			logger.debug("Duration of business year  between fromDate-> " + from + " and toDate-> " + to
					+ " is noOfDays-> " + days);

		return (int) days;
	}

	public static int getWeeksBetween(LocalDate from, LocalDate to) {

		if (to.compareTo(from) <= 0)
			to = to.plusYears(1);

		long weeks = ChronoUnit.WEEKS.between(from, to);

		if (logger.isDebugEnabled())
			logger.debug("No of weeks between fromDate-> " + from + " and toDate-> " + to + " is noOfweeks-> " + weeks);

		return (int) weeks;
	}

	public static int computeCycleCount(double duration, double paymentCount, double frequency) {

		if (duration < frequency)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
					"Business year period is less than payment frequency!");

		if (logger.isDebugEnabled())
			logger.debug(
					"PaymentCount " + paymentCount + " PaymentFrequency " + frequency + " for duration " + duration);

		double noOfCycles = (paymentCount / frequency) * duration;

		if (logger.isDebugEnabled())
			logger.debug("No of cycle for duration-> +" + duration + " is-> " + noOfCycles);

		return (int) noOfCycles;
	}

	public static BusinessCycle setCycle(LocalDate startDate, LocalDate endDate,
			BusinessCycleDefinition businessCycleDefinition, int periodId, int noOfCycles,
			BusinessCycleBean businessCycleBean) {

		BusinessCycle cycle = new BusinessCycle();
		cycle.setFromDate(DateUtils.convert(startDate, ZoneId.systemDefault()));
		cycle.setToDate(DateUtils.convert(endDate, ZoneId.systemDefault()));
		cycle.setBusinessCycleDefinition(businessCycleDefinition);
		cycle.setPeriodId(periodId);
		cycle.setPeriodName(setPeriodName(startDate, endDate, businessCycleDefinition.getName(), periodId));

		int datediff = (int) ChronoUnit.DAYS.between(startDate, endDate);
		cycle.setNoOfDays(datediff);

		cycle.setNoOfCycles(noOfCycles);
		cycle.setActive(true);
		cycle.setBusinessYear(businessCycleBean.getBusinessYear());
		return cycle;
	}

	public static String setPeriodName(LocalDate startDate, LocalDate endDate, String cycleDefinitionName,
			int periodId) {

		StringBuilder periodNameBuilder = new StringBuilder();
		periodNameBuilder.append(cycleDefinitionName);
		// periodNameBuilder.append("_" + String.valueOf(startDate.getDayOfMonth()));
		// periodNameBuilder.append(startDate.getMonth().name().substring(0, 3));
		// periodNameBuilder.append(String.valueOf(startDate.getYear()).substring(2));
//		periodNameBuilder.append("_" + String.valueOf(endDate.getDayOfMonth()));
//		periodNameBuilder.append(endDate.getMonth().name().substring(0, 3));
//		periodNameBuilder.append(String.valueOf(endDate.getYear()).substring(2));
		periodNameBuilder.append("_" + String.valueOf(periodId));

		return periodNameBuilder.toString();
	}

	public static void validate(BusinessCycleBean businessCycleBean) {

		if (logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean, object-> " + businessCycleBean);
		if (businessCycleBean == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle details not found!");

		if (logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean cycle definition, object-> "
					+ businessCycleBean.getBusinessCycleDefinition());
		if (businessCycleBean.getBusinessCycleDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle definition not found!");

		if (logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean cycle definition, object-> "
					+ businessCycleBean.getBusinessCycleDefinition());
		if (businessCycleBean.getBusinessCycleDefinition().getBusinessYearDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business year definition not found!");

		if (logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean cycle definition, object-> "
					+ businessCycleBean.getBusinessCycleDefinition());
		if (businessCycleBean.getBusinessCycleDefinition().getReoccuranceDays() == 0) {
			if (logger.isDebugEnabled())
				logger.debug("Validating if frequency is set in businessCycleDefinition, object-> "
						+ businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster());
			if (businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster() == null)
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle frequency not found!");
		}
	}

}
