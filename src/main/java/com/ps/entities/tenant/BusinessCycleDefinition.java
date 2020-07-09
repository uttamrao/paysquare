package com.ps.entities.tenant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ps.RESTful.enums.WeeksEnum;
import com.ps.entities.master.AbstractTimeEntity;

@Entity
public class BusinessCycleDefinition extends AbstractTimeEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="businessCycleDefinitionId")
	private int id;
	
	private boolean isActive = true;
	
	@Column(name="businessCycleName")
	private String name;
	
	private String serviceName;
	
	@Enumerated(EnumType.STRING)
	private WeeksEnum weekStartDefinition;
		
	private int reoccuranceDays;
	
	private boolean forceToBusinessYearEnd;
	
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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public WeeksEnum getWeekStartDefinition() {
		return weekStartDefinition;
	}

	public void setWeekStartDefinition(WeeksEnum weekStartDefinition) {
		this.weekStartDefinition = weekStartDefinition;
	}

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

}
