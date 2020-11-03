package com.ps.services;

import java.util.List;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;

public interface BusinessCycleInvoker {

	public List<BusinessCycle> createCycle(BusinessCycleCommand businessCycleCommand,
			BusinessCycleBean businessCycleBean);

	public List<BusinessCycle> updateCycle(BusinessCycleCommand businessCycleCommand, List<BusinessCycle> requestList,
			List<BusinessCycle> requestList2);

	public List<BusinessCycle> forceToYearEnd(BusinessCycleCommand businessCycleCommand,
			List<BusinessCycle> requestList, List<BusinessCycle> requestList2);
}
