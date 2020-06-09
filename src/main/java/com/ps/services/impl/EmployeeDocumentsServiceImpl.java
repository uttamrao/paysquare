package com.ps.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ps.entities.common.EmployeeDocuments;
import com.ps.restful.error.handler.ErrorCode;
import com.ps.restful.error.handler.InvalidRequestException;
import com.ps.restful.error.handler.ResourceNotFoundException;
import com.ps.services.EmployeeDocumentsService;
import com.ps.services.repository.common.EmployeeDocumentsRepository;

@Service
public class EmployeeDocumentsServiceImpl implements EmployeeDocumentsService {

	Logger logger = Logger.getLogger(EmployeeDocumentsServiceImpl.class);

	@Autowired
	EmployeeDocumentsRepository employeeDocumentsRepository;
	
	@Override
	public void upload(MultipartFile file,EmployeeDocuments document ) {
		
		if(logger.isDebugEnabled()) logger.debug("In upload employee documents"
				+ "service method");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        document.setName(fileName);
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new InvalidRequestException(ErrorCode.BAD_REQUEST,"Sorry! Filename contains invalid path sequence " + fileName);
            }

            document.setDocumentType(file.getContentType());
            document.setDocument(file.getBytes());

            if(logger.isDebugEnabled()) logger.debug("Saving document for employee Id-> "+document.getEmployee().getId()+"into DB");
            employeeDocumentsRepository.save(document);
            
        } catch (IOException ex) {        	
        	if(logger.isDebugEnabled()) logger.debug("IO exception while uploading file ",ex);
            throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,"Could not store file " + fileName + ". Please try again!");
        }
		
	}
	
	@Override
	public EmployeeDocuments downloadById(int id) {
		
		if(logger.isDebugEnabled()) logger.debug("In download employee documents"
				+ "service method for id "+id);
		Optional<EmployeeDocuments> employeeDocumentOptional = employeeDocumentsRepository.findById(id);
		
		if(!employeeDocumentOptional.isPresent()) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
				"File not found");
		
		return employeeDocumentOptional.get();
	}

	@Override
	public List<EmployeeDocuments> downloadByEmployeeId(int employeeId) {
		
		if(logger.isDebugEnabled()) logger.debug("In download employee documents"
				+ "service method for employeeId "+employeeId);
		List<EmployeeDocuments> employeeDocuments = employeeDocumentsRepository.findByEmployeeId(employeeId);
		
		if(CollectionUtils.isEmpty(employeeDocuments)) throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
				"File not found");
		
		return employeeDocuments;
	}

	
	@Override
	public void deleteById(int id) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee documents "
				+ "service method deleting record for Id "+id);
		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Document Id! ");
		employeeDocumentsRepository.deleteById(id);
	}

	@Override
	public void deleteByEmployeeId(int employeeId) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById employee documents "
				+ "service method deleting record for employee Id "+employeeId);
		
		if(employeeId == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Employee Id! ");
		employeeDocumentsRepository.deleteByEmployeeId(employeeId);
	}

}
