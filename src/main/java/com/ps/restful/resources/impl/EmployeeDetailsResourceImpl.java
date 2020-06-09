package com.ps.restful.resources.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.dto.EnumDTO;
import com.ps.entities.common.EmployeeDetails;
import com.ps.entities.common.Statuses.EmployeeJoiningStage;
import com.ps.restful.dto.adapter.request.EmployeeDetailsRequestDTOAdapter;
import com.ps.restful.dto.adapter.response.EmployeeDetailsResponseDTOAdapter;
import com.ps.restful.dto.adapter.response.EnumResponseDTOAdapter;
import com.ps.restful.dto.request.EmployeeDetailsRequestDTO;
import com.ps.restful.dto.response.EmployeeDetailsResponseDTO;
import com.ps.restful.resources.EmployeeDetailsResource;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;
import com.ps.services.EmployeeDetailsService;

@RestController
@RequestMapping(path = EmployeeDetailsResource.RESOURCE_PATH)
public class EmployeeDetailsResourceImpl extends AbstractResourceImpl implements EmployeeDetailsResource {

	Logger logger = Logger.getLogger(EmployeeDetailsResourceImpl.class);
	
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	@Override
	public ResponseEntity<Response> add(EmployeeDetailsRequestDTO requestDTO) {

		if(logger.isDebugEnabled()) logger.debug("In add employee details "
				+ "resource");
		
		EmployeeDetailsRequestDTOAdapter employeeDetailsRequestDTOAdapter = new EmployeeDetailsRequestDTOAdapter();
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails = employeeDetailsRequestDTOAdapter.buildRequest(employeeDetails,requestDTO,
				getUserName(),null);
		
		employeeDetails = employeeDetailsService.save(employeeDetails);
		
		EmployeeDetailsResponseDTOAdapter employeeDetailsResponseDTOAdapter = new EmployeeDetailsResponseDTOAdapter();
		EmployeeDetailsResponseDTO responseDTO = employeeDetailsResponseDTOAdapter.buildResponse(employeeDetails);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().message("Created successfully").result(responseDTO).build());
	}

	@Override
	public ResponseEntity<Response> update(int resourceId,EmployeeDetailsRequestDTO requestDTO) {

		if(logger.isDebugEnabled()) logger.debug("In update employee details "
				+ " for employee Id "+resourceId);
		EmployeeDetails employeeDetails = employeeDetailsService.getById(resourceId);
		
		EmployeeDetailsRequestDTOAdapter employeeDetailsRequestDTOAdapter = new EmployeeDetailsRequestDTOAdapter();		
		employeeDetails = employeeDetailsRequestDTOAdapter.buildRequest(employeeDetails,requestDTO,
				employeeDetails.getCreatedBy(),getUserName());
		
		employeeDetails = employeeDetailsService.save(employeeDetails);
		
		EmployeeDetailsResponseDTOAdapter employeeDetailsResponseDTOAdapter = new EmployeeDetailsResponseDTOAdapter();
		EmployeeDetailsResponseDTO responseDTO = employeeDetailsResponseDTOAdapter.buildResponse(employeeDetails);
	
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().message("Updated successfully").result(responseDTO).build());
	}
	
	public ResponseEntity<Response> delete(@PathVariable("resourceId") int resourceId){
		if(logger.isDebugEnabled()) logger.debug("In delete employee details "
				+ " for employee Id "+resourceId);
		
		employeeDetailsService.deleteById(resourceId);	
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().message("Deleted successfully").build());
	}
	
	@Override
	public ResponseEntity<Response> getAll(Long companyId) {
		
		if(logger.isDebugEnabled()) logger.debug("In getAll employee details "
				+ "resource "+companyId);		
		
		List<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();
		
		if(companyId != null ) {
			employeeDetailsList = employeeDetailsService.getAllByCompanyId(companyId.longValue());
		}else {
		   employeeDetailsList = employeeDetailsService.getAllEmplyees();
		}
		
		EmployeeDetailsResponseDTOAdapter employeeDetailsRequestDTOAdapter = new EmployeeDetailsResponseDTOAdapter();		
		List<EmployeeDetailsResponseDTO> employeeDetailsResponseDTOList =employeeDetailsRequestDTOAdapter.buildResponseList(employeeDetailsList);
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().results(employeeDetailsResponseDTOList).build());
	}
	
	@Override
	public ResponseEntity<Response> get(int resourceId) {
		
		if(logger.isDebugEnabled()) logger.debug("In get employee details "
				+ " for employee Id "+resourceId);
		EmployeeDetails employeeDetails = employeeDetailsService.getById(resourceId);
		
		EmployeeDetailsResponseDTOAdapter employeeDetailsResponseDTOAdapter = new EmployeeDetailsResponseDTOAdapter();
		EmployeeDetailsResponseDTO responseDTO = employeeDetailsResponseDTOAdapter.buildResponse(employeeDetails);
	
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().result(responseDTO).build());
	}

	@Override
	public ResponseEntity<Response> getJoinStageEnums() {
		
		EnumResponseDTOAdapter adapter = new EnumResponseDTOAdapter();
		List<EnumDTO> enumResponseDTOList = adapter.buildResponseFrom(EmployeeJoiningStage.values());
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().results(enumResponseDTOList).build());
	}
	
}
