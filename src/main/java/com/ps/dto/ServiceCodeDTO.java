package com.ps.dto;

public class ServiceCodeDTO {

	private int serviceCodeId;
	private String serviceName;
	private String serviceCode;
	private String createdBy;
	private String createDateTime;
	private boolean isActive;

	public ServiceCodeDTO() {
		super();
	}

	public ServiceCodeDTO(int serviceCodeId, String serviceName, String serviceCode, String createdBy,
			String createDateTime, boolean isActive) {
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

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
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
