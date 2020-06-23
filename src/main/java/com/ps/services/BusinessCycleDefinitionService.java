package com.ps.services;

import java.util.List;

import com.ps.entities.tenant.BusinessCycleDefinition;

public interface BusinessCycleDefinitionService {
	
	public BusinessCycleDefinition add(BusinessCycleDefinition businessCycleDefinitionService);
	
	public List<BusinessCycleDefinition> getAll();
	
	public void deleteByBusinessYearDefinitionId(int id);
	
}
