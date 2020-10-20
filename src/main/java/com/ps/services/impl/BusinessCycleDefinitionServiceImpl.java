package com.ps.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.enums.ServiceTypeEnum;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.services.BusinessCycleDefinitionService;
import com.ps.services.BusinessCycleService;
import com.ps.services.dao.repository.tenant.BusinessCycleDefinitionRepository;
import com.ps.services.dao.repository.tenant.BusinessCycleRepository;
import com.ps.services.dao.repository.tenant.BusinessYearDefinitionRepository;
import com.ps.util.RequestUtils;

@Service
public class BusinessCycleDefinitionServiceImpl implements BusinessCycleDefinitionService {

	Logger logger = Logger.getLogger(BusinessCycleDefinitionServiceImpl.class);

	@Autowired
	BusinessCycleDefinitionRepository businessCycleDefinitionRepository;

	@Autowired
	BusinessYearDefinitionRepository businessYearDefinitionRepository;

	@Autowired
	BusinessCycleRepository businessCycleRepository;

	@Autowired
	BusinessCycleService businessCycleService;

	@Autowired
	RequestUtils requestUtils;

	@Override
	@Transactional("tenantTransactionManager")
	public BusinessCycleDefinition add(BusinessCycleDefinition businessCycleDefinition) {
		if (logger.isDebugEnabled())
			logger.debug("In add BusinessCycleDefinition");

		validate(businessCycleDefinition);
		if (logger.isDebugEnabled())
			logger.debug("BusinessCycleDefinition data is valid");

		ServiceTypeEnum serviceType = ServiceTypeEnum.valueOf(businessCycleDefinition.getServiceName().toUpperCase());
		String shortCode = "";
		String frequncyName = "";
		if (businessCycleDefinition.getFrequencyMaster() != null) {
			shortCode = businessCycleDefinition.getFrequencyMaster().getName().getShortCode();
			frequncyName = businessCycleDefinition.getFrequencyMaster().getName().getValue();
		}

		String description = businessCycleDefinition.getName() + " " + frequncyName + " " + serviceType.getValue() + " "
				+ "Cycle";
		businessCycleDefinition.setDescription(description);

		String cycleName = businessCycleDefinition.getName() + "_" + shortCode + "_" + serviceType.getShortCode();
		businessCycleDefinition.setName(cycleName);

		businessCycleDefinition.setServiceName(serviceType.getValue());
		businessCycleDefinition.setCreatedBy("Anagha");
		businessCycleDefinition.setActive(true);

		// set isUsed field from business year definition=1
		setBusinessYear(businessCycleDefinition);

		try {
			businessCycleDefinitionRepository.save(businessCycleDefinition);
		} catch (DataIntegrityViolationException e) {
			logger.error("Business year Definition, Frequency master and service name combination should be unique");
			logger.error("Business Cycle Definition already exist");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition already exist");
		}
		if (logger.isDebugEnabled())
			logger.debug("BusinessCycleDefinition saved into DB");

		return businessCycleDefinition;
	}

