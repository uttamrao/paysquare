package com.ps.dto;

public class MetaDTO implements DTO {
	
	private long timestamp;
	private String path ="";
	private String user="";

	public long getTimestamp() {
		return timestamp;
	}
	

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	

	public String getPath() {
		return path;
	}
	

	public void setPath(String path) {
		this.path = path;
	}


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}