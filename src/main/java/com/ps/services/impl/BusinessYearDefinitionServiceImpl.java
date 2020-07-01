package com.ps.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.services.BusinessYearDefinitionService;
import com.ps.services.dao.repository.tenant.BusinessYearDefinitionRepository;
import com.ps.util.RequestUtils;

@Service
public class BusinessYearDefinitionServiceImpl implements BusinessYearDefinitionService {

	Logger logger = Logger.getLogger(BusinessYearDefinitionServiceImpl.class);
	
	@Autowired
	BusinessYearDefinitionRepository businessYearDefinitionRepository;
	
	@Autowired
	RequestUtils requestUtils;
	
	@Override
	public BusinessYearDefinition add(BusinessYearDefinition businessYearDefinition) {
		
		if(logger.isDebugEnabled()) logger.debug("In add BusinessYearDefinition");
		
		if(businessYearDefinition != null && businessYearDefinition.getCreateDateTime() == null) {
			businessYearDefinition.setCreateDateTime(new Date());
			if(logger.isDebugEnabled()) logger.debug("Changed create date "
					+ "time to-> "+businessYearDefinition.getCreateDateTime());
		}
		
		if(businessYearDefinition != null && StringUtils.isBlank(businessYearDefinition.getCreatedBy())) {
			if(logger.isDebugEnabled()) logger.debug("Setting created by to logged in "
					+ "user name-> "+businessYearDefinition.getCreatedBy()+", as createdBy is not set in request"); 
			businessYearDefinition.setCreatedBy(requestUtils.getUserName());	
		}
		
		validate(businessYearDefinition);		
		if(logger.isDebugEnabled())	logger.debug("BusinessYearDefinition data is valid, "
				+ "saving into DB");
		businessYearDefinitionRepository.save(businessYearDefinition);
		if(logger.isDebugEnabled()) logger.debug("BusinessYearDefinition saved into DB");
		
		return businessYearDefinition;
	}

	private void validate(BusinessYearDefinition businessYearDefinition) {
		
		if(logger.isDebugEnabled())
			logger.debug("In validate method, Validating businessYearDefinition");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessYearDefinition, object-> "+businessYearDefinition);
		if(businessYearDefinition == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessYearDefinition, fromDate-> "+businessYearDefinition.getFromDate());
		if(businessYearDefinition.getFromDate() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition from date not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessYearDefinition, toDate-> "+businessYearDefinition.getToDate());
		if(businessYearDefinition.getToDate() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition to date not found!");
		
//		if(logger.isDebugEnabled())
//			logger.debug("Validating businessYearDefinition, applicableTo-> "+businessYearDefinition.getDescription());
//		if((businessYearDefinition.getDescription()))
//			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition description not found!");
//		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessYearDefinition, createdBy-> "+businessYearDefinition.getCreatedBy());
		if(StringUtils.isBlank(businessYearDefinition.getCreatedBy()))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Created by is Invalid!");
	}
	
	@Override
	public List<BusinessYearDefinition> getAll() {
		
		if(logger.isDebugEnabled())
			logger.debug("Getting all BusinessYearDefinition records from DB");
		List<BusinessYearDefinition> businessYearDefinitions = businessYearDefinitionRepository.findAllByIsActive(true);
		
		return businessYearDefinitions;
	}
	
	@Override
	public void deleteById(int id) {
		
		if(logger.isDebugEnabled())
			logger.debug("Deleting business year record from DB, id-> "+id);
		businessYearDefinitionRepository.deleteById(id);		
	}

	@Override
	public BusinessYearDefinition getById(int id) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessYearDefinition getById method getting business year with id-> "+id);
		if(id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition is Invalid!");
		
		Optional<BusinessYearDefinition> businessYearOptional = businessYearDefinitionRepository.findById(id);		
		if(businessYearOptional.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Year Definition not found!");
		
		return businessYearOptional.get();
	}

}
