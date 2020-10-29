package com.ps.RESTful.dto.request;

import com.ps.dto.BusinessCycleDTO;

public class BusinessCycleUpdateRequestDTO extends BusinessCycleDTO {

	private String fromDate;
	private String toDate;
	private String remark;
	private boolean status;
	private boolean isForceToBusinessYearEnd;

	public BusinessCycleUpdateRequestDTO() {
		super();
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isForceToBusinessYearEnd() {
		return isForceToBusinessYearEnd;
	}

	public void setForceToBusinessYearEnd(boolean isForceToBusinessYearEnd) {
		this.isForceToBusinessYearEnd = isForceToBusinessYearEnd;
	}

	@Override
	public String toString() {
		return "BusinessCycleUpdateRequestDTO [fromDate=" + fromDate + ", toDate=" + toDate + ", remark=" + remark
				+ ", status=" + status + "]";
	}

}
