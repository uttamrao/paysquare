package com.ps.dto;

public class BusinessYearDefinitionDTO {
	
	private int id;
	
	private String fromDate;
	
	private String toDate;
	
	private String description;

	private boolean isUsed;
	
	private boolean isActive = true;
	
	private String createdBy;
	
	

	public BusinessYearDefinitionDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setIsUSed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	@Override
	public String toString() {
		return "BusinessYearDefinitionDTO [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", description=" + description + ", isUsed=" + isUsed + ", isActive=" + isActive + ", createdBy="
				+ createdBy + "]";
	}

}


