package com.ps.RESTful.enums;

public enum ServiceTypeEnum {
	
	PAYROLL("Payroll","PY"),
	LEAVE("Leave","LV"),
	ATTENDANCE("Attendance","AT"),
	REIMBURSEMENT("Reimbursement","RE");
	
	private String value;
	
	private String shortCode;
	
	private ServiceTypeEnum(String value, String shortCode) {
		this.value = value;
		this.shortCode = shortCode;
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
	
	public static boolean isValid(String name) {			
		boolean isValid = false;
		
		for (ServiceTypeEnum service: ServiceTypeEnum.values()) {
			
			if(service.name().equals(name)) {
				isValid = true;
				break;
			}
		}			
		return isValid;
	}

}
