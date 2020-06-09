package com.ps.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ps.entities.common.EmployeeDocuments;

public interface EmployeeDocumentsService {

	public void upload(MultipartFile file,EmployeeDocuments document );
	public EmployeeDocuments downloadById(int id);
	public List<EmployeeDocuments> downloadByEmployeeId(int employeeId);
	public void deleteById(int id);
	public void deleteByEmployeeId(int employeeId);
}
