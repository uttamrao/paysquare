package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessYearDefinitionRepository extends AbstractRepository<BusinessYearDefinition, Integer> {

	List<BusinessYearDefinition> findAllByIsActive(boolean isActive);
	
	Optional<BusinessYearDefinition> findByIdAndIsActive(int id, boolean isActive);
	
	@Modifying
	@Query("UPDATE BusinessYearDefinition c SET c.isActive = false WHERE c.id = :id")
	void softDeleteById(int id);
}