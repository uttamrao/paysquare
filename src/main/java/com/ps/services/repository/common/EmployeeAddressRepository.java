package com.ps.services.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.common.EmployeeAddress;

public interface EmployeeAddressRepository extends AbstractRepository<EmployeeAddress, Integer> {

	@Query("SELECT ad FROM EmployeeAddress ad where ad.employee.id = :employeeID")
	List<EmployeeAddress> findAllByEmployeeId(@Param("employeeID") int employeeID);

	@Modifying
	@Query("DELETE FROM EmployeeAddress ad where ad.employee.id = :employeeID")
	void deleteByEmployeeId(@Param("employeeID") int employeeID);
}
