package com.ps.restful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ps.entities.common.EmployeeDetails;
import com.ps.entities.common.EmployeeDocuments;
import com.ps.restful.dto.adapter.request.EmployeeDocumentsRequestDTOAdapter;
import com.ps.restful.dto.request.EmployeeDocumentsRequestDTO;
import com.ps.restful.resources.EmployeeDocumentsResource;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;
import com.ps.services.EmployeeDetailsService;
import com.ps.services.EmployeeDocumentsService;



@RestController
@RequestMapping(path = EmployeeDocumentsResource.RESOURCE_PATH)
public class EmployeeDocumentsResourceImpl extends AbstractResourceImpl implements EmployeeDocumentsResource {

	Logger logger = Logger.getLogger(EmployeeDocumentsResourceImpl.class);

	@Autowired
	EmployeeDocumentsService employeeDocumentsService;
	
	@Autowired
	EmployeeDetailsService employeeDetailsService;
	
	@Override
	public ResponseEntity<Response> upload(MultipartFile file,EmployeeDocumentsRequestDTO documentsRequestDTO) {
		if(logger.isDebugEnabled()) logger.debug("In upload employee documents"
				+ "resource");
		
		if(logger.isDebugEnabled()) logger.debug("Before uploading documents, checking if employee exists in DB. Id-> "+documentsRequestDTO.getEmployeeId());
		EmployeeDetails employee = employeeDetailsService.getById(documentsRequestDTO.getEmployeeId());
		EmployeeDocumentsRequestDTOAdapter adapter = new EmployeeDocumentsRequestDTOAdapter();
		if(logger.isDebugEnabled()) logger.debug("Building documents object from DTO for employee Id-> "+documentsRequestDTO.getEmployeeId());
		EmployeeDocuments document = adapter.buildRequest(null,documentsRequestDTO,getUserName(),null);
		if(document != null) document.setEmployee(employee);
		
		if(logger.isDebugEnabled()) logger.debug("Calling service method for uploading document for employee Id> "+documentsRequestDTO.getEmployeeId());
		employeeDocumentsService.upload(file, document);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().message("Uploaded successfully").build());
	}
	
    public ResponseEntity<Resource> downloadFile(int resourceId) {
        // Load file from database
    	EmployeeDocuments document = employeeDocumentsService.downloadById(resourceId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getDocumentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + document.getName() + "\"")
                .body(new ByteArrayResource(document.getDocument()));
    }

	@Override
	public ResponseEntity<Resource> downloadAll(Integer employeeId) {

		  // Load file from database
    	List<EmployeeDocuments> document = employeeDocumentsService.downloadByEmployeeId(employeeId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.get(0).getDocumentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + document.get(0).getName() + "\"")
                .body(new ByteArrayResource(document.get(0).getDocument()));
	}
}
