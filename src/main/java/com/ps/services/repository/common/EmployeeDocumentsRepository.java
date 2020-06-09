package com.ps.services.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.common.EmployeeDocuments;

public interface EmployeeDocumentsRepository extends AbstractRepository<EmployeeDocuments, Integer> {

	@Modifying
	@Query("DELETE FROM EmployeeDocuments ed where ed.employee.id = :employeeID")
	void deleteByEmployeeId(@Param("employeeID") int employeeID);
	
	@Query("SELECT ed FROM EmployeeDocuments ed where ed.employee.id = :employeeID")
	List<EmployeeDocuments> findByEmployeeId(@Param("employeeID") int employeeID);
}
