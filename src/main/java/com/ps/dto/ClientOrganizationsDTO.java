package com.ps.dto;

public class ClientOrganizationsDTO {
	
	private String name;
	private String mappedDatabaseName;
	private String headOfficeAddress;
	private String country;
	
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
