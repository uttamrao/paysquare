package com.ps.services.impl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.ResourceNotFoundException;
import com.ps.entities.master.GroupCompanyMaster;
import com.ps.services.GroupCompanyMasterService;
import com.ps.services.dao.repository.master.GroupCompanyMasterRepository;

@Service
public class GroupCompanyMasterServiceImpl implements GroupCompanyMasterService {

	Logger logger = Logger.getLogger(GroupCompanyMasterServiceImpl.class);
	
	@Autowired
	GroupCompanyMasterRepository groupDBMasterRepository;
	
	@Override
	public List<GroupCompanyMaster> getAll() {
		
		if(logger.isDebugEnabled()) logger.debug("In getAllGroupDBMasterData "
				+ "service method retrieving all group companies from databse");	
		return groupDBMasterRepository.findAll();
		
	}

	@Override
	public GroupCompanyMaster getById(int id) {
		
		if(logger.isDebugEnabled()) logger.debug("In getById "
				+ "service method retrieving group company from databse for company id-> "+id);
		
		Optional<GroupCompanyMaster> groupCompany = groupDBMasterRepository.findById(id);
		
		if(!groupCompany.isPresent()) {			
			if(logger.isDebugEnabled()) logger.debug("Group company not found in databse for group id-> "+id);			
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "Group company not found!"); 
		}
		
		return groupCompany.get();
	}

}
