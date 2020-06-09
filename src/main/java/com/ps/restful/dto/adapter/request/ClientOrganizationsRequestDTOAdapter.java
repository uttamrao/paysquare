package com.ps.restful.dto.adapter.request;

import com.ps.dto.ClientOrganizationsDTO;
import com.ps.entities.global.ClientOrganizations;

public class ClientOrganizationsRequestDTOAdapter {

	public ClientOrganizations buildRequest(ClientOrganizations clientOrganizations,ClientOrganizationsDTO clientOrganizationsDTO) {
		
		if(clientOrganizationsDTO == null) return null;
		if(clientOrganizations == null) clientOrganizations = new ClientOrganizations();
		
		clientOrganizations.setName(clientOrganizationsDTO.getName());
		clientOrganizations.setMappedDatabaseName(clientOrganizationsDTO.getMappedDatabaseName());
		clientOrganizations.setHeadOfficeAddress(clientOrganizationsDTO.getHeadOfficeAddress());
		clientOrganizations.setCountry(clientOrganizationsDTO.getCountry());
		
		return clientOrganizations;
	}
	
}
