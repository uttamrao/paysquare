package com.ps.RESTful.enums;

public enum WeeksEnum {
	
	SUNDAY("Sunday",1),
	MONDAY("Monday",2),
	TUESDAY("Tuesday",3),
	WEDNESDAY("Wednesday",4),
	THURSDAY("Thursday",5),
	FRIDAY("Friday",6),
	SATURDAY("Saturday",7);
	
	private String value;
	
	private int code;
	
	private WeeksEnum(String value, int code) {
		this.value = value;
		this.code = code;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public static boolean isValid(String name) {			
		boolean isValid = false;
		
		for (WeeksEnum week: WeeksEnum.values()) {
			
			if(week.name().equals(name)) {
				isValid = true;
				break;
			}
		}			
		return isValid;
	}

}
