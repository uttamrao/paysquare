package com.ps.restful.dto.adapter.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ps.dto.EnumDTO;
import com.ps.entities.common.EmployeeDetails;
import com.ps.entities.common.EmployeeFamilyDetails;
import com.ps.restful.dto.response.EmployeeDetailsResponseDTO;
import com.ps.restful.dto.response.EmployeeFamilyDetailsResponseDTO;

public class EmployeeFamilyDetailsResponseDTOAdapter {

	public EmployeeFamilyDetailsResponseDTO buildResponse(EmployeeFamilyDetails employeeFamilyDetails) {
		
		if(employeeFamilyDetails == null) return null;
		
		EmployeeFamilyDetailsResponseDTO employeeFamilyDetailsResponseDTO = new EmployeeFamilyDetailsResponseDTO();
		employeeFamilyDetailsResponseDTO.setFirstname(employeeFamilyDetails.getFirstname());
		employeeFamilyDetailsResponseDTO.setMiddlename(employeeFamilyDetails.getMiddlename());
		employeeFamilyDetailsResponseDTO.setLastname(employeeFamilyDetails.getLastname());
		if((employeeFamilyDetails.getRelationship() != null) && (employeeFamilyDetails.getRelationship().getValue() != null)) {
			employeeFamilyDetailsResponseDTO.setRelationship(new EnumDTO(employeeFamilyDetails.getRelationship().getValue(),
					employeeFamilyDetails.getRelationship().name()));		
			}	
		employeeFamilyDetailsResponseDTO.setEmployeeDetails(new EmployeeDetailsResponseDTOAdapter().buildResponse(employeeFamilyDetails.getEmployee()));
		employeeFamilyDetailsResponseDTO.setCreatedBy(employeeFamilyDetails.getCreatedBy());
		employeeFamilyDetailsResponseDTO.setUpdatedBy(employeeFamilyDetails.getUpdatedBy());
		
		return employeeFamilyDetailsResponseDTO;
	}
	
	public List<EmployeeFamilyDetailsResponseDTO> buildResponseList(List<EmployeeFamilyDetails> employeeFamilyDetailsList) {		
		
		if(CollectionUtils.isEmpty(employeeFamilyDetailsList)) return new ArrayList<EmployeeFamilyDetailsResponseDTO>();
		
		List<EmployeeFamilyDetailsResponseDTO> employeeFamilyDetailsResponseDTOList = new ArrayList<EmployeeFamilyDetailsResponseDTO>();

		for (EmployeeFamilyDetails employeeFamilyDetails : employeeFamilyDetailsList) {
			EmployeeFamilyDetailsResponseDTO employeedetailDTO = buildResponse(employeeFamilyDetails);
			if(employeedetailDTO != null) employeeFamilyDetailsResponseDTOList.add(employeedetailDTO);
		}
		
		return employeeFamilyDetailsResponseDTOList;
	}
}
