package com.ps.RESTful.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ps.RESTful.dto.request.BusinessYearDefinitionRequestDTO;
import com.ps.RESTful.resources.response.handler.Response;

public interface BusinessYearDefinitionResource {

	public final static String RESOURCE_PATH = "/business-year"; 
	public final static String UPDATE_PATH = "/business-year/update/{resourceId}"; 
	
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> add(@RequestBody BusinessYearDefinitionRequestDTO requestDTO);
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> getAll();
	
	@PutMapping(path = UPDATE_PATH, 
				consumes = MediaType.APPLICATION_JSON_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> update(@RequestBody BusinessYearDefinitionRequestDTO requestDTO,
			@PathVariable("resourceId") int resourceId);
}
