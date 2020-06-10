package com.ps.services.impl;

import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.services.FrequencyMasterService;
import com.ps.services.dao.repository.tenant.FrequencyMasterRepository;

public class FrequencyMasterServiceImpl implements FrequencyMasterService {

	Logger logger = Logger.getLogger(FrequencyMasterServiceImpl.class);
	
	@Autowired
	FrequencyMasterRepository frequencyMasterRepository;
	
	@Override
	public FrequencyMaster add(FrequencyMaster frequencyMaster) {
		
		if(logger.isDebugEnabled())
			logger.debug("In add frequency master");
		
		validate(frequencyMaster);		
		if(logger.isDebugEnabled())
			logger.debug("Frequency master data is valid, saving into DB");
		
		frequencyMasterRepository.save(frequencyMaster);
		if(logger.isDebugEnabled())
			logger.debug("Frequency master saved into DB");
		
		return frequencyMaster;
	}

	private FrequencyMaster validate(FrequencyMaster frequencyMaster) {
		
		if(logger.isDebugEnabled())
			logger.debug("In validate method, Validating frequency master");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating frequency master, object-> "+frequencyMaster);
		if(frequencyMaster == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency name not found");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating frequency master, name-> "+frequencyMaster.getName());
		if(StringUtils.isEmpty(frequencyMaster.getName()))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency name not found");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating frequency master, createDateTime-> "+frequencyMaster.getCreateDateTime());
		if(frequencyMaster.getCreateDateTime() == null)
			frequencyMaster.setCreateDateTime(new Date());
		
		if(logger.isDebugEnabled())
			logger.debug("Validating frequency master, createdBy-> "+frequencyMaster.getCreatedBy());
		if(StringUtils.isEmpty(frequencyMaster.getCreatedBy()))
			frequencyMaster.setCreatedBy("admin_paysquare");
		
		return frequencyMaster;
	}
	
	@Override
	public List<FrequencyMaster> getAll() {
		
		List<FrequencyMaster> frequencyMasters = frequencyMasterRepository.findAll();
		
		return frequencyMasters;
	}

}
