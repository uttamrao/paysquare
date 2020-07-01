package com.ps.services.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.services.BusinessCycleCommand;
import com.ps.services.BusinessCycleInvoker;

@Service
public class BusinessCycleInvokerImpl implements BusinessCycleInvoker {
		
	Logger logger = Logger.getLogger(BusinessCycleInvokerImpl.class);

	@Override
	public List<BusinessCycle> createCycle(BusinessCycleCommand businessCycleCommand, BusinessCycleBean businessCycleBean) {
		logger.debug("In BusinessCycleInvokerImpl createCycle method");		
		return businessCycleCommand.create(businessCycleBean);
	}
	
}
