package com.ps.dto;

public class ErrorDTO implements DTO{

	private String code;
	private String description;
	private String message;
	
	public ErrorDTO() {
		super();
	}
	
	public ErrorDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public ErrorDTO(String code, String message, String description) {
		super();
		this.code = code;
		this.message = message;
		this.description = description;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
