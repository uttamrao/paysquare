package com.ps.services;

import java.util.List;

import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.common.EmployeePersonalInformation;

public interface EmployeePersonalInformationService {

	public void save(EmployeePersonalInformation employeePersonalInformation,List<EmployeeAddress> employeeAddressList);
	public EmployeePersonalInformation getByEmployeeId(int employeeId);
	public EmployeePersonalInformation getById(int id);
	public void deleteById(int id);
	public void deleteByEmployeeId(int employeeId);
	
}
