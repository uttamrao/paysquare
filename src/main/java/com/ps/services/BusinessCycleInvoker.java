package com.ps.services;

import java.util.List;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;

public interface BusinessCycleInvoker {

	public List<BusinessCycle> createCycle(BusinessCycleCommand businessCycleCommand, BusinessCycleBean businessCycleBean);	
}
