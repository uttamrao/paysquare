package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;

public interface BusinessCycleRepository extends AbstractRepository<BusinessCycle, Integer> {
	
	Optional<BusinessCycle> findTopByBusinessCycleDefinitionOrderByFromDateDesc(BusinessCycleDefinition businessCycleDefinition);
	
	@Query("Select c from BusinessCycle c where c.businessCycleDefinition.id = :id")
	List<BusinessCycle> findAllByCycleDefinitionId(int id);
	
	@Query("Select c from BusinessCycle c where c.businessCycleDefinition.businessYearDefinition.id = :yearDefinitionId and c.isLocked = false")
	List<BusinessCycle> findAllByBusinessYearDefinitionId(@Param("yearDefinitionId")int yearDefinitionId);
	
	@Modifying
	@Query("DELETE FROM BusinessCycle c where c.businessCycleDefinition.businessYearDefinition.id = :yearDefinitionId and c.isLocked = false")
	List<BusinessCycle> deleteAllByBusinessYearDefinitionId(@Param("yearDefinitionId")int yearDefinitionId);
	
}