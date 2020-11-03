package com.ps.RESTful.dto.request;

import java.util.List;

import com.ps.RESTful.dto.response.BusinessCycleResponseDTO;
import com.ps.dto.BusinessCycleDTO;

public class BusinessCycleUpdateRequestDTO extends BusinessCycleDTO {

	private List<BusinessCycleResponseDTO> businessCycleList;
	private boolean isForceToBusinessYearEnd;
	private boolean isAdjustedToNextCycle;

	public BusinessCycleUpdateRequestDTO() {
		super();
	}

	public List<BusinessCycleResponseDTO> getBusinessCycleList() {
		return businessCycleList;
	}

	public void setBusinessCycleList(List<BusinessCycleResponseDTO> businessCycleList) {
		this.businessCycleList = businessCycleList;
	}

	public boolean isForceToBusinessYearEnd() {
		return isForceToBusinessYearEnd;
	}

	public void setForceToBusinessYearEnd(boolean isForceToBusinessYearEnd) {
		this.isForceToBusinessYearEnd = isForceToBusinessYearEnd;
	}

	public boolean isAdjustedToNextCycle() {
		return isAdjustedToNextCycle;
	}

	public void setAdjustedToNextCycle(boolean isAdjustedToNextCycle) {
		this.isAdjustedToNextCycle = isAdjustedToNextCycle;
	}

	@Override
	public String toString() {
		return "BusinessCycleUpdateRequestDTO [businessCycleList=" + businessCycleList + ", isForceToBusinessYearEnd="
				+ isForceToBusinessYearEnd + ", isAdjustedToNextCycle=" + isAdjustedToNextCycle + "]";
	}
}
