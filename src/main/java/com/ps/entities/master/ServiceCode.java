package com.ps.entities.master;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServiceCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int serviceCodeId;
	private String serviceName;
	private String serviceCode;
	private String createdBy;
	private Date createDateTime;
	private boolean isActive;

	public ServiceCode() {
		super();
	}

	public ServiceCode(int serviceCodeId, String serviceName, String serviceCode, String createdBy, Date createDateTime,
			boolean isActive) {
		super();
		this.serviceCodeId = serviceCodeId;
		this.serviceName = serviceName;
		this.serviceCode = serviceCode;
		this.createdBy = createdBy;
		this.createDateTime = createDateTime;
		this.isActive = isActive;
	}

	public int getServiceCodeId() {
		return serviceCodeId;
	}

	public void setServiceCodeId(int serviceCodeId) {
		this.serviceCodeId = serviceCodeId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ServiceCode [serviceCodeId=" + serviceCodeId + ", serviceName=" + serviceName + ", serviceCode="
				+ serviceCode + ", createdBy=" + createdBy + ", createDateTime=" + createDateTime + ", isActive="
				+ isActive + "]";
	}
}
