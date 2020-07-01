package com.ps.dto;

public class BusinessCycleDTO extends AbstractTimeDTO {
	
	private int noOfYears;
	
	private boolean isLocked;
	
	public int getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(int noOfYears) {
		this.noOfYears = noOfYears;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
}
