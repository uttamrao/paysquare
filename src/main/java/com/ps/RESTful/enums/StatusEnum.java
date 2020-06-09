package com.ps.RESTful.enums;

public enum StatusEnum {
	
	SUCCESS("Success"),
	FAILURE("Failure");
	
	private String value;
	
	private StatusEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
