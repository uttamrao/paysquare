package com.ps.RESTful.dto.response;

import com.ps.dto.BusinessYearDefinitionDTO;

public class BusinessYearDefinitionResponseDTO extends BusinessYearDefinitionDTO {

	private int id;
	
	private String yearDefinition;
	
	private String createDateTime;

	
	public BusinessYearDefinitionResponseDTO() {
		super();
	}

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

	public String getYearDefinition() {
		return yearDefinition;
	}

	public void setYearDefinition(String yearDefinition) {
		this.yearDefinition = yearDefinition;
	}

	
}
