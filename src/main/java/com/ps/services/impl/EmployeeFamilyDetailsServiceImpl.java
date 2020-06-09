package com.ps.services.impl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ps.entities.common.EmployeeFamilyDetails;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.error.handler.InvalidRequestException;
import com.ps.restful.error.handler.ResourceNotFoundException;
import com.ps.services.EmployeeFamilyDetailsService;
import com.ps.services.repository.common.EmployeeFamilyDetailsRepository;

@Service
public class EmployeeFamilyDetailsServiceImpl implements EmployeeFamilyDetailsService {

	Logger logger = Logger.getLogger(EmployeeFamilyDetailsServiceImpl.class);
	
	@Autowired
	EmployeeFamilyDetailsRepository employeeFamilyDetailsRepository;

	@Override
	public void save(EmployeeFamilyDetails employeeFamilyDetails) {
		if(logger.isDebugEnabled()) logger.debug("In add employee family details "
				+ "service method adding into databse "+employeeFamilyDetails);
		if(employeeFamilyDetails == null) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee address"); 
		
		employeeFamilyDetailsRepository.save(employeeFamilyDetails);
	}

	@Override
	public List<EmployeeFamilyDetails> getAllByEmplyeeId(int employeeId) {

		if(logger.isDebugEnabled()) logger.debug("In get employee family details "
				+ "service method for employeeId-> "+employeeId);
		if(employeeId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee Id"); 
		
		List<EmployeeFamilyDetails> employeeFamilyDetailsList =  employeeFamilyDetailsRepository.findAllByEmployeeId(employeeId);
		if(!CollectionUtils.isEmpty(employeeFamilyDetailsList)) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "Employee family details not found!"); 

		return employeeFamilyDetailsList;
	}

	@Override
	public EmployeeFamilyDetails getById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In getById employee familydetails "
				+ "service method retrieving employee family details from databse for id-> "+id);
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Employee Id is invalid!"); 

		Optional<EmployeeFamilyDetails> employeeFamilyDetails = employeeFamilyDetailsRepository.findById(id);
		if(!employeeFamilyDetails.isPresent()) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND
				, "Employee Family Details not found!"); 
		
		return employeeFamilyDetails.get();		
	}
	
	@Override
	public void deleteById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee family details "
				+ "service method deleting record for Id "+id);
		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Family Details Id! ");
		employeeFamilyDetailsRepository.deleteById(id);
	}

	@Override
	public void deleteByEmployeeId(int employeeId) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee address "
				+ "service method deleting record for employee Id "+employeeId);
		
		if(employeeId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Id! ");
		employeeFamilyDetailsRepository.deleteByEmployeeId(employeeId);
	}

}
