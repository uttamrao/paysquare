package com.ps.restful.resources.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.common.EmployeeDetails;
import com.ps.entities.common.EmployeePersonalInformation;
import com.ps.restful.dto.adapter.request.EmployeeAddressRequestDTOAdapter;
import com.ps.restful.dto.adapter.request.EmployeePersonalInformationRequestDTOAdapter;
import com.ps.restful.dto.adapter.response.EmployeeAddressResponseDTOAdapter;
import com.ps.restful.dto.adapter.response.EmployeePersonalInformationResponseDTOAdapter;
import com.ps.restful.dto.request.EmployeePersonalInformationRequestDTO;
import com.ps.restful.dto.response.EmployeePersonalInformationResponseDTO;
import com.ps.restful.resources.EmployeePersonalInformationResource;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;
import com.ps.services.EmployeeAddressService;
import com.ps.services.EmployeeDetailsService;
import com.ps.services.EmployeePersonalInformationService;



@RestController
@RequestMapping(path = EmployeePersonalInformationResource.RESOURCE_PATH)
public class EmployeePersonalInformationResourceImpl extends AbstractResourceImpl implements EmployeePersonalInformationResource {

	Logger logger = Logger.getLogger(EmployeePersonalInformationResourceImpl.class);
	
	@Autowired
	EmployeePersonalInformationService employeePersonalInformationService;
	
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	@Autowired
	EmployeeAddressService employeeAddressService;
	
	@Override
	public ResponseEntity<Response> add(EmployeePersonalInformationRequestDTO requestDTO) {

		if(logger.isDebugEnabled()) logger.debug("In add employee details "
				+ " for employee Id "+requestDTO.getEmployeeId());
		EmployeeDetails employeeDetails = employeeDetailsService.getById(requestDTO.getEmployeeId());
		
		EmployeePersonalInformationRequestDTOAdapter employeePersonalInformationRequestDTOAdapter = new EmployeePersonalInformationRequestDTOAdapter();
		EmployeePersonalInformation  employeePersonalInformation = employeePersonalInformationRequestDTOAdapter.buildRequest(null,requestDTO,
				getUserName(),null);
		if(employeePersonalInformation != null) employeePersonalInformation.setEmployee(employeeDetails);
		
		EmployeeAddressRequestDTOAdapter employeeAddressRequestDTOAdapter = new EmployeeAddressRequestDTOAdapter();
		if(logger.isDebugEnabled()) logger.debug("Building address list for employee Id "+requestDTO.getEmployeeId());
		List<EmployeeAddress> employeeAddressList = employeeAddressRequestDTOAdapter.buildRequestList(requestDTO.getEmployeeAddressList(),getUserName(),null);
		if(logger.isDebugEnabled()) logger.debug("Built address list for employee Id "+requestDTO.getEmployeeId()+" employeeAddressList "+employeeAddressList.size());

		employeePersonalInformationService.save(employeePersonalInformation,employeeAddressList);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().message("Employee Personal Information Saved successfully").build());
	}

	@Override
	public ResponseEntity<Response> update(int resourceId,EmployeePersonalInformationRequestDTO requestDTO) {

		if(logger.isDebugEnabled()) logger.debug("In update employee personal details "
				+ " for Id "+resourceId);
		EmployeePersonalInformation employeePersonalInformation = employeePersonalInformationService.getById(resourceId);
		
		EmployeePersonalInformationRequestDTOAdapter employeePersonalInformationRequestDTOAdapter = new EmployeePersonalInformationRequestDTOAdapter();		
		employeePersonalInformation = employeePersonalInformationRequestDTOAdapter.buildRequest(employeePersonalInformation,requestDTO,
				employeePersonalInformation.getCreatedBy(),getUserName());
		
		employeePersonalInformationService.save(employeePersonalInformation,null);
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().message("Employee Personal Information Updated successfully").build());
	}

	@Override
	public ResponseEntity<Response> getAll(Integer employeeId) {
		if(logger.isDebugEnabled()) logger.debug("In get employee personal information "
				+ " for employee Id "+employeeId);
		
		List<EmployeePersonalInformationResponseDTO> responseDTOList = new ArrayList<EmployeePersonalInformationResponseDTO>();	 
		List<EmployeeAddress> employeeAddressList = new ArrayList<EmployeeAddress>();
		
		if(employeeId != null ) {
			
			EmployeePersonalInformation  employeePersonalInformation = employeePersonalInformationService.getByEmployeeId(employeeId.intValue());				
			employeeAddressList = employeeAddressService.getAllByEmployeeId(employeeId);
						
			EmployeePersonalInformationResponseDTOAdapter employeePersonalInformationResponeDTOAdapter = new EmployeePersonalInformationResponseDTOAdapter();
			responseDTOList.add(employeePersonalInformationResponeDTOAdapter.buildResponse(employeePersonalInformation,employeeAddressList));		
			
		}		
				
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().results(responseDTOList).build());
	}
	
	@Override
	public ResponseEntity<Response> getAddress(Integer employeeId) {
		if(logger.isDebugEnabled()) logger.debug("In get employee address information "
				+ " for employee Id "+employeeId);
		
		List<EmployeeAddress> employeeAddressList = new ArrayList<EmployeeAddress>();
		
		if(employeeId != null ) {
			
			employeeAddressList = employeeAddressService.getAllByEmployeeId(employeeId);
						
			EmployeeAddressResponseDTOAdapter employeeAddressResponseDTOAdapter = new EmployeeAddressResponseDTOAdapter();
			employeeAddressResponseDTOAdapter.buildResponseList(employeeAddressList);		
			
		}		
				
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().results(employeeAddressList).build());
	}


}
