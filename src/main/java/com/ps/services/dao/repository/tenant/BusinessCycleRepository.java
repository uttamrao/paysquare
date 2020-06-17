package com.ps.services.dao.repository.tenant;

import java.util.List;

import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessCycleRepository extends AbstractRepository<BusinessCycle, Integer> {

	List<BusinessCycle> findAllByIsActive(boolean isActive);
	
	List<BusinessCycle> findAllByBusinessYearDefinition(BusinessYearDefinition businessYearDefinition);
}