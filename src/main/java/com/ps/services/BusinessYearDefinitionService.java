package com.ps.services;

import java.util.List;

import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessYearDefinitionService {

	public BusinessYearDefinition add(BusinessYearDefinition frequencyMaster);
	
	public List<BusinessYearDefinition> getAll();
	
	public BusinessYearDefinition getById(int id);
	
	public void softDeleteById(int id);

	public BusinessYearDefinition updateByBusinessYearId(int businessYearDefinitionId, BusinessYearDefinition businessYearDefinition);

}
