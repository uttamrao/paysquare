package com.ps.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.enums.FrequencyEnum;
import com.ps.RESTful.enums.ServiceTypeEnum;
import com.ps.RESTful.enums.WeeksEnum;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.services.BusinessCycleDefinitionService;
import com.ps.services.dao.repository.tenant.BusinessCycleDefinitionRepository;
import com.ps.util.RequestUtils;

@Service
public class BusinessCycleDefinitionServiceImpl implements BusinessCycleDefinitionService {

	Logger logger = Logger.getLogger(BusinessCycleDefinitionServiceImpl.class);
	
	@Autowired
	BusinessCycleDefinitionRepository businessCycleDefinitionRepository;
	
	@Autowired
	RequestUtils requestUtils;
	
	@Override
	public BusinessCycleDefinition add(BusinessCycleDefinition businessCycleDefinition) {
		
		if(logger.isDebugEnabled()) logger.debug("In add BusinessCycleDefinition");
		
		if(businessCycleDefinition != null && businessCycleDefinition.getCreateDateTime() == null) {
			businessCycleDefinition.setCreateDateTime(new Date());
			if(logger.isDebugEnabled()) logger.debug("Changed create date "
					+ "time to-> "+businessCycleDefinition.getCreateDateTime());
		}
		
		if(businessCycleDefinition != null && StringUtils.isBlank(businessCycleDefinition.getCreatedBy())) {
			if(logger.isDebugEnabled()) logger.debug("Setting created by to logged in "
					+ "user name-> "+businessCycleDefinition.getCreatedBy()+", as createdBy is not set in request"); 
			businessCycleDefinition.setCreatedBy(requestUtils.getUserName());	
		}
		
		validate(businessCycleDefinition);		
		if(logger.isDebugEnabled())	logger.debug("BusinessCycleDefinition data is valid, "
				+ "saving into DB");
		
		ServiceTypeEnum serviceType = ServiceTypeEnum.valueOf(businessCycleDefinition.getServiceName());
		FrequencyEnum frequency = businessCycleDefinition.getFrequencyMaster().getName();

		if(businessCycleDefinition.getWeekStartDefinition() == null && frequency.name().contains(FrequencyEnum.WEEKLY.name()))
			businessCycleDefinition.setWeekStartDefinition(WeeksEnum.SUNDAY);			
			
		businessCycleDefinition.setServiceName(serviceType.name());		
		String cycleName = businessCycleDefinition.getName()+"_"+serviceType.getShortCode()+"_"+frequency.getShortCode();		
		businessCycleDefinition.setName(cycleName);
		businessCycleDefinitionRepository.save(businessCycleDefinition);
		if(logger.isDebugEnabled()) logger.debug("BusinessCycleDefinition saved into DB");
		
		return businessCycleDefinition;
	}

	private void validate(BusinessCycleDefinition businessCycleDefinition) {
		
		if(logger.isDebugEnabled())
			logger.debug("In validate method, Validating businessCycleDefinitions");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessCycleDefinition, object-> "+businessCycleDefinition);
		if(businessCycleDefinition == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating if frequency is set in businessCycleDefinition, object-> "+businessCycleDefinition.getFrequencyMaster());
		if(businessCycleDefinition.getFrequencyMaster() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating if businessYear is set in businessCycleDefinition, object-> "+businessCycleDefinition.getBusinessYearDefinition());
		if(businessCycleDefinition.getBusinessYearDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessCycleDefinition, name-> "+businessCycleDefinition.getName());
		if(businessCycleDefinition.getName() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition name not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating businessCycleDefinition, serviceName-> "+businessCycleDefinition.getServiceName());
		if(businessCycleDefinition.getServiceName() == null
				|| !ServiceTypeEnum.isValid(businessCycleDefinition.getServiceName()))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Cycle Definition service name not found!");
			
		if(logger.isDebugEnabled())
			logger.debug("Validating businessCycleDefinition, createdBy-> "+businessCycleDefinition.getCreatedBy());
		if(StringUtils.isBlank(businessCycleDefinition.getCreatedBy()))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Created by is Invalid!");
	}
	
	@Override
	public List<BusinessCycleDefinition> getAll() {
		
		if(logger.isDebugEnabled())
			logger.debug("Getting all BusinessCycleDefinitionService records from DB");
		List<BusinessCycleDefinition> businessCycleDefinitions = businessCycleDefinitionRepository.findAllByIsActive(true);
		
		return businessCycleDefinitions;
	}

	@Override
	public BusinessCycleDefinition getById(int id) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition getById method getting business year with id-> "+id);
		if(id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");
		
		Optional<BusinessCycleDefinition> businessCycleDefinitionOptional = businessCycleDefinitionRepository.findById(id);		
		if(businessCycleDefinitionOptional.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");
		
		return businessCycleDefinitionOptional.get();
	}
	
	@Override
	public void deleteByBusinessYearDefinitionId(int id) {
		
		if(logger.isDebugEnabled()) logger.debug("In BusinessCycleService deleteByBusinessYearId "
				+ "service method for deleting business cycle from databse where businessYearDefinitionId is -> "+id);
		
		List<BusinessCycleDefinition> businessCycleDefinitionList = businessCycleDefinitionRepository.findAllByBusinessYearDefinitionId(id);
		
		for (BusinessCycleDefinition businessCycleDefinition : businessCycleDefinitionList) {
			//delete business cycles for each cycle definition
			
			businessCycleDefinitionRepository.deleteById(businessCycleDefinition.getId());
		}		
	}

}
