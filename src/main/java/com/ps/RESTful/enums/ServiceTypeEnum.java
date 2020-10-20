package com.ps.RESTful.enums;

public enum ServiceTypeEnum {

	PAYROLL("Payroll", "PAY"), LEAVE("Leave", "LEA"), ATTENDANCE("Attendance", "ATT"),
	REIMBURSEMENT("Reimbursement", "REI"), LABORLAWCOMPLAINCE("Labor Law Compliance", "LLC"),
	PAYMENTS("payments", "PMT"), HRIS("Hri", "HRI"), SAASSERVICE("SAAS Service", "SAS");

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

		for (ServiceTypeEnum service : ServiceTypeEnum.values()) {

			if (service.getValue().equals(name)) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}

}