	private void setBusinessYear(BusinessCycleDefinition businessCycleDefinition) {
		Optional<BusinessYearDefinition> businessYearOptional = businessYearDefinitionRepository
				.findByIdAndIsActive(businessCycleDefinition.getBusinessYearDefinition().getId(), true);

		if (businessYearOptional.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Year Definition not found!");

		businessYearOptional.get().setIsUsed(true);
		businessYearDefinitionRepository.save(businessYearOptional.get());

		if (logger.isDebugEnabled())
			logger.debug("Business year Definition IsUsed field updated in table to: true");
	}

	private void validate(BusinessCycleDefinition businessCycleDefinition) {

		if (logger.isDebugEnabled())
			logger.debug("In validate method, Validating businessCycleDefinitions");

		if (logger.isDebugEnabled())
			logger.debug("Validating businessCycleDefinition, object-> " + businessCycleDefinition);
		if (businessCycleDefinition == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition not found!");

		if (businessCycleDefinition.getReoccuranceDays() == 0) {
			if (logger.isDebugEnabled())
				logger.debug("Validating if frequency is set in businessCycleDefinition, object-> "
						+ businessCycleDefinition.getFrequencyMaster());
			if (businessCycleDefinition.getFrequencyMaster() == null)
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency not found!");
		}

		if (logger.isDebugEnabled())
			logger.debug("Validating if businessYear is set in businessCycleDefinition, object-> "
					+ businessCycleDefinition.getBusinessYearDefinition());
		if (businessCycleDefinition.getBusinessYearDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year not found!");

		System.out.println("businessCycleDefinition:  " + businessCycleDefinition);
		if (logger.isDebugEnabled())
			logger.debug("Validating businessCycleDefinition, name-> " + businessCycleDefinition.getName());
		if (businessCycleDefinition.getName() == null || businessCycleDefinition.getName().isEmpty())
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition name not found!");

		if (logger.isDebugEnabled())
			logger.debug(
					"Validating businessCycleDefinition, serviceName-> " + businessCycleDefinition.getServiceName());
		if (businessCycleDefinition.getServiceName() == null
				|| !ServiceTypeEnum.isValid(businessCycleDefinition.getServiceName()))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition service name not found!");
	}

	@Override
	@Transactional("tenantTransactionManager")
	public BusinessCycleDefinition update(BusinessCycleDefinition existingBusinessCycleDefinition,
			BusinessCycleDefinition updatedBusinessCycleDefinition) {

		if (updatedBusinessCycleDefinition != null
				&& updatedBusinessCycleDefinition.getLastModifiedDateTime() == null) {
			updatedBusinessCycleDefinition.setLastModifiedDateTime(new Date());
			if (logger.isDebugEnabled())
				logger.debug("Changed last modified date " + "time to-> "
						+ updatedBusinessCycleDefinition.getLastModifiedDateTime());
		}

		if (updatedBusinessCycleDefinition != null
				&& StringUtils.isBlank(updatedBusinessCycleDefinition.getLastModifiedBy())) {
			if (logger.isDebugEnabled())
				logger.debug("Setting LastModifiedBy to logged in " + "user name-> "
						+ updatedBusinessCycleDefinition.getLastModifiedBy() + ", as createdBy is not set in request");
			updatedBusinessCycleDefinition.setLastModifiedBy(requestUtils.getUserName());
		}

		validateUpdateRequest(existingBusinessCycleDefinition, updatedBusinessCycleDefinition);
		if (logger.isDebugEnabled())
			logger.debug("BusinessCycleDefinition data is valid, " + "saving into DB");

		updatedBusinessCycleDefinition = overrideForUpdate(existingBusinessCycleDefinition,
				updatedBusinessCycleDefinition);
		businessCycleDefinitionRepository.save(updatedBusinessCycleDefinition);
		if (logger.isDebugEnabled())
			logger.debug("BusinessCycleDefinition saved into DB");

		return updatedBusinessCycleDefinition;
	}

	private void validateUpdateRequest(BusinessCycleDefinition existingBusinessCycleDefinition,
			BusinessCycleDefinition updatedBusinessCycleDefinition) {

		if (logger.isDebugEnabled())
			logger.debug("In update BusinessCycleDefinition");

		if (existingBusinessCycleDefinition != null) {
			List<BusinessCycle> businessCycles = businessCycleService
					.getAllByCycleDefinition(existingBusinessCycleDefinition.getId());
			if (!CollectionUtils.isEmpty(businessCycles)) {
				if (logger.isDebugEnabled())
					logger.debug("Business cycles are already created for cycleDefinitionId "
							+ existingBusinessCycleDefinition.getId());
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business cycles are already created!");
			}
		}
		// if (logger.isDebugEnabled())
		// logger.debug("Validating businessCycleDefinition, lastModifiedBy-> "
		// + updatedBusinessCycleDefinition.getLastModifiedBy());
		// if (StringUtils.isBlank(updatedBusinessCycleDefinition.getLastModifiedBy()))
		// throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Modified by is
		// Invalid!");

		validate(updatedBusinessCycleDefinition);

	}

	private BusinessCycleDefinition overrideForUpdate(BusinessCycleDefinition dbCycleDefinitionObject,
			BusinessCycleDefinition requestCycleDefinitionObject) {

		requestCycleDefinitionObject.setId(dbCycleDefinitionObject.getId());
		requestCycleDefinitionObject.setCreateDateTime(dbCycleDefinitionObject.getCreateDateTime());
		requestCycleDefinitionObject.setCreatedBy(dbCycleDefinitionObject.getCreatedBy());
		requestCycleDefinitionObject.setServiceName(dbCycleDefinitionObject.getServiceName());
		requestCycleDefinitionObject.setName(dbCycleDefinitionObject.getName());

		return requestCycleDefinitionObject;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public List<BusinessCycleDefinition> getAll() {

		if (logger.isDebugEnabled())
			logger.debug("Getting all BusinessCycleDefinitionService records from DB");
		return businessCycleDefinitionRepository.findAllByIsActive(true);
	}

	@Override
	@Transactional("tenantTransactionManager")
	public BusinessCycleDefinition getById(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition getById method getting business year with id-> " + id);
		if (id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(id, true);
		if (businessCycleDefinitionOptional.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");

		BusinessCycleDefinition businessCycle = new BusinessCycleDefinition(businessCycleDefinitionOptional.get());
		String name = businessCycle.getName();
		name = name.substring(0, name.indexOf('_'));
		businessCycle.setName(name);

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition getById method getting business -> " + businessCycle);
		return businessCycle;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void softDeleteById(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleService deleteById "
					+ "service method for deleting business cycle definition from databse where id is -> " + id);
		if (id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");

		List<BusinessCycle> businessCycleList = businessCycleService.getAllByCycleDefinition(id);
		if (businessCycleList.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");

		// businessCycleService.deleteAllByCycleDefinitionId(id);
		businessCycleDefinitionRepository.softDeleteById(id);
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void softDeleteByBusinessYearDefinitionId(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinitionService deleteByBusinessYearId "
					+ "service method for deleting business cycle from databse where businessYearDefinitionId is -> "
					+ id);

		List<BusinessCycle> businessCycleList = businessCycleService.getAllByBusinessYearDefinitionId(id);
		if (logger.isDebugEnabled())
			logger.debug("businessCycleList -> " + businessCycleList.size());
		if (CollectionUtils.isEmpty(businessCycleList)) {
			businessCycleDefinitionRepository.softDeleteAllByBusinessYearDefinitionId(id);
		} else {
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
					"Business cycle definition cannot be deleted as cycles are already created");
		}
	}
}
