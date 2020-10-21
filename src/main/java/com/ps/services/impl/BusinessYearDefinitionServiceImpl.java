package com.ps.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.services.BusinessCycleDefinitionService;
import com.ps.services.BusinessCycleService;
import com.ps.services.BusinessYearDefinitionService;
import com.ps.services.dao.repository.tenant.BusinessYearDefinitionRepository;
import com.ps.util.RequestUtils;

@Service
public class BusinessYearDefinitionServiceImpl implements BusinessYearDefinitionService {

	Logger logger = Logger.getLogger(BusinessYearDefinitionServiceImpl.class);

	@Autowired
	BusinessYearDefinitionRepository businessYearDefinitionRepository;

	@Autowired
	BusinessCycleDefinitionService businessCycleDefinitionService;

	@Autowired
	BusinessCycleService businessCycleService;

	@Autowired
	RequestUtils requestUtils;

	@Override
	public BusinessYearDefinition add(BusinessYearDefinition businessYearDefinition) {

		if (logger.isDebugEnabled())
			logger.debug("In add BusinessYearDefinition");

		validate(businessYearDefinition);

		businessYearDefinition.setCreatedBy("Anagha");
		businessYearDefinition.setActive(true);

		if (logger.isDebugEnabled())
			logger.debug("BusinessYearDefinition data is valid, " + "saving into DB");
		businessYearDefinitionRepository.save(businessYearDefinition);
		if (logger.isDebugEnabled())
			logger.debug("BusinessYearDefinition saved into DB: " + businessYearDefinition);

		return businessYearDefinition;
	}

	private void validate(BusinessYearDefinition businessYearDefinition) {

		if (logger.isDebugEnabled())
			logger.debug("In validate method, Validating businessYearDefinition");

		if (logger.isDebugEnabled())
			logger.debug("Validating businessYearDefinition, object-> " + businessYearDefinition);
		if (businessYearDefinition == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition not found!");

		if (businessYearDefinition.getCreateDateTime() == null) {
			businessYearDefinition.setCreateDateTime(new Date());
			if (logger.isDebugEnabled())
				logger.debug("Changed create date " + "time to-> " + businessYearDefinition.getCreateDateTime());
		}

		if (logger.isDebugEnabled())
			logger.debug("Validating businessYearDefinition, fromDate-> " + businessYearDefinition.getFromDate());
		if (businessYearDefinition.getFromDate() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition from date not found!");

		if (logger.isDebugEnabled())
			logger.debug("Validating businessYearDefinition, toDate-> " + businessYearDefinition.getToDate());
		if (businessYearDefinition.getToDate() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition to date not found!");

		BusinessYearDefinition businessYear = businessYearDefinitionRepository
				.findByfromDateAndToDate(businessYearDefinition.getFromDate(), businessYearDefinition.getToDate());

		if (businessYear != null) {
			logger.error("From date and To date combination already exist");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
					" From date and To date combination should be unique");
		}
	}

	@Override
	public List<BusinessYearDefinition> getAll() {

		if (logger.isDebugEnabled())
			logger.debug("Getting all BusinessYearDefinition records from DB");
		return businessYearDefinitionRepository.findAllByIsActive(true);
	}

	@Override
	@Transactional("tenantTransactionManager")
	public void softDeleteById(int id) {

		if (logger.isDebugEnabled())
			logger.debug(" Deleting business year definition record with business year  id-> " + id);

		if (id == 0) {
			logger.error("Business Year Definition id is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition is Invalid!");
		}

		Optional<BusinessYearDefinition> businessYearOptional = businessYearDefinitionRepository.findByIdAndIsActive(id,
				true);
		if (businessYearOptional.isEmpty()) {
			logger.error("Business Year Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Year Definition not found!");
		}

		if (businessYearOptional.get().isUsed()) {
			logger.error("Business Year Definition is used can not delete!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
					"Business Year Definition is used can not delete!");
		}

		businessYearDefinitionRepository.softDeleteById(id);
		if (logger.isDebugEnabled())
			logger.debug(" Deleted business year definition record with business year id-> " + id);
	}

	@Override
	public BusinessYearDefinition getById(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessYearDefinition getById method getting business year with id-> " + id);
		if (id == 0) {
			logger.error("Business Year Definition id is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition is Invalid!");
		}

		Optional<BusinessYearDefinition> businessYearOptional = businessYearDefinitionRepository.findByIdAndIsActive(id,
				true);
		if (businessYearOptional.isEmpty()) {
			logger.error("Business Year Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Year Definition not found!");
		}

		return businessYearOptional.get();
	}

	@Override
	public BusinessYearDefinition updateByBusinessYearId(int businessYearDefinitionId,
			BusinessYearDefinition businessYearDefinition) {
		if (logger.isDebugEnabled())
			logger.debug("In add BusinessYearDefinition");

		if (logger.isDebugEnabled())
			logger.debug("In BusinessYearDefinition getById method getting business year with id-> "
					+ businessYearDefinitionId);

		if (businessYearDefinitionId == 0) {
			logger.error("Business Year Definition id is Invalid!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition is Invalid!");
		}

		Optional<BusinessYearDefinition> businessYearOptional = businessYearDefinitionRepository
				.findByIdAndIsActive(businessYearDefinitionId, true);
		if (businessYearOptional.isEmpty()) {
			logger.error("Business Year Definition not found!");
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Year Definition not found!");
		}

		if (businessYearOptional.get().isUsed()) {
			logger.error("Business Year Definition is used can not update!");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
					"Business Year Definition is used can not update!");
		}

		validate(businessYearDefinition);

		if (logger.isDebugEnabled())
			logger.debug("BusinessYearDefinition data is valid, " + "updating into DB");

		businessYearOptional.get().setFromDate(businessYearDefinition.getFromDate());
		businessYearOptional.get().setToDate(businessYearDefinition.getToDate());
		businessYearOptional.get().setDescription(businessYearDefinition.getDescription());
		businessYearOptional.get().setLastModifiedBy("Anagha");
		businessYearOptional.get().setLastModifiedDateTime(new Date());
		businessYearDefinitionRepository.save(businessYearOptional.get());

		if (logger.isDebugEnabled())
			logger.debug("BusinessYearDefinition updated into DB: " + businessYearOptional.get());

		return businessYearOptional.get();
	}

}
