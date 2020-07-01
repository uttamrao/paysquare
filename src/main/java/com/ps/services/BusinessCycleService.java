package com.ps.services;

import java.util.List;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;

public interface BusinessCycleService {
	
	public void add(BusinessCycleBean businessCycleBean);
	
	public List<BusinessCycle> getAll();
		
}
