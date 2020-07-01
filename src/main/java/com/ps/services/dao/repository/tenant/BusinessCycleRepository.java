package com.ps.services.dao.repository.tenant;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.BusinessCycle;

public interface BusinessCycleRepository extends AbstractRepository<BusinessCycle, Integer> {
	
	@Query("Select c from BusinessCycle c where c.businessCycleDefinition.id = :id")
	List<BusinessCycle> findLatestByCycleId(int id);
	
}