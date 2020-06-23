package com.ps.RESTful.dto.request;

import com.ps.dto.BusinessCycleDefinitionDTO;

public class BusinessCycleDefinitionRequestDTO extends BusinessCycleDefinitionDTO{

	private int frequencyMasterId;
	
	private int businessYearDefinitionId;

	public int getFrequencyMasterId() {
		return frequencyMasterId;
	}

	public void setFrequencyMasterId(int frequencyMasterId) {
		this.frequencyMasterId = frequencyMasterId;
	}

	public int getBusinessYearDefinitionId() {
		return businessYearDefinitionId;
	}

	public void setBusinessYearDefinitionId(int businessYearDefinitionId) {
		this.businessYearDefinitionId = businessYearDefinitionId;
	}

}
