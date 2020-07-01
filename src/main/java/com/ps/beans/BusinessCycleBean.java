package com.ps.beans;

import com.ps.entities.tenant.BusinessCycleDefinition;

public class BusinessCycleBean {

	BusinessCycleDefinition businessCycleDefinition;	
	int lastCreatedYear;
	int noOfYears = 1;

	public BusinessCycleDefinition getBusinessCycleDefinition() {
		return businessCycleDefinition;
	}

	public void setBusinessCycleDefinition(BusinessCycleDefinition businessCycleDefinition) {
		this.businessCycleDefinition = businessCycleDefinition;
	}

	public int getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}

	public int getLastCreatedYear() {
		return lastCreatedYear;
	}

	public void setLastCreatedYear(int lastCreatedYear) {
		this.lastCreatedYear = lastCreatedYear;
	}

}
