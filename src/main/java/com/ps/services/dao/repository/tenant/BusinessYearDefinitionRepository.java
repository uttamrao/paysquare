package com.ps.services.dao.repository.tenant;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessYearDefinitionRepository extends AbstractRepository<BusinessYearDefinition, Integer> {

	List<BusinessYearDefinition> findAllByIsActive(boolean isActive);
	
	BusinessYearDefinition findAllByFromDateAndToDateAndIsActive(Date from, Date to, boolean isActive);
	
	@Modifying
	@Query("UPDATE BusinessYearDefinition c SET c.isActive = false WHERE c.id = :id")
	void softDeleteById(int id);
}