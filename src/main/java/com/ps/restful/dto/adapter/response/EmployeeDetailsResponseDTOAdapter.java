package com.ps.restful.dto.adapter.response;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.util.CollectionUtils;

import com.ps.dto.EnumDTO;
import com.ps.entities.common.EmployeeDetails;
import com.ps.restful.dto.response.EmployeeDetailsResponseDTO;

public class EmployeeDetailsResponseDTOAdapter {

	public EmployeeDetailsResponseDTO buildResponse(EmployeeDetails employeedetails) {
		
		Logger logger = Logger.getLogger(EmployeeDetailsResponseDTO.class);

		if(employeedetails == null) return null;
		
		EmployeeDetailsResponseDTO employeedetailsDTO = new EmployeeDetailsResponseDTO();
		employeedetailsDTO.setId(employeedetails.getId());
		employeedetailsDTO.setFirstname(employeedetails.getFirstname());
		employeedetailsDTO.setMiddlename(employeedetails.getMiddlename());
		employeedetailsDTO.setLastname(employeedetails.getLastname());
		employeedetailsDTO.setEmail(employeedetails.getEmail());
		employeedetailsDTO.setJobTitle(employeedetails.getJobTitle());
		employeedetailsDTO.setDepartment(employeedetails.getDepartment());
		employeedetailsDTO.setJoiningDate(employeedetails.getJoiningDate());		
		if((employeedetails.getJoiningStage() != null)) {
			employeedetailsDTO.setJoiningStage(
					new EnumDTO(employeedetails.getJoiningStage().getValue(),employeedetails.getJoiningStage().name()) );
		}		
		if((employeedetails.getJoiningStatus() != null)) {
			employeedetailsDTO.setJoiningStatus(
					new EnumDTO(employeedetails.getJoiningStatus().getValue(),employeedetails.getJoiningStatus().name()) );
		}		
		if(logger.isDebugEnabled()) logger.debug("employeedetails.getPanNumber() "+employeedetails.getPanNumber());
		if(logger.isDebugEnabled()) logger.debug("employeedetails.getUAN() "+employeedetails.getUAN());
		
		employeedetailsDTO.setReportingManager(employeedetails.getReportingManager());
		employeedetailsDTO.setCompanyId(employeedetails.getCompanyId());		
		employeedetailsDTO.setPanNumber(employeedetails.getPanNumber());
		employeedetailsDTO.setUanNumber(employeedetails.getUAN());
		employeedetailsDTO.setCreatedBy(employeedetails.getCreatedBy());
		employeedetailsDTO.setUpdatedBy(employeedetails.getUpdatedBy());
		
		return employeedetailsDTO;
	}
	
	public List<EmployeeDetailsResponseDTO> buildResponseList(List<EmployeeDetails> employeedetailsList) {		
		
		if(CollectionUtils.isEmpty(employeedetailsList)) return new ArrayList<EmployeeDetailsResponseDTO>();
		
		List<EmployeeDetailsResponseDTO> employeedetailsDTOList = new ArrayList<EmployeeDetailsResponseDTO>();

		for (EmployeeDetails employeedetail : employeedetailsList) {
			EmployeeDetailsResponseDTO employeedetailDTO = buildResponse(employeedetail);
			if(employeedetailDTO != null) employeedetailsDTOList.add(employeedetailDTO);
		}
		
		return employeedetailsDTOList;
	}
}
