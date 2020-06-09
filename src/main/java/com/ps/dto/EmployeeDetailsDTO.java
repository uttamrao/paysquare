package com.ps.dto;

public class EmployeeDetailsDTO extends AbstractDTO  {

	private String firstname;
	private String middlename;
	private String lastname;
	private String email;
	private String jobTitle;
	private String department;
	private String reportingManager;
	private long joiningDate;
	private String uanNumber;
	private String panNumber;
	private EnumDTO joiningStage;
	private EnumDTO joiningStatus;	
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
	public EnumDTO getJoiningStage() {
		return joiningStage;
	}
	public void setJoiningStage(EnumDTO joiningStage) {
		this.joiningStage = joiningStage;
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
	public EnumDTO getJoiningStatus() {
		return joiningStatus;
	}
	public void setJoiningStatus(EnumDTO joiningStatus) {
		this.joiningStatus = joiningStatus;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getUanNumber() {
		return uanNumber;
	}
	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

}
