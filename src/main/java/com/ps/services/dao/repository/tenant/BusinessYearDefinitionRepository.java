package com.ps.services.dao.repository.tenant;

import java.util.List;

import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessYearDefinitionRepository extends AbstractRepository<BusinessYearDefinition, Integer> {

	List<BusinessYearDefinition> findAllByIsActive(boolean isActive);

}