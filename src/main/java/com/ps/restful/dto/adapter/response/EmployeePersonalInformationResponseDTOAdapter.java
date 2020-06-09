package com.ps.restful.dto.adapter.response;

import java.util.List;

import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.common.EmployeePersonalInformation;
import com.ps.restful.dto.response.EmployeePersonalInformationResponseDTO;

public class EmployeePersonalInformationResponseDTOAdapter {

	public EmployeePersonalInformationResponseDTO buildResponse(EmployeePersonalInformation employeePersonalInformation,
			List<EmployeeAddress> employeeAddressList) {
		
		if(employeePersonalInformation == null) return null;
		
		EmployeePersonalInformationResponseDTO employeePersonalInformationResponseDTO = new EmployeePersonalInformationResponseDTO();		
		employeePersonalInformationResponseDTO.setAadharNumber(employeePersonalInformation.getAadharNumber());
		employeePersonalInformationResponseDTO.setBloodgroup(employeePersonalInformation.getBloodgroup());
		employeePersonalInformationResponseDTO.setEmail(employeePersonalInformation.getEmail());
		employeePersonalInformationResponseDTO.setMobile(employeePersonalInformation.getMobile());
	    employeePersonalInformationResponseDTO.setEmployeeDetails(new EmployeeDetailsResponseDTOAdapter().buildResponse(employeePersonalInformation.getEmployee()));
	    employeePersonalInformationResponseDTO.setCreatedBy(employeePersonalInformation.getCreatedBy());
	    employeePersonalInformationResponseDTO.setUpdatedBy(employeePersonalInformation.getUpdatedBy());
	    employeePersonalInformationResponseDTO.setId(employeePersonalInformation.getId());
	    employeePersonalInformationResponseDTO.setEmployeeAddressList(new EmployeeAddressResponseDTOAdapter().buildResponseList(employeeAddressList));
	    
	    return employeePersonalInformationResponseDTO;
	}
	
}
