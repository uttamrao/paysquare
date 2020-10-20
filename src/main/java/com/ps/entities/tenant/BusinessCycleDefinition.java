package com.ps.entities.tenant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ps.entities.master.AbstractTimeEntity;

@Entity
public class BusinessCycleDefinition extends AbstractTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "businessCycleDefinitionId")
	private int id;

	private boolean isActive;

	@Column(name = "cycleName")
	private String name;

	private String serviceName;

	private int reoccuranceDays;

	@Column(name = "isForceYearEnd")
	private boolean forceToBusinessYearEnd = false;

	private boolean isUsed;

	private String description;

	@ManyToOne
	@JoinColumn(name = "frequencyMasterId", referencedColumnName = "frequencyMasterId")
	private FrequencyMaster frequencyMaster;

	@ManyToOne
	@JoinColumn(name = "businessYearDefinitionId", referencedColumnName = "businessYearDefinitionId")
	private BusinessYearDefinition businessYearDefinition;

	public BusinessCycleDefinition() {
		super();
	}

	// copy constructor
	public BusinessCycleDefinition(BusinessCycleDefinition businessCycleDefinition) {
		id = businessCycleDefinition.id;
		isActive = businessCycleDefinition.isActive;
		name = businessCycleDefinition.name;
		serviceName = businessCycleDefinition.serviceName;
		reoccuranceDays = businessCycleDefinition.reoccuranceDays;
		forceToBusinessYearEnd = businessCycleDefinition.forceToBusinessYearEnd;
		isUsed = businessCycleDefinition.isUsed;
		description = businessCycleDefinition.description;
		frequencyMaster = businessCycleDefinition.frequencyMaster;
		businessYearDefinition = businessCycleDefinition.businessYearDefinition;
	}

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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

//	public WeeksEnum getWeekStartDefinition() {
//		return weekStartDefinition;
//	}
//
//	public void setWeekStartDefinition(WeeksEnum weekStartDefinition) {
//		this.weekStartDefinition = weekStartDefinition;
//	}

	public boolean isForceToBusinessYearEnd() {
		return forceToBusinessYearEnd;
	}

	public void setForceToBusinessYearEnd(boolean forceToBusinessYearEnd) {
		this.forceToBusinessYearEnd = forceToBusinessYearEnd;
	}

	public int getReoccuranceDays() {
		return reoccuranceDays;
	}

	public void setReoccuranceDays(int reoccuranceDays) {
		this.reoccuranceDays = reoccuranceDays;
	}

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
		return "BusinessCycleDefinition [id=" + id + ", isActive=" + isActive + ", name=" + name + ", serviceName="
				+ serviceName + ", reoccuranceDays=" + reoccuranceDays + ", forceToBusinessYearEnd="
				+ forceToBusinessYearEnd + ", isUsed=" + isUsed + ", description=" + description + ", frequencyMaster="
				+ frequencyMaster + ", businessYearDefinition=" + businessYearDefinition + "]";
	}

}
