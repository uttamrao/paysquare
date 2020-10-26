package com.ps.RESTful.resources.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.BusinessCycleDTOMapper;
import com.ps.RESTful.dto.request.BusinessCycleRequestDTO;
import com.ps.RESTful.dto.response.BusinessCycleResponseDTO;
import com.ps.RESTful.enums.StatusEnum;
import com.ps.RESTful.enums.SuccessCode;
import com.ps.RESTful.resources.BusinessCycleResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.services.BusinessCycleDefinitionService;
import com.ps.services.BusinessCycleService;

@RestController
@RequestMapping(path = { BusinessCycleResource.RESOURCE_PATH })
public class BusinessCycleResourceImpl extends AbstractResourceImpl implements BusinessCycleResource {

	Logger logger = Logger.getLogger(BusinessCycleResourceImpl.class);

	@Autowired
	BusinessCycleDTOMapper businessCycleDTOMapper;

	@Autowired
	BusinessCycleService businessCycleService;

	@Autowired
	BusinessCycleDefinitionService businessCycleDefinitionService;

	@Override
	public ResponseEntity<Response> add(BusinessCycleRequestDTO requestDTO) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle add EP");

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle add EP, finding businessCycleDifinition object in db with id-> "
					+ requestDTO.getBusinessCycleDefinitionId());
		BusinessCycleDefinition businessCycleDefinition = businessCycleDefinitionService
				.getById(requestDTO.getBusinessCycleDefinitionId());

		BusinessCycleBean businessCycleBean = businessCycleDTOMapper.dtoToBean(requestDTO);
		businessCycleBean.setBusinessCycleDefinition(businessCycleDefinition);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycleDefinition service add method to add data into DB");
		businessCycleService.add(businessCycleBean);

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
				.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Business Cycle added successfully.")
				.build());
	}

	@Override
	public ResponseEntity<Response> getAll(Integer cycleDefinitionId) {

		if (logger.isDebugEnabled())
			logger.debug("In businessCycle getAll EP");

		List<BusinessCycle> businessCycleList = new ArrayList<BusinessCycle>();

		if (cycleDefinitionId != null) {
			if (logger.isDebugEnabled())
				logger.debug("Sending request to businessCycle service getByCycleID method for cycleDefiniitonID-> "
						+ cycleDefinitionId);
			businessCycleList = businessCycleService.getAllByCycleDefinition(cycleDefinitionId);
		} else {
			if (logger.isDebugEnabled())
				logger.debug("Sending request to businessCycle service getAll method");
			businessCycleList = businessCycleService.getAll();
		}

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycle dto mapper to map entity list to responseDTO list");
		List<BusinessCycleResponseDTO> responseDTOList = businessCycleDTOMapper.entityListToDtoList(businessCycleList);

		return ResponseEntity
				.status(HttpStatus.OK).body(
						ResponseBuilder.builder()
								.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
										"Business Cycle records retrieved successfully")
								.results(responseDTOList).build());
	}

	@Override
	public ResponseEntity<Response> getAllByBusinessCycleIdAndBusinessYear(int cycleDefinitionId, int businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In businessCycle getAllByBusinessCycleId EP");

		List<BusinessCycle> businessCycleList = new ArrayList<BusinessCycle>();

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycle service getByCycleID method for cycleDefiniitonID-> "
					+ cycleDefinitionId);
		businessCycleList = businessCycleService.getAllByCycleDefinition(cycleDefinitionId);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycle dto mapper to map entity list to responseDTO list");
		List<BusinessCycleResponseDTO> responseDTOList = businessCycleDTOMapper.entityListToDtoList(businessCycleList);

		return ResponseEntity
				.status(HttpStatus.OK).body(
						ResponseBuilder.builder()
								.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
										"Business Cycle records retrieved successfully")
								.results(responseDTOList).build());
	}

}
