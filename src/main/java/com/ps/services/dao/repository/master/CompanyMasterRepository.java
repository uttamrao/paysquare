package com.ps.services.dao.repository.master;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.master.CompanyMaster;

public interface CompanyMasterRepository extends AbstractRepository<CompanyMaster, Integer> {
	
	Optional<CompanyMaster> findByCompanyName(String name);
	
	@Query("SELECT cm FROM CompanyMaster cm JOIN FETCH cm.groupCompany "
			+ "WHERE cm.companyMasterId = (:id)")
	Optional<CompanyMaster> findByIdAndFetchGroup(@Param("id") int id);
	
}