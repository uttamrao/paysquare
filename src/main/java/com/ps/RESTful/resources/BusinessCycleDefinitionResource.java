package com.ps.RESTful.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ps.RESTful.dto.request.BusinessCycleDefinitionRequestDTO;
import com.ps.RESTful.resources.response.handler.Response;

public interface BusinessCycleDefinitionResource {

	public final static String RESOURCE_PATH = "/business-cycle-definition";
	public final static String UPDATE_PATH = "/update/{resourceId}";
	public final static String RESOURCE_ID_PATH = "/{resourceId}";

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> add(@RequestBody BusinessCycleDefinitionRequestDTO requestDTO);

	@PutMapping(path = UPDATE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> update(@PathVariable("resourceId") int resourceId,
			@RequestBody BusinessCycleDefinitionRequestDTO requestDTO);

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAll();

}
