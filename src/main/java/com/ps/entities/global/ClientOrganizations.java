package com.ps.entities.global;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientOrganizations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String mappedDatabaseName;
	private boolean initialize;
	@Column(columnDefinition = "varchar(255)")
	private String headOfficeAddress;
	@Column(columnDefinition = "varchar(255)")
	private String country;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMappedDatabaseName() {
		return mappedDatabaseName;
	}
	public void setMappedDatabaseName(String mappedDatabaseName) {
		this.mappedDatabaseName = mappedDatabaseName;
	}
	public boolean isInitialize() {
		return initialize;
	}
	public void setInitialize(boolean initialize) {
		this.initialize = initialize;
	}
	public String getHeadOfficeAddress() {
		return headOfficeAddress;
	}
	public void setHeadOfficeAddress(String headOfficeAddress) {
		this.headOfficeAddress = headOfficeAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
