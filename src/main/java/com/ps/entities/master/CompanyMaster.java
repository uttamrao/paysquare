package com.ps.entities.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="GlobalCompanyMaster")
public class CompanyMaster extends AbstractTimeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="globalCompanyMasterId")
	private int companyMasterId;
	
	private String companyName;
	
	@ManyToOne
	@JoinColumn(name="groupDBMasterId",referencedColumnName = "groupDBMasterId")
	private GroupCompanyMaster groupCompany;
	
	private String companyType;
	
	private String location;
	
	private String gst;
	
	private String pan;
	
	private String tan;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date activateDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
			
	private String emailId;
	
	private boolean isActive;
			
	private String language;
	
	private String currency;
	
	@Column(insertable = false, updatable = false)
	private Date validFrom;
	
	@Column(insertable = false, updatable = false)
	private Date validTo;
	
	public int getCompanyId() {
		return companyMasterId;
	}
	public void setCompanyId(int companyMasterId) {
		this.companyMasterId = companyMasterId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public GroupCompanyMaster getGroupCompany() {
		return groupCompany;
	}
	public void setGroupCompany(GroupCompanyMaster groupCompany) {
		this.groupCompany = groupCompany;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getTan() {
		return tan;
	}
	public void setTan(String tan) {
		this.tan = tan;
	}
	public Date getActivateDate() {
		return activateDate;
	}
	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}	
	
}
