package com.ps.restful.dto.adapter.request;

import com.ps.entities.common.EmployeeFamilyDetails;
import com.ps.entities.common.Statuses.EmployeeRelationshipStatuses;
import com.ps.restful.dto.request.EmployeeFamilyDetailsRequestDTO;

public class EmployeeFamilyDetailsRequestDTOAdapter {

	public EmployeeFamilyDetails buildRequest(EmployeeFamilyDetails employeeFamilyDetails,EmployeeFamilyDetailsRequestDTO employeeFamilyDetailsDTO,
			String createdBy,String updatedBy) {
		
		if(employeeFamilyDetailsDTO == null) return null;
		if(employeeFamilyDetails == null) employeeFamilyDetails = new EmployeeFamilyDetails();
		
		employeeFamilyDetails.setFirstname(employeeFamilyDetailsDTO.getFirstname());
		employeeFamilyDetails.setMiddlename(employeeFamilyDetailsDTO.getMiddlename());
		employeeFamilyDetails.setLastname(employeeFamilyDetailsDTO.getLastname());
		if((employeeFamilyDetailsDTO.getRelationship() != null) && (employeeFamilyDetailsDTO.getRelationship().getValue() != null)) {
			employeeFamilyDetails.setRelationship((EmployeeRelationshipStatuses.valueOf(employeeFamilyDetailsDTO.getRelationship().getValue())));
		}	
		employeeFamilyDetails.setCreatedBy(createdBy);
		employeeFamilyDetails.setUpdatedBy(updatedBy);
		
		return employeeFamilyDetails;
	}
	
}
