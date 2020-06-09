package com.ps.restful.resources;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ps.restful.dto.request.EmployeeDetailsRequestDTO;
import com.ps.restful.resources.response.handler.Response;

public interface EmployeeDetailsResource  {

	public static final String RESOURCE_PATH = "/employee-details";
	public static final String RESOURCE_ID_PATH = "/{resourceId}";
	public static final String GET_ENUM_PATH = "/joiningStage";
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> add(@RequestBody EmployeeDetailsRequestDTO requestDTO);
	
	@PutMapping(
			path=RESOURCE_ID_PATH,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> update(@PathVariable("resourceId") int resourceId,@RequestBody EmployeeDetailsRequestDTO requestDTO);
	
	@DeleteMapping(
			path=RESOURCE_ID_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> delete(@PathVariable("resourceId") int resourceId);
	
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> getAll(@RequestParam(name = "companyId",
			required = false) Long companyId);
	
	@GetMapping(
			path = GET_ENUM_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> getJoinStageEnums();
}
