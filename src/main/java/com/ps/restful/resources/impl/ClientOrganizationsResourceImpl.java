package com.ps.restful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.entities.global.ClientOrganizations;
import com.ps.restful.dto.adapter.request.ClientOrganizationsRequestDTOAdapter;
import com.ps.restful.dto.adapter.response.ClientOrganizationsResponseDTOAdapter;
import com.ps.restful.dto.request.ClientOrganizationsRequestDTO;
import com.ps.restful.dto.response.ClientOrganizationsResponseDTO;
import com.ps.restful.resources.ClientOrganizationsResource;
import com.ps.restful.resources.response.handler.Response;
import com.ps.restful.resources.response.handler.ResponseBuilder;
import com.ps.services.ClientOrganizationsService;



@RestController
@RequestMapping(path = ClientOrganizationsResource.RESOURCE_PATH)
public class ClientOrganizationsResourceImpl extends AbstractResourceImpl implements ClientOrganizationsResource {

	Logger logger = Logger.getLogger(ClientOrganizationsResourceImpl.class);
	
	@Autowired
	ClientOrganizationsService clientOrganizationsService;
	
	@Override
	public ResponseEntity<Response> add(ClientOrganizationsRequestDTO clientOrganizationsDTO) {

		if(logger.isDebugEnabled()) logger.debug("In add ClientOrganizations"
				+ "resource");
		
		ClientOrganizationsRequestDTOAdapter clientOrganizationsRequestDTOAdapter = new ClientOrganizationsRequestDTOAdapter();
		if(logger.isDebugEnabled()) logger.debug("Building client organization for client "+clientOrganizationsDTO.getName());
		ClientOrganizations clientOrganizations = clientOrganizationsRequestDTOAdapter.buildRequest(null,clientOrganizationsDTO);
		if(logger.isDebugEnabled()) logger.debug("Sending clientOrganization entity to service method for saving into db "+clientOrganizations.getName());
		clientOrganizationsService.add(clientOrganizations);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().message("Created successfully").build());
	}

	@Override
	public ResponseEntity<Response> getAll() {
		
		if(logger.isDebugEnabled()) logger.debug("In getAll employee details "
				+ "resource");				
		List<ClientOrganizations> clientOrganizationsList = clientOrganizationsService.getAllClientOrganizations();
		ClientOrganizationsResponseDTOAdapter clientOrganizationsRequestDTOAdapter = new ClientOrganizationsResponseDTOAdapter();
		List<ClientOrganizationsResponseDTO> clientOrganizationsResponseDTO = clientOrganizationsRequestDTOAdapter.buildResponseList(clientOrganizationsList);
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().results(clientOrganizationsResponseDTO).build());
	}
}
