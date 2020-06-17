package com.ps.services;

import java.util.List;

import com.ps.entities.tenant.BusinessYearDefinition;

public interface BusinessYearDefinitionService {

	public BusinessYearDefinition add(BusinessYearDefinition frequencyMaster);
	
	public List<BusinessYearDefinition> getAll();

}
