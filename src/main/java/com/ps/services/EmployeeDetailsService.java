package com.ps.services;

import java.util.List;

import com.ps.entities.common.EmployeeDetails;

public interface EmployeeDetailsService {

	public EmployeeDetails save(EmployeeDetails employeeDetails);
	public List<EmployeeDetails> getAllEmplyees();
	public List<EmployeeDetails> getAllByCompanyId(long companyId);
	public EmployeeDetails getById(int id);
	public void deleteById(int id);

}
