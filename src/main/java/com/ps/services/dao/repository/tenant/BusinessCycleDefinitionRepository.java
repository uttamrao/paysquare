package com.ps.services.dao.repository.tenant;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessCycleDefinitionRepository extends AbstractRepository<BusinessCycleDefinition, Integer> {

	List<BusinessCycleDefinition> findAllByIsActive(boolean isActive);
	
	List<BusinessCycleDefinition> findAllByBusinessYearDefinition(BusinessYearDefinition businessYearDefinition);
	
	@Query("SELECT c FROM BusinessCycleDefinition c WHERE c.businessYearDefinition.id = (:businessYearDefinitionId)")
	List<BusinessCycleDefinition> findAllByBusinessYearDefinitionId(@Param("businessYearDefinitionId") int businessYearDefinitionId);

}