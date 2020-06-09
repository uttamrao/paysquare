package com.ps.restful.dto.request;

import java.util.List;

import com.ps.dto.EmployeePersonalInformationDTO;

public class EmployeePersonalInformationRequestDTO extends EmployeePersonalInformationDTO {

	private int employeeId;
	private List<EmployeeAddressRequestDTO> employeeAddressList;
	 
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public List<EmployeeAddressRequestDTO> getEmployeeAddressList() {
		return employeeAddressList;
	}
	public void setEmployeeAddressList(List<EmployeeAddressRequestDTO> employeeAddressList) {
		this.employeeAddressList = employeeAddressList;
	}	 
	
}
