package com.ps.services;

import java.util.List;

import com.ps.entities.common.EmployeeFamilyDetails;

public interface EmployeeFamilyDetailsService {

	public void save(EmployeeFamilyDetails employeeFamilyDetails);
	public List<EmployeeFamilyDetails> getAllByEmplyeeId(int employeeId);
	public EmployeeFamilyDetails getById(int id);
	public void deleteById(int id);
	public void deleteByEmployeeId(int employeeId);
}
