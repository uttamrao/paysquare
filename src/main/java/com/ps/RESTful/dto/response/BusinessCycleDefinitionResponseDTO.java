package com.ps.RESTful.dto.response;

import com.ps.dto.BusinessCycleDefinitionDTO;

public class BusinessCycleDefinitionResponseDTO extends BusinessCycleDefinitionDTO{

	private int id;	
	
	private BusinessYearDefinitionResponseDTO businessYearDefinition;
	
	private FrequencyMasterResponseDTO frequency;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BusinessYearDefinitionResponseDTO getBusinessYearDefinition() {
		return businessYearDefinition;
	}

	public void setBusinessYearDefinition(BusinessYearDefinitionResponseDTO businessYearDefinition) {
		this.businessYearDefinition = businessYearDefinition;
	}

	public FrequencyMasterResponseDTO getFrequency() {
		return frequency;
	}

	public void setFrequency(FrequencyMasterResponseDTO frequency) {
		this.frequency = frequency;
	}
	
}
