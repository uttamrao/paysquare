package com.ps.restful.dto.response;

import java.util.List;

import com.ps.dto.EmployeePersonalInformationDTO;

public class EmployeePersonalInformationResponseDTO extends EmployeePersonalInformationDTO {

	private int id;
	private EmployeeDetailsResponseDTO employeeDetails;
	private List<EmployeeAddressResponseDTO> employeeAddressList;
	
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
	public List<EmployeeAddressResponseDTO> getEmployeeAddressList() {
		return employeeAddressList;
	}
	public void setEmployeeAddressList(List<EmployeeAddressResponseDTO> employeeAddressList) {
		this.employeeAddressList = employeeAddressList;
	}

}
