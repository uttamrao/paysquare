package com.ps.services.impl;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.common.EmployeePersonalInformation;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.error.handler.InvalidRequestException;
import com.ps.restful.error.handler.ResourceNotFoundException;
import com.ps.services.EmployeeAddressService;
import com.ps.services.EmployeePersonalInformationService;
import com.ps.services.repository.common.EmployeePersonalInformationRepository;

@Service
public class EmployeePersonalInformationServiceImpl implements EmployeePersonalInformationService {

	Logger logger = Logger.getLogger(EmployeePersonalInformationServiceImpl.class);
	
	@Autowired
	EmployeePersonalInformationRepository employeePersonalInformationRepository;
	
	@Autowired
	EmployeeAddressService employeeAddressService;
	
	@Override
	@Transactional
	public void save(EmployeePersonalInformation employeePersonalInformation,List<EmployeeAddress> employeeAddressList) {
		if(logger.isDebugEnabled()) logger.debug("In add employee personal information "
				+ "service method adding employee info into databse "+employeePersonalInformation);
		if(employeePersonalInformation == null) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee personal information!"); 
		
		employeePersonalInformationRepository.save(employeePersonalInformation);	
		
		if(employeeAddressList != null) employeeAddressService.saveAllByEmployee(employeeAddressList,employeePersonalInformation.getEmployee());
	}

	@Override
	public EmployeePersonalInformation getByEmployeeId(int employeeId) {
		if(logger.isDebugEnabled()) logger.debug("In get personal information "
				+ "service method for employeeId "+employeeId);
		if(employeeId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee Id"); 
		
		Optional<EmployeePersonalInformation> employeePersonalInformationOptional =  employeePersonalInformationRepository.findByEmployeeId(employeeId);
		if(!employeePersonalInformationOptional.isPresent()) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "Employee personalInformation not found!"); 

		return employeePersonalInformationOptional.get();
	}

	@Override
	public EmployeePersonalInformation getById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In get personal information "
				+ "service method for id "+id);
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid request"); 
		
		Optional<EmployeePersonalInformation> employeePersonalInformationOptional =  employeePersonalInformationRepository.findById(id);
		if(!employeePersonalInformationOptional.isPresent()) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "Employee personalInformation not found!"); 

		return employeePersonalInformationOptional.get();
	}

	@Override
	public void deleteById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee personal information "
				+ "service method deleting record for Id "+id);
		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Personal Information Id! ");
		employeePersonalInformationRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void deleteByEmployeeId(int employeeId) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee personal information "
				+ "service method deleting record for employee Id "+employeeId);
		
		if(employeeId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Id! ");
		employeePersonalInformationRepository.deleteByEmployeeId(employeeId);
		employeeAddressService.deleteByEmployeeId(employeeId);
	}

}
