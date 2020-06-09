package com.ps.restful.dto.adapter.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ps.entities.global.ClientOrganizations;
import com.ps.restful.dto.response.ClientOrganizationsResponseDTO;

public class ClientOrganizationsResponseDTOAdapter {

	public ClientOrganizationsResponseDTO buildResponse(ClientOrganizations clientOrganizations) {
		
		if(clientOrganizations == null) return null;
		
		ClientOrganizationsResponseDTO clientOrganizationsResponseDTO = new ClientOrganizationsResponseDTO();
		clientOrganizationsResponseDTO.setName(clientOrganizations.getName());
		clientOrganizationsResponseDTO.setMappedDatabaseName(clientOrganizations.getMappedDatabaseName());
		clientOrganizationsResponseDTO.setHeadOfficeAddress(clientOrganizations.getHeadOfficeAddress());
		clientOrganizationsResponseDTO.setCountry(clientOrganizations.getCountry());
		clientOrganizationsResponseDTO.setId(clientOrganizations.getId());
		
		return clientOrganizationsResponseDTO;
	}
	
	public List<ClientOrganizationsResponseDTO> buildResponseList(List<ClientOrganizations> clientOrganizationsList) {		
		
		if(CollectionUtils.isEmpty(clientOrganizationsList)) new ArrayList<ClientOrganizationsResponseDTO>();
		
		List<ClientOrganizationsResponseDTO> clientOrganizationsResponseDTOList = new ArrayList<ClientOrganizationsResponseDTO>();

		for (ClientOrganizations clientOrganization : clientOrganizationsList) {
			ClientOrganizationsResponseDTO clientOrganizationsResponseDTO = buildResponse(clientOrganization);
			if(clientOrganizationsResponseDTO != null) clientOrganizationsResponseDTOList.add(clientOrganizationsResponseDTO);
		}
		
		return clientOrganizationsResponseDTOList;
	}
}
