package com.ps.restful.dto.response;

import com.ps.dto.EmployeeAddressDTO;

public class EmployeeAddressResponseDTO extends EmployeeAddressDTO  {

	private int id;
	private EmployeeDetailsResponseDTO employeeDetails;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EmployeeDetailsResponseDTO getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(EmployeeDetailsResponseDTO employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	
}
