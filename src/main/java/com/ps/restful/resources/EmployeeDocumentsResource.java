package com.ps.restful.resources;


import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ps.restful.dto.request.EmployeeDocumentsRequestDTO;
import com.ps.restful.resources.response.handler.Response;

public interface EmployeeDocumentsResource  {

	public static final String RESOURCE_PATH = "/employee-documents";
	public static final String UPLOAD_PATH = "/upload";
	public static final String DOWNLOAD_PATH = "/download/{resourceId}";
	
	@PostMapping(
			path= UPLOAD_PATH,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Response> upload(@RequestParam("file") MultipartFile file, @ModelAttribute EmployeeDocumentsRequestDTO requestDTO);

	@GetMapping(
			path= DOWNLOAD_PATH,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Resource> downloadFile(@PathVariable int resourceId);
	
	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Resource> downloadAll(@RequestParam(name = "employeeId",
			required = false) Integer employeeId);
}
