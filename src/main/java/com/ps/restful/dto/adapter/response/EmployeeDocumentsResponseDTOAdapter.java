package com.ps.restful.dto.adapter.response;

import com.ps.entities.common.EmployeeDocuments;
import com.ps.restful.dto.response.EmployeeDocumentsResponseDTO;

public class EmployeeDocumentsResponseDTOAdapter {

	public EmployeeDocumentsResponseDTO buildResponse(EmployeeDocuments employeeDocuments) {
		
		if(employeeDocuments == null) return null;
		
		EmployeeDocumentsResponseDTO documentsDTO = new EmployeeDocumentsResponseDTO();
		documentsDTO.setEmployeeDetails(new EmployeeDetailsResponseDTOAdapter().buildResponse(employeeDocuments.getEmployee()));
		documentsDTO.setDescription(employeeDocuments.getDescription());
		documentsDTO.setName(employeeDocuments.getName());
		documentsDTO.setCreatedBy(employeeDocuments.getCreatedBy());
		documentsDTO.setUpdatedBy(employeeDocuments.getUpdatedBy());
		
		return documentsDTO;
	}
	
}
