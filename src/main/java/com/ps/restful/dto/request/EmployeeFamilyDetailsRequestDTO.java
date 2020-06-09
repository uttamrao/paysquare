package com.ps.restful.dto.request;

import com.ps.dto.EmployeeFamilyDetailsDTO;

public class EmployeeFamilyDetailsRequestDTO extends EmployeeFamilyDetailsDTO {

	private int employeeId;
	 
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}	 
}
