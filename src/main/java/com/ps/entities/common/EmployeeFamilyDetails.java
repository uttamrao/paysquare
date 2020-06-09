package com.ps.entities.common;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ps.entities.common.Statuses.EmployeeRelationshipStatuses;

@Entity
public class EmployeeFamilyDetails extends AbstractEntity{

	@ManyToOne
	@JoinColumn(name="employeeId",referencedColumnName = "id")
    private EmployeeDetails employee;
	private String firstname;
	private String middlename;
	private String lastname;
	@Enumerated(EnumType.STRING)
	private EmployeeRelationshipStatuses relationship;
	

	public EmployeeDetails getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDetails employee) {
		this.employee = employee;
	}
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
	public EmployeeRelationshipStatuses getRelationship() {
		return relationship;
	}
	public void setRelationship(EmployeeRelationshipStatuses relationship) {
		this.relationship = relationship;
	}
}
