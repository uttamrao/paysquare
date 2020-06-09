package com.ps.restful.dto.adapter.request;

import com.ps.entities.common.EmployeeDocuments;
import com.ps.restful.dto.request.EmployeeDocumentsRequestDTO;

public class EmployeeDocumentsRequestDTOAdapter {

	public EmployeeDocuments buildRequest(EmployeeDocuments employeeDocuments,EmployeeDocumentsRequestDTO documentsRequestDTO,
			String createdBy, String updatedBy) {
		
		if(documentsRequestDTO == null) return null;
		if(employeeDocuments == null) employeeDocuments = new EmployeeDocuments();
		
		employeeDocuments.setDescription(documentsRequestDTO.getDescription());
		employeeDocuments.setName(documentsRequestDTO.getName());
		employeeDocuments.setCreatedBy(createdBy);
		employeeDocuments.setUpdatedBy(updatedBy);
		
		
		return employeeDocuments;
	}
	
}
