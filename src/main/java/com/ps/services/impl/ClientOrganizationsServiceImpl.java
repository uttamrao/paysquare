package com.ps.services.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.entities.global.ClientOrganizations;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.error.handler.InvalidRequestException;
import com.ps.services.ClientOrganizationsService;
import com.ps.services.repository.global.ClientOrganizationsRepository;

@Service
public class ClientOrganizationsServiceImpl implements ClientOrganizationsService {

	Logger logger = Logger.getLogger(ClientOrganizationsServiceImpl.class);
	
	@Autowired
	ClientOrganizationsRepository cientOrganizationsRepository;
	
	@Override
	public void add(ClientOrganizations clientOrganizations) {
		
		if(logger.isDebugEnabled()) logger.debug("In add ClientOrganizations "
				+ "service method adding ClientOrganizations details into databse "+clientOrganizations);
		
		if(clientOrganizations == null) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid request");
		
		cientOrganizationsRepository.save(clientOrganizations);		
	}

	@Override
	public List<ClientOrganizations> getAllClientOrganizations() {
		if(logger.isDebugEnabled()) logger.debug("In getAll client organizations "
				+ "service method retrieving all client organizations details from databse");
		
		return cientOrganizationsRepository.findAll();
		
	}

	@Override
	public void deleteById(long id) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById client organizations "
				+ "service method deleting record for Id "+id);
		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Client Organization Id! ");
		cientOrganizationsRepository.deleteById(id);
	}


}
