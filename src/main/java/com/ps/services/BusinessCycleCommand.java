package com.ps.services;

import java.util.List;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;

public interface BusinessCycleCommand {

	public List<BusinessCycle> create(BusinessCycleBean businessCycleBean);
}
