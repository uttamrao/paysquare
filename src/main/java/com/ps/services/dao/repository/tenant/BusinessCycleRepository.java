package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;

public interface BusinessCycleRepository extends AbstractRepository<BusinessCycle, Integer> {
	
	Optional<BusinessCycle> findTopByBusinessCycleDefinitionOrderByFromDateDesc(BusinessCycleDefinition businessCycleDefinition);
	
	@Query("Select c from BusinessCycle c where c.businessCycleDefinition.id = :id")
	List<BusinessCycle> findAllByCycleDefinitionId(int id);
	
}