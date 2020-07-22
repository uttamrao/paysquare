package com.ps.RESTful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.FrequencyMasterDTOMapper;
import com.ps.RESTful.dto.request.FrequencyMasterRequestDTO;
import com.ps.RESTful.dto.response.FrequencyMasterResponseDTO;
import com.ps.RESTful.enums.StatusEnum;
import com.ps.RESTful.enums.SuccessCode;
import com.ps.RESTful.resources.FrequencyMasterResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.services.FrequencyMasterService;

@RestController
@RequestMapping(path = { FrequencyMasterResource.RESOURCE_PATH })
public class FrequencyMasterResourceImpl extends AbstractResourceImpl implements FrequencyMasterResource  {

	Logger logger = Logger.getLogger(FrequencyMasterResourceImpl.class);

	@Autowired
	FrequencyMasterDTOMapper frequencyMasterDTOMapper;
	
	@Autowired
	FrequencyMasterService frequencyMasterService; 
	
	@Override
	public ResponseEntity<Response> add(FrequencyMasterRequestDTO requestDTO) {
		
		if(logger.isDebugEnabled())
			logger.debug("In frequency master add EP");
		FrequencyMaster frequencyMaster = frequencyMasterDTOMapper.dtoToEntity(requestDTO);
		
		if(logger.isDebugEnabled())
			logger.debug("Sending request to frequency master service add method to add data into DB"); 
		frequencyMasterService.add(frequencyMaster);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
							"Frequency added successfully.")
					.build());
	}

	@Override
	public ResponseEntity<Response> getAll() {
		
		if(logger.isDebugEnabled())
			logger.debug("In frequency master getAll EP");
		
		if(logger.isDebugEnabled())
			logger.debug("Sending request to frequency master service getAll method"); 
		List<FrequencyMaster> frequencyMasterList= frequencyMasterService.getAll();
		
		if(logger.isDebugEnabled())
			logger.debug("Sending request to Mapp entity list to responseDTO list");
		List<FrequencyMasterResponseDTO> responseDTOList = frequencyMasterDTOMapper.entityListToDtoList(frequencyMasterList);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
							"Frequency records retrieved successfully")
					.results(responseDTOList)
					.build());
	}

}
