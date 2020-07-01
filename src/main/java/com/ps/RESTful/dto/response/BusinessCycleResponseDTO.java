package com.ps.RESTful.dto.response;

import com.ps.dto.BusinessCycleDTO;

public class BusinessCycleResponseDTO extends BusinessCycleDTO{

	private int id;
	
	private BusinessCycleDefinitionResponseDTO businessCycleDefinition;
	
	private String fromDate;
	
	private String toDate;
	
	private int periodId;
	
	private String periodName;

	public BusinessCycleDefinitionResponseDTO getBusinessCycleDefinition() {
		return businessCycleDefinition;
	}

	public void setBusinessCycleDefinition(BusinessCycleDefinitionResponseDTO businessCycleDefinition) {
		this.businessCycleDefinition = businessCycleDefinition;
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
