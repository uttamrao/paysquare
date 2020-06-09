package com.ps.restful.resources;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ps.restful.dto.request.EmployeeFamilyDetailsRequestDTO;
import com.ps.restful.resources.response.handler.Response;

public interface EmployeeFamilyDetailsResource  {

	public static final String RESOURCE_PATH = "/employee-family-details";
	public static final String UPDATE_PATH = "/{resourceId}";
	public static final String GET_ALL_BY_EMPLOYEE_PATH = "/{employeeId}";
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> add(@RequestBody EmployeeFamilyDetailsRequestDTO requestDTO);
	
	@PutMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> update(@PathVariable("resourceId") int resourceId,@RequestBody EmployeeFamilyDetailsRequestDTO requestDTO);
	
	@GetMapping(
			path = GET_ALL_BY_EMPLOYEE_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> getAllByEmployee(@PathVariable("employeeId") int employeeId);
}
