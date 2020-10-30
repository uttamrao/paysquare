package com.ps.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.enums.FrequencyEnum;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.services.BusinessCycleCommand;
import com.ps.services.BusinessCycleInvoker;
import com.ps.services.BusinessCycleService;
import com.ps.services.dao.repository.tenant.BusinessCycleDefinitionRepository;
import com.ps.services.dao.repository.tenant.BusinessCycleRepository;
import com.ps.util.RequestUtils;

@Service
public class BusinessCycleServiceImpl implements BusinessCycleService {

	Logger logger = Logger.getLogger(BusinessCycleServiceImpl.class);

	@Autowired
	BusinessCycleRepository businessCycleRepository;

	@Autowired
	BusinessCycleDefinitionRepository businessCycleDefinitionRepository;

	@Autowired
	RequestUtils requestUtils;

	@Autowired
	BusinessCycleInvoker businessCycleInvoker;

	static Map<FrequencyEnum, BusinessCycleCommand> businessCycleCommandObject = null;

	static {
		if (CollectionUtils.isEmpty(businessCycleCommandObject)) {
			businessCycleCommandObject = new HashMap<FrequencyEnum, BusinessCycleCommand>();
			businessCycleCommandObject.put(FrequencyEnum.MONTHLY, new BusinessCycleMonthlyImpl());
			businessCycleCommandObject.put(FrequencyEnum.SEMI_MONTHLY, new BusinessCycleSemiMonthlyImpl());
			businessCycleCommandObject.put(FrequencyEnum.WEEKLY, new BusinessCycleWeeklyImpl());
			businessCycleCommandObject.put(FrequencyEnum.BI_WEEKLY, new BusinessCycleBiWeeklyImpl());
			businessCycleCommandObject.put(FrequencyEnum.ADHOC, new BusinessCycleAdhocImpl());
		}
	}

	@Override
	@Transactional("tenantTransactionManager")
	public List<BusinessCycle> add(BusinessCycleBean businessCycleBean) {

		if (logger.isDebugEnabled())
			logger.debug("In add BusinessCycleDefinition");
		validate(businessCycleBean);

		FrequencyEnum frequency = null;

		if (businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster() != null)
			frequency = businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster().getName();
		else
			frequency = FrequencyEnum.ADHOC;

		if (logger.isDebugEnabled())
			logger.debug("Initializing businessCycle creation for frequency-> " + frequency);

		// check last created cycle year and create cycle from that year till the
		// noOfYears requested
		// i.e set start year to the year last cycle was created
		// based on the the duration it should be decided if cycle should be created for
		// this year or next
		// Optional<BusinessCycle> businessCycle = businessCycleRepository
		// .findTopByBusinessCycleDefinitionOrderByFromDateDesc(businessCycleBean.getBusinessCycleDefinition());
		// if (businessCycle.isPresent()) {
		// LocalDate fromDate = LocalDateUtils.convert(businessCycle.get().getToDate(),
		// ZoneId.systemDefault());
		// businessCycleBean.setLastCreatedYear(fromDate.plusDays(1).getYear());

		// LocalDate fromDate = LocalDateUtils.convert(businessCycle.get().getToDate(),
		// ZoneId.systemDefault());
		businessCycleBean.setLastCreatedYear(Integer.parseInt(businessCycleBean.getBusinessYear()));
		// }

		BusinessCycleCommand businessCycleCommand = businessCycleCommandObject.get(frequency);
		List<BusinessCycle> cycleList = businessCycleInvoker.createCycle(businessCycleCommand, businessCycleBean);

		if (logger.isDebugEnabled())
			logger.debug("Saving  cycles->" + cycleList.size() + " into db");

		if (!CollectionUtils.isEmpty(cycleList)) {
			businessCycleRepository.saveAll(cycleList);

			// set isUsed field from business cycle definition=1
			setBusinessCycleDefinitionToTrue(businessCycleBean.getBusinessCycleDefinition());

			if (logger.isDebugEnabled())
				logger.debug("Business Cycle Definition saved into DB successfully");
		}
		return cycleList;
	}

