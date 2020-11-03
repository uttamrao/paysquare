package com.ps.entities.tenant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BusinessCycle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "businessCycleId")
	private int id;

	@ManyToOne
	@JoinColumn(name = "businessCycleDefinitionId", referencedColumnName = "businessCycleDefinitionId")
	private BusinessCycleDefinition businessCycleDefinition;

	private int periodId;

	private String periodName;

	private boolean isLocked;

	@Temporal(TemporalType.DATE)
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	private Date toDate;

	private String businessYear;
	private int noOfDays;
	private int noOfCycles;
	private boolean isForceToYearEnd;
	private String remark;
	private boolean isUsed;

	private boolean isActive;

	private boolean isAdjustedToNextCycle;

	// constructor
	public BusinessCycle() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BusinessCycleDefinition getBusinessCycleDefinition() {
		return businessCycleDefinition;
	}

	public void setBusinessCycleDefinition(BusinessCycleDefinition businessCycleDefinition) {
		this.businessCycleDefinition = businessCycleDefinition;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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

	public boolean isAdjustedToNextCycle() {
		return isAdjustedToNextCycle;
	}

	public void setAdjustedToNextCycle(boolean isAdjustedToNextCycle) {
		this.isAdjustedToNextCycle = isAdjustedToNextCycle;
	}

	@Override
	public String toString() {
		return "BusinessCycle [id=" + id + ", businessCycleDefinition=" + businessCycleDefinition + ", periodId="
				+ periodId + ", periodName=" + periodName + ", isLocked=" + isLocked + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", businessYear=" + businessYear + ", noOfDays=" + noOfDays + ", noOfCycles="
				+ noOfCycles + ", isForceToYearEnd=" + isForceToYearEnd + ", remark=" + remark + ", isUsed=" + isUsed
				+ ", isActive=" + isActive + ", isAdjustedToNextCycle=" + isAdjustedToNextCycle + "]";
	}
}
