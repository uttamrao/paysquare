package com.ps.restful.dto.request;

import com.ps.dto.EmployeeDocumentsDTO;

public class EmployeeDocumentsRequestDTO extends EmployeeDocumentsDTO {

	private int employeeId;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
