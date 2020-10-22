package com.ps.services;

import java.util.List;

import com.ps.beans.BusinessCycleDefinitionBean;
import com.ps.entities.tenant.BusinessCycleDefinition;

public interface BusinessCycleDefinitionService {

	// public BusinessCycleDefinition add(BusinessCycleDefinition
	// businessCycleDefinitionService);
	public void add(BusinessCycleDefinitionBean businessCycleDefinitionBean);

	public List<BusinessCycleDefinition> getAll();

	public BusinessCycleDefinition getById(int id);

	public void softDeleteByBusinessYearDefinitionId(int id);

	public void softDeleteById(int id);

	public BusinessCycleDefinition update(BusinessCycleDefinition existingBusinessCycleDefinition,
			BusinessCycleDefinition updatedBusinessCycleDefinition);

}
