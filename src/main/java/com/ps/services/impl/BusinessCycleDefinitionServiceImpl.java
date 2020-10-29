package com.ps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.beans.BusinessCycleDefinitionBean;
import com.ps.entities.master.ServiceCode;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.services.BusinessCycleDefinitionService;
import com.ps.services.BusinessCycleService;
import com.ps.services.dao.repository.master.ServiceCodeRepository;
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
	ServiceCodeRepository serviceCodeRepository;

	@Autowired
	BusinessCycleService businessCycleService;

	@Autowired
	RequestUtils requestUtils;

	@Override
	@Transactional("tenantTransactionManager")
	public void add(BusinessCycleDefinitionBean businessCycleDefinitionBean) {
		if (logger.isDebugEnabled())
			logger.debug("In add BusinessCycleDefinition");

		List<String> serviceNames = businessCycleDefinitionBean.getServiceName();
		List<BusinessCycleDefinition> cycleDefinitions = new ArrayList<>();

		for (String service : serviceNames) {
			if (logger.isDebugEnabled())
				logger.debug("Saving Business Cycle Definition for service name--> " + service);

			BusinessCycleDefinition businessCycleDefinition;
			businessCycleDefinition = businessCycleDefinitionBean.beanToEntity(businessCycleDefinitionBean);
			businessCycleDefinition.setServiceName(service);

			validate(0, businessCycleDefinition);
			if (logger.isDebugEnabled())
				logger.debug("BusinessCycleDefinition data is valid");

			// set all fields
			addData(businessCycleDefinition);
			businessCycleDefinition.setCreatedBy("Anagha");
			businessCycleDefinition.setActive(true);

			// set isUsed field from business year definition=1
			setBusinessYearToTrue(businessCycleDefinition);
			cycleDefinitions.add(businessCycleDefinition);

			if (logger.isDebugEnabled())
				logger.debug("For service name --> " + service + " Business Cycle Definition added into list");
		}

		businessCycleDefinitionRepository.saveAll(cycleDefinitions);
		if (logger.isDebugEnabled())
			logger.debug("Business Cycle Definitions saved successfully for given services--> " + serviceNames);
	}

	private BusinessCycleDefinition addData(BusinessCycleDefinition businessCycleDefinition) {

		String shortCode = "";
		String frequncyName = "";
		if (businessCycleDefinition.getFrequencyMaster() != null) {
			shortCode = businessCycleDefinition.getFrequencyMaster().getName().getShortCode();
			frequncyName = businessCycleDefinition.getFrequencyMaster().getName().getValue();
		}

		ServiceCode serviceType = serviceCodeRepository.findByServiceName(businessCycleDefinition.getServiceName());
		String description = businessCycleDefinition.getName() + " " + frequncyName + " " + serviceType.getServiceName()
				+ " " + "Cycle";
		businessCycleDefinition.setDescription(description);

		String cycleName = businessCycleDefinition.getName() + "_" + shortCode + "_" + serviceType.getServiceCode();
		businessCycleDefinition.setName(cycleName);

		businessCycleDefinition.setServiceName(serviceType.getServiceName());

		return businessCycleDefinition;
	}

	private void setBusinessYearToTrue(BusinessCycleDefinition businessCycleDefinition) {
		Optional<BusinessYearDefinition> businessYearOptional = businessYearDefinitionRepository
				.findByIdAndIsActive(businessCycleDefinition.getBusinessYearDefinition().getId(), true);

		if (businessYearOptional.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Year Definition not found!");

		businessYearOptional.get().setIsUsed(true);
		businessYearDefinitionRepository.save(businessYearOptional.get());

		if (logger.isDebugEnabled())
			logger.debug("Business year Definition IsUsed field updated in table to: true");
	}

	private void validate(int businessCycleDefinitionId, BusinessCycleDefinition businessCycleDefinition) {

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

		if (logger.isDebugEnabled())
			logger.debug("Validating businessCycleDefinition, name-> " + businessCycleDefinition.getName());
		if (businessCycleDefinition.getName() == null || businessCycleDefinition.getName().isEmpty())
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition name not found!");

		if (logger.isDebugEnabled())
			logger.debug(
					"Validating businessCycleDefinition, serviceName-> " + businessCycleDefinition.getServiceName());
		// !ServiceTypeEnum.isValid(businessCycleDefinition.getServiceName())
		if (businessCycleDefinition.getServiceName() == null || businessCycleDefinition.getServiceName().isEmpty())
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition service name not found!");

		ServiceCode serviceType = serviceCodeRepository.findByServiceName(businessCycleDefinition.getServiceName());
		if (serviceType == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition service name is invalid!");

		// to check unique constraint
		BusinessCycleDefinition businessCycleDef = businessCycleDefinitionRepository
				.findByBusinessYearDefinitionAndFrequencyMasterAndServiceNameAndIsActive(
						businessCycleDefinition.getBusinessYearDefinition().getId(),
						businessCycleDefinition.getFrequencyMaster().getId(), businessCycleDefinition.getServiceName());

		if (businessCycleDef != null) {
			if (businessCycleDefinitionId != businessCycleDef.getId()) {
				logger.error(
						"Business year Definition, Frequency master and service name combination should be unique");
				logger.error("Business Cycle Definition already exist");
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
						" Business cycle Definition record alreay exist");
			}
		}
	}

	@Override
	@Transactional("tenantTransactionManager")
	public BusinessCycleDefinition update(int businessCycleDefinitionId,
			BusinessCycleDefinition updatedBusinessCycleDefinition) {
		if (logger.isDebugEnabled())
			logger.debug("In update BusinessCycleDefinition service method");

		BusinessCycleDefinition existingCycleDefinition = validateUpdateRequest(businessCycleDefinitionId,
				updatedBusinessCycleDefinition);

		if (logger.isDebugEnabled())
			logger.debug("BusinessCycleDefinition data is valid, " + "updating into DB");

		overrideForUpdate(existingCycleDefinition, updatedBusinessCycleDefinition);

		businessCycleDefinitionRepository.save(existingCycleDefinition);

		// set isUsed field from business year definition=1
		setBusinessYearToTrue(existingCycleDefinition);
		if (logger.isDebugEnabled())
			logger.debug("BusinessCycleDefinition updated into DB: " + existingCycleDefinition);

		return updatedBusinessCycleDefinition;
	}

	private BusinessCycleDefinition validateUpdateRequest(int businessCycleDefinitionId,
			BusinessCycleDefinition updatedBusinessCycleDefinition) {

		if (logger.isDebugEnabled())
			logger.debug("In validateUpdateRequest BusinessCycleDefinition");

		if (businessCycleDefinitionId == 0) {
			logger.error("Business Year Definition id is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition is Invalid!");
		}

		Optional<BusinessCycleDefinition> businessCycleOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(businessCycleDefinitionId, true);
		if (businessCycleOptional.isEmpty()) {
			logger.error("Business Year Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");
		}

		if (businessCycleOptional.get().getIsUsed()) {
			logger.error("Business Year Definition is used can not update!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
					"Business Year Definition is used can not update!");
		}

		validate(businessCycleDefinitionId, updatedBusinessCycleDefinition);

		return businessCycleOptional.get();
	}

	private BusinessCycleDefinition overrideForUpdate(BusinessCycleDefinition existingCycleDefinition,
			BusinessCycleDefinition requestCycleDefinitionObject) {
		if (logger.isDebugEnabled())
			logger.debug("In overrideForUpdate BusinessCycleDefinition");

		existingCycleDefinition.setServiceName(requestCycleDefinitionObject.getServiceName());
		existingCycleDefinition.setName(requestCycleDefinitionObject.getName());
		existingCycleDefinition.setDescription(requestCycleDefinitionObject.getDescription());
		existingCycleDefinition.setFrequencyMaster(requestCycleDefinitionObject.getFrequencyMaster());

		// before updating business cycle definition isUsed from business year set=0 if
		// year is not used any other cycle
		setBusinessYearToFalse(existingCycleDefinition);

		existingCycleDefinition.setBusinessYearDefinition(requestCycleDefinitionObject.getBusinessYearDefinition());
		existingCycleDefinition.setLastModifiedBy("Anagha");
		// set all fields
		addData(existingCycleDefinition);
		if (logger.isDebugEnabled())
			logger.debug("In overrideForUpdate BusinessCycleDefinition, all data set to existing object");
		return existingCycleDefinition;
	}

	private void setBusinessYearToFalse(BusinessCycleDefinition existingCycleDefinition) {
		List<BusinessCycleDefinition> cycles = businessCycleDefinitionRepository.findBusinessYearDefinitionUsed(
				existingCycleDefinition.getBusinessYearDefinition().getId(), existingCycleDefinition.getId());

		if (cycles.isEmpty()) {
			Optional<BusinessYearDefinition> businessYearOptional = businessYearDefinitionRepository
					.findByIdAndIsActive(existingCycleDefinition.getBusinessYearDefinition().getId(), true);

			if (businessYearOptional.isEmpty())
				throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Year Definition not found!");

			businessYearOptional.get().setIsUsed(false);
			businessYearDefinitionRepository.save(businessYearOptional.get());

			if (logger.isDebugEnabled())
				logger.debug("Business year Definition IsUsed field updated in table to: false");
		}
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
		if (id == 0) {
			logger.error("Business Year Definition id is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");
		}

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
	public BusinessCycleDefinition get(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition get method getting business year with id-> " + id);
		if (id == 0) {
			logger.error("Business Year Definition id is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");
		}

		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(id, true);
		if (businessCycleDefinitionOptional.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition getById method getting business -> "
					+ businessCycleDefinitionOptional.get());
		return businessCycleDefinitionOptional.get();
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void softDeleteById(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleService softDeleteById "
					+ "service method for deleting business cycle definition from databse where id is -> " + id);
		if (id == 0) {
			logger.error("Business Cycle Definition id is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");
		}

		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository
				.findByIdAndIsActive(id, true);
		if (businessCycleDefinitionOptional.isEmpty()) {
			logger.error("Business Cycle Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");
		}

		if (businessCycleDefinitionOptional.get().getIsUsed()) {
			logger.error("Business Cycle Definition is used can not delete!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
					"Business Cycle Definition is used can not delete!");
		}

		businessCycleDefinitionRepository.softDeleteById(id);
		if (logger.isDebugEnabled())
			logger.debug("Soft deleted business cycle definition record with business year id-> " + id);
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
