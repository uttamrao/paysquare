package com.ps.restful.resources;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ps.restful.dto.request.EmployeePersonalInformationRequestDTO;
import com.ps.restful.resources.response.handler.Response;

public interface EmployeePersonalInformationResource  {

	public static final String RESOURCE_PATH = "/employee-personal-information";
	public static final String UPDATE_PATH = "/{resourceId}";

	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> add(@RequestBody EmployeePersonalInformationRequestDTO requestDTO);
	
	@PutMapping(
			path = UPDATE_PATH,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> update(@PathVariable("resourceId") int resourceId,
			@RequestBody EmployeePersonalInformationRequestDTO requestDTO);
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> getAll(@RequestParam(name = "employeeId",
			required = false) Integer employeeId);
	
	@GetMapping(
			path = "/address",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> getAddress(@RequestParam(name = "employeeId",
			required = false) Integer employeeId);
}
