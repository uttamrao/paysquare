package com.ps.RESTful.dto.response;

import com.ps.dto.BusinessYearDefinitionDTO;

public class BusinessYearDefinitionResponseDTO extends BusinessYearDefinitionDTO {

	private int id;
	
	private String createdDateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
