package com.ps.restful.dto.adapter.request;

import com.ps.entities.common.EmployeePersonalInformation;
import com.ps.restful.dto.request.EmployeePersonalInformationRequestDTO;

public class EmployeePersonalInformationRequestDTOAdapter {

public EmployeePersonalInformation buildRequest(EmployeePersonalInformation employeePersonalInformation,
		EmployeePersonalInformationRequestDTO employeePersonalInformationDTO,String createdBy,String updatedBy) {
		
		if(employeePersonalInformationDTO == null) return null;
		if(employeePersonalInformation == null) employeePersonalInformation = new EmployeePersonalInformation();
		
		employeePersonalInformation.setAadharNumber(employeePersonalInformationDTO.getAadharNumber());
		employeePersonalInformation.setBloodgroup(employeePersonalInformationDTO.getBloodgroup());
		employeePersonalInformation.setEmail(employeePersonalInformationDTO.getEmail());
		employeePersonalInformation.setMobile(employeePersonalInformationDTO.getMobile());
		employeePersonalInformation.setCreatedBy(createdBy);
		employeePersonalInformation.setUpdatedBy(updatedBy);
				
		return employeePersonalInformation;
	}
	
}
