package com.ps.RESTful.dto.response;

import com.ps.dto.BusinessCycleDTO;

public class BusinessCycleResponseDTO extends BusinessCycleDTO {

	private int id;

	private int periodId;

	private String periodName;

	private boolean isLocked;

	private String fromDate;

	private String toDate;

	private String businessYear;
	private int noOfDays;
	private int noOfCycles;
	private boolean isForceToYearEnd;
	private String remark;
	private boolean isUsed;

	private boolean isActive;

	private BusinessCycleDefinitionResponseDTO businessCycleDefinition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
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

	public String getBusinessYear() {
		return businessYear;
	}

	public void setBusinessYear(String businessYear) {
		this.businessYear = businessYear;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public int getNoOfCycles() {
		return noOfCycles;
	}

	public void setNoOfCycles(int noOfCycles) {
		this.noOfCycles = noOfCycles;
	}

	public boolean isForceToYearEnd() {
		return isForceToYearEnd;
	}

	public void setForceToYearEnd(boolean isForceToYearEnd) {
		this.isForceToYearEnd = isForceToYearEnd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "BusinessCycleResponseDTO [id=" + id + ", businessCycleDefinition=" + businessCycleDefinition
				+ ", periodId=" + periodId + ", periodName=" + periodName + ", isLocked=" + isLocked + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", businessYear=" + businessYear + ", noOfDays=" + noOfDays
				+ ", noOfCycles=" + noOfCycles + ", isForceToYearEnd=" + isForceToYearEnd + ", remark=" + remark
				+ ", isUsed=" + isUsed + ", isActive=" + isActive + "]";
	}

}
