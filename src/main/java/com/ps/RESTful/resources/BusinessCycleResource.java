package com.ps.RESTful.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ps.RESTful.dto.request.BusinessCycleRequestDTO;
import com.ps.RESTful.dto.request.BusinessCycleUpdateRequestDTO;
import com.ps.RESTful.resources.response.handler.Response;

public interface BusinessCycleResource {

	public final static String RESOURCE_PATH = "/business-cycle";
	public final static String UPDATE_PATH = "/update/{cycleDefinitionId}/{businessYear}";
	public final static String GET_CYCLE_DEFINITION_PATH = "/cycle-definition/{cycleDefinitionId}/{businessYear}";
	public final static String DELETE_CYCLE_DEFINITION_PATH = "/cycles/{cycleDefinitionId}/{businessYear}";
	public final static String FORCE_END_PATH = "/force-end/{cycleDefinitionId}/{businessYear}";

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> add(@RequestBody BusinessCycleRequestDTO requestDTO);

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAll(
			@RequestParam(name = "cycleDefinitionId", required = false) Integer cycleDefinitionId);

	@GetMapping(path = GET_CYCLE_DEFINITION_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAllByBusinessCycleIdAndBusinessYear(
			@PathVariable(name = "cycleDefinitionId") int cycleDefinitionId,
			@PathVariable(name = "businessYear") String businessYear);

	@DeleteMapping(path = GET_CYCLE_DEFINITION_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> softDeleteByBusinessCycleIdAndBusinessYear(
			@PathVariable(name = "cycleDefinitionId") int cycleDefinitionId,
			@PathVariable(name = "businessYear") String businessYear);

	@DeleteMapping(path = DELETE_CYCLE_DEFINITION_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> hardDeleteByBusinessCycleIdAndBusinessYear(
			@PathVariable(name = "cycleDefinitionId") int cycleDefinitionId,
			@PathVariable(name = "businessYear") String businessYear);

	@PutMapping(path = UPDATE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> update(@RequestBody BusinessCycleUpdateRequestDTO requestDTO,
			@PathVariable(name = "cycleDefinitionId") int cycleDefinitionId,
			@PathVariable(name = "businessYear") String businessYear);

	@PutMapping(path = FORCE_END_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> forceEnd(@RequestBody BusinessCycleUpdateRequestDTO requestDTO,
			@PathVariable(name = "cycleDefinitionId") int cycleDefinitionId,
			@PathVariable(name = "businessYear") String businessYear);
}
