package com.ps.entities.tenant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ps.entities.master.AbstractTimeEntity;

public class BusinessCycle extends AbstractTimeEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="businessCycleId")
	private int id;
	
	private boolean isActive;
	
	@Column(name="businessCycleName")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="frequencyMasterId",referencedColumnName = "frequencyMasterId")
	private FrequencyMaster frequencyMaster;
	
	@ManyToOne
	@JoinColumn(name="businessYearDefinitionId",referencedColumnName = "businessYearDefinitionId")
	private BusinessYearDefinition businessYearDefinition;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public FrequencyMaster getFrequencyMaster() {
		return frequencyMaster;
	}

	public void setFrequencyMaster(FrequencyMaster frequencyMaster) {
		this.frequencyMaster = frequencyMaster;
	}

	public BusinessYearDefinition getBusinessYearDefinition() {
		return businessYearDefinition;
	}

	public void setBusinessYearDefinition(BusinessYearDefinition businessYearDefinition) {
		this.businessYearDefinition = businessYearDefinition;
	}
	
}
