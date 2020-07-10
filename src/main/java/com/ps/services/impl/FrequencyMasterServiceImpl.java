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
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.services.FrequencyMasterService;
import com.ps.services.dao.repository.tenant.FrequencyMasterRepository;
import com.ps.util.RequestUtils;

@Service
public class FrequencyMasterServiceImpl implements FrequencyMasterService {

	Logger logger = Logger.getLogger(FrequencyMasterServiceImpl.class);
	
	@Autowired
	FrequencyMasterRepository frequencyMasterRepository;
	
	@Autowired
	RequestUtils requestUtils;
	
	@Override
	public FrequencyMaster add(FrequencyMaster frequencyMaster) {
		
		if(logger.isDebugEnabled()) logger.debug("In add frequency master");
		
		if(frequencyMaster != null && frequencyMaster.getCreateDateTime() == null) {
			frequencyMaster.setCreateDateTime(new Date());
			if(logger.isDebugEnabled()) logger.debug("Changed create date "
					+ "time to-> "+frequencyMaster.getCreateDateTime());
		}
		
		if(frequencyMaster != null && StringUtils.isBlank (frequencyMaster.getCreatedBy())) {
			if(logger.isDebugEnabled()) logger.debug("Setting created by to logged in "
					+ "user name-> "+frequencyMaster.getCreatedBy()+", as createdBy is not set in request"); 
			frequencyMaster.setCreatedBy(requestUtils.getUserName());	
		}
		
		validate(frequencyMaster);		
		if(logger.isDebugEnabled())	logger.debug("Frequency master data is valid, "
				+ "saving into DB");
		
		Optional<FrequencyMaster> existingFrequencyMaster = frequencyMasterRepository.findByNameAndIsActive(frequencyMaster.getName(), true);
		
		if(existingFrequencyMaster.isEmpty()) {
			
			frequencyMasterRepository.save(frequencyMaster);
			if(logger.isDebugEnabled()) logger.debug("Frequency master saved into DB");
			
		} else {
			if(logger.isDebugEnabled())
				logger.debug("Frequency master record is already present in db with name-> "+frequencyMaster.getName());
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency already exists!");
		}				
		
		return frequencyMaster;
	}

	private void validate(FrequencyMaster frequencyMaster) {
		
		if(logger.isDebugEnabled())
			logger.debug("In validate method, Validating frequency master");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating frequency master, object-> "+frequencyMaster);
		if(frequencyMaster == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating frequency master, name-> "+frequencyMaster.getName());
		if(frequencyMaster.getName() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency name is found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating frequency master, createdBy-> "+frequencyMaster.getCreatedBy());
		if(StringUtils.isBlank (frequencyMaster.getCreatedBy()))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Created by is Invalid!");		
	}
	
	@Override
	public List<FrequencyMaster> getAll() {
		
		if(logger.isDebugEnabled())
			logger.debug("Getting all frequency master records from DB");
		List<FrequencyMaster> frequencyMasters = frequencyMasterRepository.findAllByIsActive(true);
		
		return frequencyMasters;
	}
	
	@Override
	public FrequencyMaster getById(int id) {
		
		if(logger.isDebugEnabled())
			logger.debug("In FrequencyMaster getById method getting frequency with id-> "+id);
		if(id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency is Invalid!");
		
		Optional<FrequencyMaster> frequencyMaster = frequencyMasterRepository.findById(id);		
		if(frequencyMaster.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Frequency not found!");
		
		return frequencyMaster.get();
	}

}
