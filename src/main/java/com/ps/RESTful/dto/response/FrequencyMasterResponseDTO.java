package com.ps.RESTful.dto.response;

import com.ps.dto.FrequencyMasterDTO;

public class FrequencyMasterResponseDTO extends FrequencyMasterDTO {

	private int id;
	
	private String createDateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

}
