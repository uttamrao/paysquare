package com.ps.RESTful.dto.request;

import com.ps.dto.BusinessCycleDTO;

public class BusinessCycleRequestDTO extends BusinessCycleDTO{

	private int businessCycleDefinitionId;

	public int getBusinessCycleDefinitionId() {
		return businessCycleDefinitionId;
	}

	public void setBusinessCycleDefinitionId(int businessCycleDefinitionId) {
		this.businessCycleDefinitionId = businessCycleDefinitionId;
	}

}