	private void setBusinessCycleDefinitionToTrue(BusinessCycleDefinition businessCycleDefinition) {

		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(businessCycleDefinition.getId(), true);

		if (businessCycleDefinitionOptional.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");

		businessCycleDefinitionOptional.get().setIsUsed(true);
		businessCycleDefinitionRepository.save(businessCycleDefinitionOptional.get());

		if (logger.isDebugEnabled())
			logger.debug("Business cycle Definition IsUsed field updated in table to: true");
	}

	private void validate(BusinessCycleBean businessCycleBean) {

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

		if (logger.isDebugEnabled())
			logger.debug("Validating cycle alreday exist for , business cycle def id-> "
					+ businessCycleBean.getBusinessCycleDefinition().getId() + " and business year-->"
					+ businessCycleBean.getBusinessYear());
		List<BusinessCycle> businessCycleList = businessCycleRepository.findAllByCycleDefinitionIdAndBusinessYear(
				businessCycleBean.getBusinessCycleDefinition().getId(), businessCycleBean.getBusinessYear());

		if (!businessCycleList.isEmpty())
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle already exist");

		if (logger.isDebugEnabled())
			logger.debug("Business cycle creation object validated successfully");
	}

	@Override
	@Transactional("tenantTransactionManager")
	public List<BusinessCycle> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle getAll");

		// List<BusinessCycle> businessCycleList = businessCycleRepository.findAll();
		List<BusinessCycle> businessCycleList = businessCycleRepository.findAllByPeriodIdByIsActive();
		return businessCycleList;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public List<BusinessCycle> getAllByCycleDefinition(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle Definition getByCycleDefinition method, businessCycleDefinitionId-> " + id);
		if (id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		List<BusinessCycle> businessCycleList = businessCycleRepository.findAllByCycleDefinitionId(id);

		if (businessCycleList.isEmpty())
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition not found!");

		return businessCycleList;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public List<BusinessCycle> getAllByBusinessYearDefinitionId(int id) {

		if (logger.isDebugEnabled())
			logger.debug(
					"In BusinessCycleDefinition getAllByBusinessYearDefinitionId method, BusinessYearDefinitionId-> "
							+ id);
		if (id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business year definition is Invalid!");

		List<BusinessCycle> businessCycleList = businessCycleRepository.findAllByBusinessYearDefinitionId(id);

		return businessCycleList;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void deleteAllByCycleDefinitionIds(List<Integer> ids) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle deleteByCycleDefinitionIds method, businessCycleDefinitionId-> " + ids);
		if (CollectionUtils.isEmpty(ids))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		businessCycleRepository.deleteAllByBusinessCycleDefinitionIds(ids);
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void deleteAllByCycleDefinitionId(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle deleteByCycleDefinitionId method, businessCycleDefinitionId-> " + id);
		if (id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		List<Integer> cycleDefinitionId = new ArrayList<Integer>();
		cycleDefinitionId.add(id);
		businessCycleRepository.deleteAllByBusinessCycleDefinitionIds(cycleDefinitionId);
	}

	/** GET by Id ***/

	@Override
	@Transactional("tenantTransactionManager")
	public List<BusinessCycle> getAllByBusinessCycleIdAndBusinessYear(int cycleDefinitionId, String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle creation getByCycleDefinition method, businessCycleDefinitionId-> "
					+ cycleDefinitionId);
		if (cycleDefinitionId == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(cycleDefinitionId, true);
		if (businessCycleDefinitionOptional.isEmpty()) {
			logger.error("Business Cycle Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");
		}

		if (businessYear == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business businessYear is Invalid!");

		List<BusinessCycle> businessCycleList = businessCycleRepository
				.findAllByCycleDefinitionIdAndBusinessYear(cycleDefinitionId, businessYear);

		if (businessCycleList.isEmpty()) {
			logger.error("Business Cycle creation not found!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle creation not found!");
		}

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle creation found");
		return businessCycleList;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void softDeleteByBusinessCycleIdAndBusinessYear(int cycleDefinitionId, String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug(
					"In BusinessCycle Definition softDeleteByBusinessCycleIdAndBusinessYear method, businessCycleDefinitionId-> "
							+ cycleDefinitionId);
		if (cycleDefinitionId == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(cycleDefinitionId, true);
		if (businessCycleDefinitionOptional.isEmpty()) {
			logger.error("Business Cycle Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");
		}

		if (businessYear == null) {
			logger.error("Business Year is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year is Invalid!");
		}

		List<BusinessCycle> businessCycleList = businessCycleRepository
				.findAllByCycleDefinitionIdAndBusinessYear(cycleDefinitionId, businessYear);

		if (businessCycleList.isEmpty()) {
			logger.error("Business Cycle Creation not found!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Creation not found!");
		}

		for (BusinessCycle businessCycle : businessCycleList) {
			if (businessCycle.isLocked()) {
				logger.error("Business Cycle Creation not delete!");
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Creation can not delete!");
			}

			if (businessCycle.isUsed()) {
				logger.error("Business Cycle Creation not delete!");
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Creation can not delete!");
			}
		}

		List<BusinessCycle> businessCycles = businessCycleRepository
				.findAllByBusinessYearDefinitionIdAndIsUsed(cycleDefinitionId);
		if (!businessCycles.isEmpty()) {
			logger.error("Business year is used and Cycle Creation not delete!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Creation can not delete!");
		}

		businessCycleRepository.softdeleteByCycleDefinitionIdAndBusinessYear(cycleDefinitionId, businessYear);
		// before updating business cycle definition isUsed from business year set=0 if
		// year is not used any other cycle
		setBusinessCycleDefinitionToFalse(businessCycleDefinitionOptional.get());
		if (logger.isDebugEnabled())
			logger.debug("Soft deleted business cycle creation record with business cycle creation id-> "
					+ cycleDefinitionId);
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void hardDeleteByBusinessCycleIdAndBusinessYear(int cycleDefinitionId, String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug(
					"In BusinessCycle Definition hardDeleteByBusinessCycleIdAndBusinessYear method, businessCycleDefinitionId-> "
							+ cycleDefinitionId);
		if (cycleDefinitionId == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(cycleDefinitionId, true);
		if (businessCycleDefinitionOptional.isEmpty()) {
			logger.error("Business Cycle Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");
		}

		if (businessYear == null) {
			logger.error("Business Year is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year is Invalid!");
		}

		List<BusinessCycle> businessCycleList = businessCycleRepository
				.findAllByCycleDefinitionIdAndBusinessYear(cycleDefinitionId, businessYear);

		if (businessCycleList.isEmpty()) {
			logger.error("Business Cycle Creation not found!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Creation not found!");
		}

		for (BusinessCycle businessCycle : businessCycleList) {
			if (businessCycle.isLocked()) {
				logger.error("Business Cycle Creation not delete!");
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Creation not delete!");
			}
		}

		businessCycleRepository.hardDeleteByCycleDefinitionIdAndBusinessYear(cycleDefinitionId, businessYear);

		// before updating business cycle definition isUsed from business year set=0 if
		// year is not used any other cycle
		setBusinessCycleDefinitionToFalse(businessCycleDefinitionOptional.get());

		if (logger.isDebugEnabled())
			logger.debug("Hard deleted business cycle creation record with business cycle creation id-> "
					+ cycleDefinitionId);
	}

	private void setBusinessCycleDefinitionToFalse(BusinessCycleDefinition businessCycleDefinition) {

		if (logger.isDebugEnabled())
			logger.debug("In setBusinessCycleDefinitionToFalse method");

		List<BusinessCycle> cycles = businessCycleRepository
				.findByBusinessCycleDefinitionIdUsed(businessCycleDefinition.getId());

		if (cycles.isEmpty()) {
			if (logger.isDebugEnabled())
				logger.debug("Inside loop");
			Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
					.findByIdAndIsActive(businessCycleDefinition.getId(), true);

			if (businessCycleDefinitionOptional.isEmpty())
				throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");

			businessCycleDefinitionOptional.get().setIsUsed(false);
			businessCycleDefinitionRepository.save(businessCycleDefinitionOptional.get());

			if (logger.isDebugEnabled())
				logger.debug("Business cycle Definition IsUsed field updated in table to: false");
		}

	}

	@Override
	@Transactional("tenantTransactionManager")
	public List<BusinessCycle> update(List<BusinessCycle> requestList, int cycleDefinitionId, String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In add BusinessCycleDefinition");
		// validate(businessCycleBean);

		FrequencyEnum frequency = null;

		if (requestList.get(0).getBusinessCycleDefinition().getFrequencyMaster() != null)
			frequency = requestList.get(0).getBusinessCycleDefinition().getFrequencyMaster().getName();
		else
			frequency = FrequencyEnum.ADHOC;

		if (logger.isDebugEnabled())
			logger.debug("Initializing businessCycle creation for frequency-> " + frequency);

		BusinessCycleCommand businessCycleCommand = businessCycleCommandObject.get(frequency);
		List<BusinessCycle> cycleList = businessCycleInvoker.updateCycle(businessCycleCommand, requestList);

		if (logger.isDebugEnabled())
			logger.debug("Updating  cycles->" + cycleList.size() + " into db");

		if (!CollectionUtils.isEmpty(cycleList)) {
			businessCycleRepository.saveAll(cycleList);

			if (logger.isDebugEnabled())
				logger.debug("Business Cycle Definition updated into DB successfully");
		}
		return cycleList;
	}

}
