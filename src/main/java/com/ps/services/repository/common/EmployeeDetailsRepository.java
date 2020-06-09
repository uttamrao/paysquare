package com.ps.services.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.common.EmployeeDetails;


public interface EmployeeDetailsRepository extends AbstractRepository<EmployeeDetails, Integer>, 
		CustomRepository<EmployeeDetails, Integer>{

	@Query("SELECT ed FROM EmployeeDetails ed where ed.companyId = :companyId")
	List<EmployeeDetails> findAllByCompanyId(@Param("companyId") long companyId);
	
}
