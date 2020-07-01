package com.ps.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.services.BusinessCycleCommand;

@Service("BusinessCycleAdhocWeeklyImpl")
public class BusinessCycleAdhocWeeklyImpl implements BusinessCycleCommand {

	@Override
	public List<BusinessCycle> create(BusinessCycleBean businessCycleBean) {
			
		return null;
	}
}
