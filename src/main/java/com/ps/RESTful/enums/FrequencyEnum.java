package com.ps.RESTful.enums;

public enum FrequencyEnum {
	
	MONTHLY("Monthly","MO",1),
	WEEKLY("Weekly","WK",4),
	BI_WEEKLY("Bi-Weekly","BW",2),
	SEMI_MONTHLY("Semi-Monthly","SM",2),
	ADHOC("Adhoc","AD",0);
	
	private String value;
	
	private String shortCode;
	
	private double count;
	
	private FrequencyEnum(String value,String shortCode, double count) {
		this.value = value;
		this.shortCode = shortCode;
		this.count = count;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
	
	public static boolean isValid(String name) {			
		boolean isValid = false;
		
		for (FrequencyEnum frequency: FrequencyEnum.values()) {
			
			if(frequency.name().equals(name)) {
				isValid = true;
				break;
			}
		}			
		return isValid;
	}

}
