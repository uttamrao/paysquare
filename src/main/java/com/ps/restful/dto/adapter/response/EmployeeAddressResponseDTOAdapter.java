package com.ps.restful.dto.adapter.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ps.dto.EnumDTO;
import com.ps.entities.common.EmployeeAddress;
import com.ps.restful.dto.response.EmployeeAddressResponseDTO;

public class EmployeeAddressResponseDTOAdapter {

	public EmployeeAddressResponseDTO buildResponse(EmployeeAddress employeeAddress) {
		
		EmployeeAddressResponseDTO employeeAddressResponseDTO = new EmployeeAddressResponseDTO();
		
		employeeAddressResponseDTO.setAddress(employeeAddress.getAddress());
		employeeAddressResponseDTO.setCity(employeeAddress.getCity());
		employeeAddressResponseDTO.setState(employeeAddress.getState());
		employeeAddressResponseDTO.setPincode(employeeAddress.getPincode());
		employeeAddressResponseDTO.setCreatedBy(employeeAddress.getCreatedBy());
		employeeAddressResponseDTO.setUpdatedBy(employeeAddress.getUpdatedBy());
		if((employeeAddress.getType() != null) && (employeeAddress.getType().getValue() != null)) {  
			employeeAddressResponseDTO.setType(new EnumDTO(employeeAddress.getType().getValue(),
					employeeAddress.getType().name()));
			}
		employeeAddressResponseDTO.setCreatedBy(employeeAddress.getCreatedBy());
		employeeAddressResponseDTO.setUpdatedBy(employeeAddress.getUpdatedBy());
		
		return employeeAddressResponseDTO;
	  }	
	
	public List<EmployeeAddressResponseDTO> buildResponseList(List<EmployeeAddress> addressList) {		
		
		List<EmployeeAddressResponseDTO> addressListDTOList = new ArrayList<EmployeeAddressResponseDTO>();

		if(CollectionUtils.isEmpty(addressList)) return addressListDTOList;
		

		for (EmployeeAddress address : addressList) {
			EmployeeAddressResponseDTO addressDTO= buildResponse(address);
			if(address != null) addressListDTOList.add(addressDTO);
		}
		
		return addressListDTOList;
	}
 }
