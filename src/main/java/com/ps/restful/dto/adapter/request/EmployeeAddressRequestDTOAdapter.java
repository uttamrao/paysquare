package com.ps.restful.dto.adapter.request;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.util.CollectionUtils;

import com.ps.entities.common.EmployeeAddress;
import com.ps.entities.common.TypesEnum.AddressType;
import com.ps.restful.dto.request.EmployeeAddressRequestDTO;

public class EmployeeAddressRequestDTOAdapter {

	Logger logger = Logger.getLogger(EmployeeAddressRequestDTOAdapter.class);

	public EmployeeAddress buildRequest(EmployeeAddress address,EmployeeAddressRequestDTO addressDTO,
			String createdBy,String updatedBy) {
		
		if(addressDTO == null) return null;			
		if(address == null) address = new EmployeeAddress();	
		
		address.setAddress(addressDTO.getAddress());
		address.setCity(addressDTO.getCity());
		address.setState(addressDTO.getState());
		address.setPincode(addressDTO.getPincode());
		address.setCreatedBy(createdBy);
		address.setUpdatedBy(updatedBy);
		if((addressDTO.getType() != null) && (addressDTO.getType().getCode() != null)) {
			address.setType(AddressType.valueOf(addressDTO.getType().getCode()));
		}
		return address;
	}
	
	public List<EmployeeAddress> buildRequestList(List<EmployeeAddressRequestDTO> addressDTOList,
			String createdBy,String updatedBy) {		
		
		if(logger.isDebugEnabled()) logger.debug("In EmployeeAddressRequestDTOAdapter buildRequestList "+addressDTOList);
		
		if(CollectionUtils.isEmpty(addressDTOList)) return null;
		
		
		List<EmployeeAddress> addressList = new ArrayList<EmployeeAddress>();
		if(logger.isDebugEnabled()) logger.debug("In Address buildRequestList iterating over addressDTOList and building address object from DTO  "+addressDTOList.size());

		for (EmployeeAddressRequestDTO addressDTO : addressDTOList) {
			EmployeeAddress address= buildRequest(null,addressDTO,createdBy,updatedBy);
			if(address != null) addressList.add(address);
		}
		
		return addressList;
	}
}
