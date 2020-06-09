package com.ps.services.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.common.EmployeeDetails;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.error.handler.InvalidRequestException;
import com.ps.restful.error.handler.ResourceNotFoundException;
import com.ps.services.EmployeeAddressService;
import com.ps.services.repository.common.EmployeeAddressRepository;

@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService {

	Logger logger = Logger.getLogger(EmployeeAddressServiceImpl.class);
	
	@Autowired
	EmployeeAddressRepository employeeAddressRepository;

	@Override
	public void save(EmployeeAddress employeeAddress) {
		
		if(logger.isDebugEnabled()) logger.debug("In add employee address "
				+ "service method adding employee address into databse "+employeeAddress);
		if(employeeAddress == null) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee address"); 
		
		employeeAddressRepository.save(employeeAddress);
	}

	@Override
	public void saveAll(List<EmployeeAddress> employeeAddressList) {
		
		if(logger.isDebugEnabled()) logger.debug("In addAll employee address "
				+ "service method adding employee addresses into databse "+employeeAddressList);
		if(CollectionUtils.isEmpty(employeeAddressList)) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee addresses"); 
		
		employeeAddressRepository.saveAll(employeeAddressList);
	}

	@Override
	public List<EmployeeAddress> getAllByEmployeeId(int employeeId) {
		
		if(logger.isDebugEnabled()) logger.debug("In getAll employee address "
				+ "service method for employeeId-> "+employeeId);
		if(employeeId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee Id"); 
		
		List<EmployeeAddress> employeeAddressList =  employeeAddressRepository.findAllByEmployeeId(employeeId);
		if(CollectionUtils.isEmpty(employeeAddressList)) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "Employee address not found!"); 

		return employeeAddressList;
	}

	@Override
	public void saveAllByEmployee(List<EmployeeAddress> employeeAddressList,
			EmployeeDetails employee) {
		if(logger.isDebugEnabled()) logger.debug("In saveAllByEmployee employee address "
				+ "service method adding employee addresses into databse "+employeeAddressList.size());
		if(CollectionUtils.isEmpty(employeeAddressList)) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid employee addresses"); 
		
		for (EmployeeAddress employeeAddress : employeeAddressList) {
			employeeAddress.setEmployee(employee);
		}
		
		employeeAddressRepository.saveAll(employeeAddressList);
	}

	@Override
	public void deleteById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee address "
				+ "service method deleting record for Id "+id);
		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Address Id! ");
		employeeAddressRepository.deleteById(id);
	}

	@Override
	public void deleteByEmployeeId(int employeeId) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee address "
				+ "service method deleting record for employee Id "+employeeId);
		
		if(employeeId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Id! ");
		employeeAddressRepository.deleteByEmployeeId(employeeId);
	}
}
