package com.ps.RESTful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.BusinessYearDefinitionDTOMapper;
import com.ps.RESTful.dto.request.BusinessYearDefinitionRequestDTO;
import com.ps.RESTful.dto.response.BusinessYearDefinitionResponseDTO;
import com.ps.RESTful.enums.StatusEnum;
import com.ps.RESTful.enums.SuccessCode;
import com.ps.RESTful.resources.BusinessYearDefinitionResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.services.BusinessYearDefinitionService;

@RestController
@RequestMapping(path = { BusinessYearDefinitionResource.RESOURCE_PATH })
public class BusinessYearDefinitionResourceImpl extends AbstractResourceImpl implements BusinessYearDefinitionResource  {

	Logger logger = Logger.getLogger(BusinessYearDefinitionResourceImpl.class);

	@Autowired
	BusinessYearDefinitionDTOMapper businessYearDefinitionDTOMapper;
	
	@Autowired
	BusinessYearDefinitionService businessYearDefinitionService; 
	
	@Override
	public ResponseEntity<Response> add(BusinessYearDefinitionRequestDTO requestDTO) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessYearDefinition add EP");
		BusinessYearDefinition businessYearDefinition = businessYearDefinitionDTOMapper.dtoToEntity(requestDTO);
		
		if(logger.isDebugEnabled())
			logger.debug("Sending request to businessYearDefinition service add method to add data into DB"); 
		businessYearDefinitionService.add(businessYearDefinition);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
							"Business Year Definition added successfully.")
					.build());
	}

	@Override
	public ResponseEntity<Response> getAll() {
		
		if(logger.isDebugEnabled())
			logger.debug("In businessYearDefinition getAll EP");
		
		if(logger.isDebugEnabled())
			logger.debug("Sending request to businessYearDefinition service getAll method"); 
		List<BusinessYearDefinition> businessYearDefinitionList= businessYearDefinitionService.getAll();
		
		if(logger.isDebugEnabled())
			logger.debug("Sending request to businessYearDefinition dto mapper to map entity list to responseDTO list");
		List<BusinessYearDefinitionResponseDTO> responseDTOList = businessYearDefinitionDTOMapper.entityListToDtoList(businessYearDefinitionList);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
							"Business Year Definition records retrieved successfully")
					.results(responseDTOList)
					.build());
	}

	@Override
	public ResponseEntity<Response> delete(@PathVariable("resourceId") int resourceId){
		
		if(logger.isDebugEnabled())
			logger.debug("In businessYearDefinition delete EP for id-> "+resourceId);
		businessYearDefinitionService.softDeleteById(resourceId);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
							"Business Year Definition record deleted successfully")
					.build());
	}
}
