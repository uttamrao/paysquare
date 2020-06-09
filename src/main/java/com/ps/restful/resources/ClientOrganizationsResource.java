package com.ps.restful.resources;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ps.restful.dto.request.ClientOrganizationsRequestDTO;
import com.ps.restful.resources.response.handler.Response;

public interface ClientOrganizationsResource  {

	public static final String RESOURCE_PATH = "/client-organisations";
	
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> add(@RequestBody ClientOrganizationsRequestDTO requestDTO);
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> getAll();
}
