package com.ps.dto;

public class BusinessCycleDefinitionDTO extends AbstractTimeDTO{
	
	private boolean isActive = true;
	
	private String name;
	
	private String serviceName;
	
	private String weekStartDefinition;
	
	private int reOccuranceDays;
	
	private boolean forceToBusinessYearEnd;
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getWeekStartDefinition() {
		return weekStartDefinition;
	}

	public void setWeekStartDefinition(String weekStartDefinition) {
		this.weekStartDefinition = weekStartDefinition;
	}

	public int getReOccuranceDays() {
		return reOccuranceDays;
	}

	public void setReOccuranceDays(int reOccuranceDays) {
		this.reOccuranceDays = reOccuranceDays;
	}

	public boolean isForceToBusinessYearEnd() {
		return forceToBusinessYearEnd;
	}

	public void setForceToBusinessYearEnd(boolean forceToBusinessYearEnd) {
		this.forceToBusinessYearEnd = forceToBusinessYearEnd;
	}

}
