package com.ps.dto;

public class BusinessCycleDefinitionDTO extends AbstractTimeDTO {

	private boolean isActive;

	private String name;

	private boolean isUsed;

	private String description;

	private int reoccuranceDays;

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

//	public List<String> getServiceName() {
//		return serviceName;
//	}
//
//	public void setServiceName(List<String> serviceName) {
//		this.serviceName = serviceName;
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

	public int getReoccuranceDays() {
		return reoccuranceDays;
	}

	public void setReoccuranceDays(int reoccuranceDays) {
		this.reoccuranceDays = reoccuranceDays;
	}

	@Override
	public String toString() {
		return "BusinessCycleDefinitionDTO [isActive=" + isActive + ", name=" + name + ", isUsed=" + isUsed
				+ ", description=" + description + ", reoccuranceDays=" + reoccuranceDays + "]";
	}

}
