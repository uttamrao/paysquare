package com.ps.RESTful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.BusinessCycleDefinitionDTOMapper;
import com.ps.RESTful.dto.request.BusinessCycleDefinitionRequestDTO;
import com.ps.RESTful.dto.response.BusinessCycleDefinitionResponseDTO;
import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.enums.StatusEnum;
import com.ps.RESTful.enums.SuccessCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.resources.BusinessCycleDefinitionResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.services.BusinessCycleDefinitionService;
import com.ps.services.BusinessYearDefinitionService;
import com.ps.services.FrequencyMasterService;

@RestController
@RequestMapping(path = { BusinessCycleDefinitionResource.RESOURCE_PATH })
public class BusinessCycleDefinitionResourceImpl extends AbstractResourceImpl
		implements BusinessCycleDefinitionResource {

	Logger logger = Logger.getLogger(BusinessCycleDefinitionResourceImpl.class);

	@Autowired
	BusinessCycleDefinitionDTOMapper businessCycleDefinitionDTOMapper;

	@Autowired
	BusinessCycleDefinitionService businessCycleDefinitionService;

	@Autowired
	BusinessYearDefinitionService businessYearDefinitionService;

	@Autowired
	FrequencyMasterService frequencyMasterService;

	@Override
	public ResponseEntity<Response> add(BusinessCycleDefinitionRequestDTO requestDTO) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition add EP");
		BusinessCycleDefinition businessCycleDefinition = businessCycleDefinitionDTOMapper.dtoToEntity(requestDTO);

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition add EP, finding businessYearDifinition object in db with id-> "
					+ requestDTO.getBusinessYearDefinitionId());
		BusinessYearDefinition businessYearDefinition = businessYearDefinitionService
				.getById(requestDTO.getBusinessYearDefinitionId());

		if (businessYearDefinition == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Year Definition not found!");

		businessCycleDefinition.setBusinessYearDefinition(businessYearDefinition);

		if (requestDTO.getFrequencyMasterId() != 0) {
			if (logger.isDebugEnabled())
				logger.debug("In BusinessCycleDefinition add EP, finding frequencyMaster object in db with id-> "
						+ requestDTO.getFrequencyMasterId());
			FrequencyMaster frequencyMaster = frequencyMasterService.getById(requestDTO.getFrequencyMasterId());

			if (frequencyMaster == null)
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Frequency master not found!");

			businessCycleDefinition.setFrequencyMaster(frequencyMaster);
		}

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycleDefinition service add method to add data into DB");
		businessCycleDefinitionService.add(businessCycleDefinition);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
						"Business Cycle Definition added successfully.").build());
	}

	@Override
	public ResponseEntity<Response> update(int resourceId, BusinessCycleDefinitionRequestDTO requestDTO) {

		if (logger.isDebugEnabled())
			logger.debug(
					"In BusinessCycleDefinition update EP, calling service method to find cycleDefinition with id-> "
							+ resourceId);
		BusinessCycleDefinition dbBusinessCycleDefinition = businessCycleDefinitionService.getById(resourceId);

		if (logger.isDebugEnabled())
			logger.debug("Mapping request dto to entity");
		BusinessCycleDefinition businessCycleDefinition = businessCycleDefinitionDTOMapper.dtoToEntity(requestDTO);

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycleDefinition update EP, finding businessYearDifinition object in db with id-> "
					+ requestDTO.getBusinessYearDefinitionId());
		BusinessYearDefinition businessYearDefinition = businessYearDefinitionService
				.getById(requestDTO.getBusinessYearDefinitionId());
		businessCycleDefinition.setBusinessYearDefinition(businessYearDefinition);

		if (requestDTO.getFrequencyMasterId() != 0) {
			if (logger.isDebugEnabled())
				logger.debug("In BusinessCycleDefinition add EP, finding frequencyMaster object in db with id-> "
						+ requestDTO.getFrequencyMasterId());
			FrequencyMaster frequencyMaster = frequencyMasterService.getById(requestDTO.getFrequencyMasterId());
			businessCycleDefinition.setFrequencyMaster(frequencyMaster);
		}

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycleDefinition service add method to add data into DB");
		businessCycleDefinitionService.update(dbBusinessCycleDefinition, businessCycleDefinition);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
						"Business Cycle Definition updated successfully.").build());
	}

	@Override
	public ResponseEntity<Response> getAll() {

		if (logger.isDebugEnabled())
			logger.debug("In businessCycleDefinition getAll EP");

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycleDefinition service getAll method");
		List<BusinessCycleDefinition> businessCycleDefinitionList = businessCycleDefinitionService.getAll();

		if (logger.isDebugEnabled())
			logger.debug(
					"Sending request to businessCycleDefinition dto mapper to map entity list to responseDTO list");
		List<BusinessCycleDefinitionResponseDTO> responseDTOList = businessCycleDefinitionDTOMapper
				.entityListToDtoList(businessCycleDefinitionList);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseBuilder.builder()
						.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
								"Business Cycle Definition records retrieved successfully")
						.results(responseDTOList).build());
	}

	@Override
	public ResponseEntity<Response> get(@PathVariable("resourceId") int resourceId) {

		if (logger.isDebugEnabled())
			logger.debug("In businessCycleDefinition get EP");

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycleDefinition service get method");
		BusinessCycleDefinition businessCycleDefinition = businessCycleDefinitionService.getById(resourceId);

		if (logger.isDebugEnabled())
			logger.debug(
					"Sending request to businessCycleDefinition dto mapper to map entity list to responseDTO list");
		BusinessCycleDefinitionResponseDTO responseDTO = businessCycleDefinitionDTOMapper
				.entityToDto(businessCycleDefinition);

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseBuilder.builder()
						.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
								"Business Cycle Definition records retrieved successfully")
						.result(responseDTO).build());
	}

	@Override
	public ResponseEntity<Response> delete(@PathVariable("resourceId") int resourceId) {

		if (logger.isDebugEnabled())
			logger.debug("In businessCycleDefinition delete EP for id-> " + resourceId);
		businessCycleDefinitionService.softDeleteById(resourceId);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
				SuccessCode.OK.getCode(), "Business Cycle Definition record deleted successfully").build());
	}

}
