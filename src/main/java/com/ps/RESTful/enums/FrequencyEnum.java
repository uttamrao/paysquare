package com.ps.RESTful.enums;

public enum FrequencyEnum {

	MONTHLY("Monthly", "MM"), WEEKLY("Weekly", "WK"), BI_WEEKLY("Bi-Weekly", "BW"), SEMI_MONTHLY("Semi-Monthly", "SM"),
	DAILY("Daily", "DD"), YEARLY("Yearly", "YY"), ADHOC("Adhoc", "AD");

	private String value;

	private String shortCode;

	private int paymentCount;

	private int paymentFrequency;

	private FrequencyEnum(String value, String shortCode) {
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

		for (FrequencyEnum frequency : FrequencyEnum.values()) {

			if (frequency.name().equals(name)) {
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
