package com.ps.entities.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ps.entities.common.Statuses.EmployeeJoiningStage;
import com.ps.entities.common.Statuses.EmployeeJoiningStatuses;

@Entity
public class EmployeeDetails extends AbstractEntity  {

	private String firstname;
	private String middlename;
	private String lastname;
	private String email;
	private String jobTitle;
	private String department;
	private String reportingManager;
	private long joiningDate;
	private String UAN;
	private String panNumber;
	@Enumerated(EnumType.STRING)
	private EmployeeJoiningStage joiningStage;
	@Enumerated(EnumType.STRING)
	private EmployeeJoiningStatuses joiningStatus;
	@Column(columnDefinition = "numeric(19, 0) default 0")
	private long companyId;

	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getReportingManager() {
		return reportingManager;
	}
	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
	public long getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(long joiningDate) {
		this.joiningDate = joiningDate;
	}
	public EmployeeJoiningStage getJoiningStage() {
		return joiningStage;
	}
	public void setJoiningStage(EmployeeJoiningStage joiningStage) {
		this.joiningStage = joiningStage;
	}
	public EmployeeJoiningStatuses getJoiningStatus() {
		return joiningStatus;
	}
	public void setJoiningStatus(EmployeeJoiningStatuses joiningStatus) {
		this.joiningStatus = joiningStatus;
	}
	public String getUAN() {
		return UAN;
	}
	public void setUAN(String uAN) {
		UAN = uAN;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

}
