package com.ps.beans;

import com.ps.entities.tenant.BusinessCycleDefinition;

public class BusinessCycleBean {

	BusinessCycleDefinition businessCycleDefinition;
	String businessYear;
	int lastCreatedYear;
	int noOfYears = 1;

	public BusinessCycleBean() {
		super();
	}

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

	public String getBusinessYear() {
		return businessYear;
	}

	public void setBusinessYear(String businessYear) {
		this.businessYear = businessYear;
	}

	@Override
	public String toString() {
		return "BusinessCycleBean [businessCycleDefinition=" + businessCycleDefinition + ", businessYear="
				+ businessYear + ", lastCreatedYear=" + lastCreatedYear + ", noOfYears=" + noOfYears + "]";
	}
}
