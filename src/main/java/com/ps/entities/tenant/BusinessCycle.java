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

import com.ps.entities.master.AbstractTimeEntity;

@Entity
public class BusinessCycle {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="businessCycleId")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="businessCycleDefinitionId",referencedColumnName = "businessCycleDefinitionId")
	private BusinessCycleDefinition businessCycleDefinition;
	
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	private Date toDate;
	
	private int periodId;
	
	private String periodName;
	
	private boolean isLocked;
	
	private boolean isActive = true;

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
}
