package com.ps.services.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.common.EmployeeFamilyDetails;

public interface EmployeeFamilyDetailsRepository extends AbstractRepository<EmployeeFamilyDetails, Integer> {

	@Query("SELECT fd FROM EmployeeFamilyDetails fd where fd.employee.id = :employeeId")
	List<EmployeeFamilyDetails> findAllByEmployeeId(@Param("employeeId") int employeeId);

	@Modifying
	@Query("DELETE FROM EmployeeFamilyDetails fd where fd.employee.id = :employeeID")
	void deleteByEmployeeId(@Param("employeeID") int employeeID);
	
}
