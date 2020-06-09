package com.ps.restful.resources.response.handler;

import java.util.ArrayList;
import java.util.List;

import com.ps.dto.DTO;
import com.ps.dto.DataDTO;
import com.ps.dto.ErrorDTO;
import com.ps.dto.MetaDTO;

public class ResponseBuilder {
	private List<Object> results = new ArrayList<Object>();
	private String message ="";
	private List<DTO> errors = new ArrayList<DTO>();
	
	private ResponseBuilder() {}
	
	public static ResponseBuilder builder() {
		ResponseBuilder builder = new ResponseBuilder();
		return builder;
	}

	public ResponseBuilder results(List results) {
		if (results != null) {
			this.results.addAll(results);
		}
		return this;
	}
	
	public ResponseBuilder result(Object result) {
		if (results != null) {
			this.results.add(result);
		}
		return this;
	}
	
	public ResponseBuilder message(String message) {
		this.message = message;
		return this;
	}
	
	public ResponseBuilder errors(List<ErrorDTO> errors) {
		if (errors != null) {
			this.errors.addAll(errors);
		}
		return this;
	}
	
	public ResponseBuilder error(ErrorDTO error) {
		if (errors != null) {
			this.errors.add(error);
		}
		return this;
	}
	
	public Response build() {
		DataDTO dataDTO = new DataDTO();
		dataDTO.setMessage(message);
		dataDTO.setResults(results);
		
		MetaDTO metaDTO = new MetaDTO();
		metaDTO.setTimestamp(System.currentTimeMillis());
		
		Response response = new ResponseImpl();
		response.setData(dataDTO);
		response.setErrors(errors);
		response.setMeta(metaDTO);
		
		return response;
	}
}
