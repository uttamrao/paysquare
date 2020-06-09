package com.ps.restful.dto.adapter.request;

import org.jboss.logging.Logger;

import com.ps.entities.common.EmployeeDetails;
import com.ps.entities.common.Statuses.EmployeeJoiningStage;
import com.ps.entities.common.Statuses.EmployeeJoiningStatuses;
import com.ps.restful.dto.request.EmployeeDetailsRequestDTO;

public class EmployeeDetailsRequestDTOAdapter {

	static Logger logger = Logger.getLogger(EmployeeDetailsRequestDTOAdapter.class);
	
	public EmployeeDetails buildRequest(EmployeeDetails employeedetails,
			EmployeeDetailsRequestDTO employeedetailsDTO,String createdBy,String updatedBy) {
		
		if(employeedetailsDTO == null) return null;
		if(employeedetails == null) employeedetails = new EmployeeDetails();
		
		employeedetails.setFirstname(employeedetailsDTO.getFirstname());
		employeedetails.setMiddlename(employeedetailsDTO.getMiddlename());
		employeedetails.setLastname(employeedetailsDTO.getLastname());
		employeedetails.setEmail(employeedetailsDTO.getEmail());
		employeedetails.setJobTitle(employeedetailsDTO.getJobTitle());
		employeedetails.setDepartment(employeedetailsDTO.getDepartment());
		employeedetails.setReportingManager(employeedetailsDTO.getReportingManager());
		employeedetails.setJoiningDate(employeedetailsDTO.getJoiningDate());
		if((employeedetailsDTO.getJoiningStage() != null) && (employeedetailsDTO.getJoiningStage().getCode() != null)) {
			employeedetails.setJoiningStage(EmployeeJoiningStage.valueOf(employeedetailsDTO.getJoiningStage().getCode()));
		}	
		if((employeedetailsDTO.getJoiningStatus() != null) && (employeedetailsDTO.getJoiningStatus().getCode() != null)) {
			employeedetails.setJoiningStatus(EmployeeJoiningStatuses.valueOf(employeedetailsDTO.getJoiningStatus().getCode()));
			employeedetails.setJoiningStage(EmployeeJoiningStage.COMPLETE);
		}else {
			employeedetails.setJoiningStatus(EmployeeJoiningStatuses.PENDING);
		}		
		employeedetails.setPanNumber(employeedetailsDTO.getPanNumber());
		logger.debug("In EmployeeDetailsRequestDTOAdapter UAN-> "+employeedetailsDTO.getUanNumber());
		logger.debug("In EmployeeDetailsRequestDTOAdapter Entity UAN-> "+employeedetails.getUAN());

		employeedetails.setUAN(employeedetailsDTO.getUanNumber());
		employeedetails.setCompanyId(employeedetailsDTO.getCompanyId());
		employeedetails.setCreatedBy(createdBy);
		employeedetails.setUpdatedBy(updatedBy);
		
		return employeedetails;
	}

}
