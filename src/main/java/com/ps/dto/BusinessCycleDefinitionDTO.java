package com.ps.dto;

public class BusinessCycleDefinitionDTO extends AbstractTimeDTO {

	private boolean isActive;

	private String name;

	private String serviceName;

//	private String weekStartDefinition;
//
//	private int reOccuranceDays;
//
//	private boolean forceToBusinessYearEnd;

	private boolean isUsed;

	private String description;

	public BusinessCycleDefinitionDTO() {
		super();
	}

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

//	public String getWeekStartDefinition() {
//		return weekStartDefinition;
//	}
//
//	public void setWeekStartDefinition(String weekStartDefinition) {
//		this.weekStartDefinition = weekStartDefinition;
//	}
//
//	public int getReOccuranceDays() {
//		return reOccuranceDays;
//	}
//
//	public void setReOccuranceDays(int reOccuranceDays) {
//		this.reOccuranceDays = reOccuranceDays;
//	}
//
//	public boolean isForceToBusinessYearEnd() {
//		return forceToBusinessYearEnd;
//	}
//
//	public void setForceToBusinessYearEnd(boolean forceToBusinessYearEnd) {
//		this.forceToBusinessYearEnd = forceToBusinessYearEnd;
//	}

	public boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BusinessCycleDefinitionDTO [isActive=" + isActive + ", name=" + name + ", serviceName=" + serviceName
				+ ", isUsed=" + isUsed + ", description=" + description + "]";
	}

}
