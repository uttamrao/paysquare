package com.ps.restful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.entities.common.EmployeeDetails;
import com.ps.entities.common.EmployeeFamilyDetails;
import com.ps.restful.dto.adapter.request.EmployeeFamilyDetailsRequestDTOAdapter;
import com.ps.restful.dto.adapter.response.EmployeeFamilyDetailsResponseDTOAdapter;
import com.ps.restful.dto.request.EmployeeFamilyDetailsRequestDTO;
import com.ps.restful.dto.response.EmployeeFamilyDetailsResponseDTO;
import com.ps.restful.resources.EmployeeFamilyDetailsResource;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;
import com.ps.services.EmployeeDetailsService;
import com.ps.services.EmployeeFamilyDetailsService;



@RestController
@RequestMapping(path = EmployeeFamilyDetailsResource.RESOURCE_PATH)
public class EmployeeFamilyDetailsResourceImpl extends AbstractResourceImpl implements EmployeeFamilyDetailsResource {

	Logger logger = Logger.getLogger(EmployeeFamilyDetailsResourceImpl.class);
	
	@Autowired
	EmployeeFamilyDetailsService employeeFamilyDetailsService;
	
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	@Override
	public ResponseEntity<Response> add(EmployeeFamilyDetailsRequestDTO requestDTO) {

		if(logger.isDebugEnabled()) logger.debug("In add employee family details "
				+ "resource");
		
		EmployeeDetails employeeDetails =employeeDetailsService.getById(requestDTO.getEmployeeId());
		
		EmployeeFamilyDetailsRequestDTOAdapter employeeDetailsRequestDTOAdapter = new EmployeeFamilyDetailsRequestDTOAdapter();
		EmployeeFamilyDetails employeeFamilyDetails = employeeDetailsRequestDTOAdapter.buildRequest(null,requestDTO,
				getUserName(),null);
		if(employeeFamilyDetails != null) employeeFamilyDetails.setEmployee(employeeDetails);
		employeeFamilyDetailsService.save(employeeFamilyDetails);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().message("Employee Family Details Created successfully").build());
	}

	@Override
	public ResponseEntity<Response> update(int resourceId,EmployeeFamilyDetailsRequestDTO requestDTO) {

		if(logger.isDebugEnabled()) logger.debug("In update employee details "
				+ " for employee Id "+resourceId);
		EmployeeFamilyDetails employeeFamilyDetails = employeeFamilyDetailsService.getById(resourceId);
		
		EmployeeFamilyDetailsRequestDTOAdapter employeeDetailsRequestDTOAdapter = new EmployeeFamilyDetailsRequestDTOAdapter();
		employeeFamilyDetails = employeeDetailsRequestDTOAdapter.buildRequest(employeeFamilyDetails,requestDTO,
				employeeFamilyDetails.getCreatedBy(),getUserName());
		
		employeeFamilyDetailsService.save(employeeFamilyDetails);
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().message("Employee Family Details Updated successfully").build());
	}
	
	@Override
	public ResponseEntity<Response> getAllByEmployee(int employeeId) {
		
		if(logger.isDebugEnabled()) logger.debug("In getAll employee family details "
				+ "resource");				
		List<EmployeeFamilyDetails> employeeFamilyDetailsList =  employeeFamilyDetailsService.getAllByEmplyeeId(employeeId);
		
		EmployeeFamilyDetailsResponseDTOAdapter employeeDetailsResponsetDTOAdapter = new EmployeeFamilyDetailsResponseDTOAdapter();
		List<EmployeeFamilyDetailsResponseDTO> employeeFamilyDetailsResponseDTOList = employeeDetailsResponsetDTOAdapter.buildResponseList(employeeFamilyDetailsList);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().results(employeeFamilyDetailsResponseDTOList).build());
	}
	
	@Override
	public ResponseEntity<Response> get(int resourceId) {
		
		/*
		 * if(logger.isDebugEnabled()) logger.debug("In get employee details " +
		 * " for employee Id "+resourceId); EmployeeDetails employeeDetails =
		 * employeeDetailsService.getById(resourceId);
		 * 
		 * EmployeeDetailsResponseDTOAdapter employeeDetailsResponseDTOAdapter = new
		 * EmployeeDetailsResponseDTOAdapter(); EmployeeDetailsResponseDTO responseDTO =
		 * employeeDetailsResponseDTOAdapter.buildResponse(employeeDetails);
		 * 
		 * return ResponseEntity.status(HttpStatus.OK).body(
		 * ResponseBuilder.builder().result(responseDTO).build());
		 */
		return null;
	}
}
