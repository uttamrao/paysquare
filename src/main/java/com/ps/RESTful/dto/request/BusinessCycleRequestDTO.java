package com.ps.RESTful.dto.request;

import com.ps.dto.BusinessCycleDTO;

public class BusinessCycleRequestDTO extends BusinessCycleDTO {

	private int businessCycleDefinitionId;
	private String businessYear;

	public int getBusinessCycleDefinitionId() {
		return businessCycleDefinitionId;
	}

	public void setBusinessCycleDefinitionId(int businessCycleDefinitionId) {
		this.businessCycleDefinitionId = businessCycleDefinitionId;
	}

	public String getBusinessYear() {
		return businessYear;
	}

	public void setBusinessYear(String businessYear) {
		this.businessYear = businessYear;
	}

}
