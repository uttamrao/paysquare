package com.ps.restful.dto.request;

import com.ps.dto.EmployeeAddressDTO;

public class EmployeeAddressRequestDTO extends EmployeeAddressDTO {

	private int employeeId;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
