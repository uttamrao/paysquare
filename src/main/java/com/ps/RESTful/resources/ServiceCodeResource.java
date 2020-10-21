package com.ps.RESTful.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.ps.RESTful.resources.response.handler.Response;

public interface ServiceCodeResource {
	public final static String RESOURCE_PATH = "/service-code";

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAll();
}
