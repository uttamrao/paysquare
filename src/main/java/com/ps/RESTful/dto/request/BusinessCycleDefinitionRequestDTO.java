package com.ps.RESTful.dto.request;

import java.util.List;

import com.ps.dto.BusinessCycleDefinitionDTO;

public class BusinessCycleDefinitionRequestDTO extends BusinessCycleDefinitionDTO {

	private int id;

	private int frequencyMasterId;

	private int businessYearDefinitionId;

	private List<String> serviceName;

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

	public List<String> getServiceName() {
		return serviceName;
	}

	public void setServiceName(List<String> serviceName) {
		this.serviceName = serviceName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BusinessCycleDefinitionRequestDTO [id=" + id + ", frequencyMasterId=" + frequencyMasterId
				+ ", businessYearDefinitionId=" + businessYearDefinitionId + ", serviceName=" + serviceName + "]";
	}
}
