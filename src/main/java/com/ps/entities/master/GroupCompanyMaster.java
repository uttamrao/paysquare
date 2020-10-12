package com.ps.entities.master;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="GroupDBMaster")
public class GroupCompanyMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="groupDBMasterId")
	private int groupCompanyMasterId;
	
	private String serverName;
	
	private String instanceName;
	
	private String databaseName;
	
	private String groupName;
	
	private String userName;
	
	private String password;
	
	private String currency;
	
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;
	
	private boolean isActive;
	
	public int getGroupCompanyMasterId() {
		return groupCompanyMasterId;
	}
	public void setGroupCompanyMasterId(int groupCompanyMasterId) {
		this.groupCompanyMasterId = groupCompanyMasterId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getInstanceName() {
		return instanceName;
	}
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}	
	
	
}
