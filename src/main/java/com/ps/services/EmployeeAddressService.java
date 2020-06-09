package com.ps.services;

import java.util.List;

import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.common.EmployeeDetails;

public interface EmployeeAddressService {

	public void save(EmployeeAddress employeeAddress);
	public void saveAll(List<EmployeeAddress> employeeAddressList);
	public void saveAllByEmployee(List<EmployeeAddress> employeeAddressList,EmployeeDetails employee);
	public List<EmployeeAddress> getAllByEmployeeId(int employeeId);
	public void deleteById(int id);
	public void deleteByEmployeeId(int employeeId);

}
