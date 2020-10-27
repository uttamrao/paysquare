package com.ps.services;

import java.util.List;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;

public interface BusinessCycleService {

	public void add(BusinessCycleBean businessCycleBean);

	public List<BusinessCycle> getAll();

	public List<BusinessCycle> getAllByBusinessCycleIdAndBusinessYear(int cycleDefinitionId, String businessYear);

	public List<BusinessCycle> getAllByCycleDefinition(int id);

	public List<BusinessCycle> getAllByBusinessYearDefinitionId(int id);

	public void deleteAllByCycleDefinitionIds(List<Integer> ids);

	public void deleteAllByCycleDefinitionId(int ids);

	public void softDeleteByBusinessCycleIdAndBusinessYear(int cycleDefinitionId, String businessYear);

}
