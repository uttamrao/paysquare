package com.ps.RESTful.enums;

public enum FrequencyEnum {
	
	MONTHLY("Monthly","MO",1,1),
	WEEKLY("Weekly","WK",1,1),
	BI_WEEKLY("Bi-Weekly","BW",1,2),
	SEMI_MONTHLY("Semi-Monthly","SM",2,1),
	ADHOC_MONTHLY("Adhoc-Monthly","AD",0,0),
	ADHOC_WEEKLY("Adhoc-Weekly","AD",0,0);
	
	private String value;
	
	private String shortCode;
	
	private int paymentCount;

	private int paymentFrequency;

	private FrequencyEnum(String value,String shortCode, int paymentCount, int paymentFrequency) {
		this.value = value;
		this.shortCode = shortCode;
		this.paymentCount = paymentCount;
		this.paymentFrequency = paymentFrequency;
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
		
		for (FrequencyEnum frequency: FrequencyEnum.values()) {
			
			if(frequency.name().equals(name)) {
				isValid = true;
				break;
			}
		}			
		return isValid;
	}

	public int getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(int paymentCount) {
		this.paymentCount = paymentCount;
	}

	public int getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(int paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

}
