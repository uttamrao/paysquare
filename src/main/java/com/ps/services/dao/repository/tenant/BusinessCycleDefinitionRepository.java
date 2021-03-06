package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessCycleDefinitionRepository extends AbstractRepository<BusinessCycleDefinition, Integer> {

	List<BusinessCycleDefinition> findAllByIsActive(boolean isActive);
	
	Optional<BusinessCycleDefinition> findByIdAndIsActive(int id,boolean isActive);
	
	List<BusinessCycleDefinition> findAllByBusinessYearDefinitionAndIsActive(BusinessYearDefinition businessYearDefinition, boolean isActive);
	
	@Query("SELECT cd FROM BusinessCycleDefinition cd WHERE cd.isActive = true and cd.businessYearDefinition.id = (:businessYearDefinitionId)")
	List<BusinessCycleDefinition> findAllByBusinessYearDefinitionId(@Param("businessYearDefinitionId") int businessYearDefinitionId);

	@Modifying
	@Query("UPDATE BusinessCycleDefinition cd SET cd.isActive = false WHERE cd.businessYearDefinition.id = :id")
	void softDeleteAllByBusinessYearDefinitionId(int id);
	
	@Modifying
	@Query("UPDATE BusinessCycleDefinition cd SET cd.isActive = false WHERE cd.id = :id")
	void softDeleteById(int id);
	
}