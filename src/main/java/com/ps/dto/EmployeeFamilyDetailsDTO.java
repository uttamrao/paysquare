package com.ps.dto;

public class EmployeeFamilyDetailsDTO extends AbstractDTO{

   
	private String firstname;
	private String middlename;
	private String lastname;
	private EnumDTO relationship;	

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public EnumDTO getRelationship() {
		return relationship;
	}
	public void setRelationship(EnumDTO relationship) {
		this.relationship = relationship;
	}

}
