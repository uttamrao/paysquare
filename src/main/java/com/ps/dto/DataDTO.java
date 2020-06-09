package com.ps.dto;

import java.util.List;

public class DataDTO implements DTO {
	private static final long serialVersionUID = 1L;
	
	private String message;
	private List<Object> results;
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the results
	 */
	public List<Object> getResults() {
		return results;
	}
	
	/**
	 * @param results the results to set
	 */
	public void setResults(List<Object> results) {
		this.results = results;
	}

}
