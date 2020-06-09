package com.ps.services.impl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.entities.common.EmployeeDetails;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.error.handler.InvalidRequestException;
import com.ps.restful.error.handler.ResourceNotFoundException;
import com.ps.services.EmployeeDetailsService;
import com.ps.services.EmployeeDocumentsService;
import com.ps.services.EmployeePersonalInformationService;
import com.ps.services.repository.common.EmployeeDetailsRepository;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	Logger logger = Logger.getLogger(EmployeeDetailsServiceImpl.class);
	
	@Autowired
	EmployeeDetailsRepository employeeDetailsRepository;
	
	@Autowired
	EmployeePersonalInformationService employeePersonalInformationService;
	
	@Autowired
	EmployeeDocumentsService employeeDocumentsService;
	
	@Override
	public EmployeeDetails save(EmployeeDetails employeeDetails) {
		
		if(logger.isDebugEnabled()) logger.debug("In add employee details "
				+ "service method adding employee details into databse "+employeeDetails);
		isValid(employeeDetails);
				
		employeeDetailsRepository.save(employeeDetails);	
	    employeeDetailsRepository.refreshEntity(employeeDetails);
	    
	    return employeeDetails;
	}
	
	private void isValid(EmployeeDetails employeeDetails){		
		if(employeeDetails == null) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Entity is null");  
	}

	@Override
	public List<EmployeeDetails> getAllEmplyees() {
		if(logger.isDebugEnabled()) logger.debug("In getAll employee details "
				+ "service method retrieving all employees details from databse");
		
		return employeeDetailsRepository.findAll();
		
	}
	
	@Override
	public EmployeeDetails getById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In getById employee details "
				+ "service method retrieving employees details from databse for id-> "+id);
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Employee Id is invalid!"); 

		Optional<EmployeeDetails> employee = employeeDetailsRepository.findById(id);
		if(!employee.isPresent()) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND
				, "Employee not found!"); 
		
		return employee.get();
		
	}

	@Override
	public List<EmployeeDetails> getAllByCompanyId(long companyId) {
		if(logger.isDebugEnabled()) logger.debug("In getAllByCompanyId "
				+ "service method retrieving all employees details from databse");
		
		if(companyId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Company Id is invalid!"); 

		return employeeDetailsRepository.findAllByCompanyId(companyId);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee details "
				+ "service method deleting record for Id "+id);		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Id! ");
		employeeDocumentsService.deleteByEmployeeId(id);
		employeePersonalInformationService.deleteByEmployeeId(id);
		employeeDetailsRepository.deleteById(id);
	}

}
