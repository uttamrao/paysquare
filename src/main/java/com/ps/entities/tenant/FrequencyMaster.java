package com.ps.entities.tenant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ps.RESTful.enums.FrequencyEnum;

@Entity
public class FrequencyMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="frequencyMasterId")
	private int id;
	
	@Column(name="frequencyName")
	@Enumerated(EnumType.STRING)
	private FrequencyEnum name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime = new Date();
	
	private String createdBy;
	
	private String description;
			
	private boolean isActive = true;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FrequencyEnum getName() {
		return name;
	}

	public void setName(FrequencyEnum name) {
		this.name = name;
	}
	
}
