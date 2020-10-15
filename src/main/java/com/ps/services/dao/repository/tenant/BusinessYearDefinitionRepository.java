package com.ps.services.dao.repository.tenant;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessYearDefinitionRepository extends AbstractRepository<BusinessYearDefinition, Integer> {

	List<BusinessYearDefinition> findAllByIsActive(boolean isActive);
	
	Optional<BusinessYearDefinition> findByIdAndIsActive(int id, boolean isActive);
	
	Optional<BusinessYearDefinition> findByIdAndIsActiveAndIsUsed(int id,boolean isActive,boolean isUsed );
	
	@Modifying
	@Query("UPDATE BusinessYearDefinition c SET c.isActive = false , c.isUsed = false WHERE c.id = :id")
	void softDeleteById(int id);
	
	BusinessYearDefinition findByfromDateAndToDate(Date fromDate, Date toDate);
}