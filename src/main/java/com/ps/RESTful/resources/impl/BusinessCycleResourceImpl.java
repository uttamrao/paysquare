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
import com.ps.RESTful.dto.request.BusinessCycleUpdateRequestDTO;
import com.ps.RESTful.dto.response.BusinessCycleResponseDTO;
import com.ps.RESTful.enums.ErrorCode;
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
				.get(requestDTO.getBusinessCycleDefinitionId());

		BusinessCycleBean businessCycleBean = businessCycleDTOMapper.dtoToBean(requestDTO);
		businessCycleBean.setBusinessCycleDefinition(businessCycleDefinition);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycleDefinition service add method to add data into DB");
		List<BusinessCycle> businessCycleList = businessCycleService.add(businessCycleBean);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessYearDefinition dto mapper to map entity to responseDTO");
		List<BusinessCycleResponseDTO> responseDTO = businessCycleDTOMapper.entityListToDtoList(businessCycleList);

		if (responseDTO.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE).body(
							ResponseBuilder
									.builder().status(StatusEnum.FAILURE.getValue(),
											ErrorCode.SERVICE_UNAVAILABLE.getCode(), "Business Cycle not added")
									.build());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
				.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Business Cycle added successfully.")
				.results(responseDTO).build());
	}

	@Override
	public ResponseEntity<Response> getAll(Integer cycleDefinitionId) {

		if (logger.isDebugEnabled())
			logger.debug("In businessCycle getAll EP");

		List<BusinessCycle> businessCycleList = new ArrayList<>();

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

		if (responseDTOList.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE).body(
							ResponseBuilder
									.builder().status(StatusEnum.FAILURE.getValue(),
											ErrorCode.SERVICE_UNAVAILABLE.getCode(), "Business Ccyle records not found")
									.build());
		}
		return ResponseEntity
				.status(HttpStatus.OK).body(
						ResponseBuilder.builder()
								.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
										"Business Cycle records retrieved successfully")
								.results(responseDTOList).build());
	}

	/** GET by ID **/
	@Override
	public ResponseEntity<Response> getAllByBusinessCycleIdAndBusinessYear(int cycleDefinitionId, String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In businessCycle getAllByBusinessCycleId EP");

		List<BusinessCycle> businessCycleList = new ArrayList<>();

		if (logger.isDebugEnabled())
			logger.debug(
					"Sending request to businessCycle service getAllByBusinessCycleIdAndBusinessYear method for cycleDefiniitonID-> "
							+ cycleDefinitionId);
		businessCycleList = businessCycleService.getAllByBusinessCycleIdAndBusinessYear(cycleDefinitionId,
				businessYear);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycle dto mapper to map entity list to responseDTO list");
		List<BusinessCycleResponseDTO> responseDTOList = businessCycleDTOMapper.entityListToDtoList(businessCycleList);

		if (responseDTOList.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE).body(
							ResponseBuilder
									.builder().status(StatusEnum.FAILURE.getValue(),
											ErrorCode.SERVICE_UNAVAILABLE.getCode(), "Business Ccyle records not found")
									.build());
		}
		return ResponseEntity
				.status(HttpStatus.OK).body(
						ResponseBuilder.builder()
								.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
										"Business Cycle records retrieved successfully")
								.results(responseDTOList).build());
	}

	/** Soft delete by Id **/

	@Override
	public ResponseEntity<Response> softDeleteByBusinessCycleIdAndBusinessYear(int cycleDefinitionId,
			String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In businessCycle softDeleteByBusinessCycleIdAndBusinessYear EP");

		if (logger.isDebugEnabled())
			logger.debug(
					"Sending request to businessCycle service softDeleteByBusinessCycleIdAndBusinessYear method for cycleDefiniitonID-> "
							+ cycleDefinitionId);
		businessCycleService.softDeleteByBusinessCycleIdAndBusinessYear(cycleDefinitionId, businessYear);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder()
				.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Business Cycle deleted successfully.")
				.build());
	}

	/** hard deleted for discard button **/

	@Override
	public ResponseEntity<Response> hardDeleteByBusinessCycleIdAndBusinessYear(int cycleDefinitionId,
			String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In businessCycle hardDeleteByBusinessCycleIdAndBusinessYear EP");

		if (logger.isDebugEnabled())
			logger.debug(
					"Sending request to businessCycle service hardDeleteByBusinessCycleIdAndBusinessYear method for cycleDefiniitonID-> "
							+ cycleDefinitionId);
		businessCycleService.hardDeleteByBusinessCycleIdAndBusinessYear(cycleDefinitionId, businessYear);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
				SuccessCode.OK.getCode(), "Business Cycle discarded successfully.").build());
	}

	/** update by Id **/

	@Override
	public ResponseEntity<Response> update(BusinessCycleUpdateRequestDTO requestDTO, int cycleDefinitionId,
			String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In businessCycle update EP");

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle update EP, finding businessCycleDifinition object in db with id-> "
					+ cycleDefinitionId);
		BusinessCycleDefinition businessCycleDefinition = businessCycleDefinitionService.get(cycleDefinitionId);

		List<BusinessCycle> businessCycleList = businessCycleDTOMapper.updateDtoToEntityList(requestDTO,
				businessCycleDefinition);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessCycleDefinition service update method to updating data into DB");

		businessCycleList = businessCycleService.update(businessCycleList, cycleDefinitionId, businessYear);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessYearDefinition dto mapper to map entity to responseDTO");
		List<BusinessCycleResponseDTO> responseDTO = businessCycleDTOMapper.entityListToDtoList(businessCycleList);

		if (businessCycleList.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE).body(
							ResponseBuilder
									.builder().status(StatusEnum.FAILURE.getValue(),
											ErrorCode.SERVICE_UNAVAILABLE.getCode(), "Business Cycle not updated")
									.build());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
				.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Business Cycle updated successfully.")
				.results(responseDTO).build());
	}

	/** force to year end by Id **/
	@Override
	public ResponseEntity<Response> forceEnd(BusinessCycleUpdateRequestDTO requestDTO, int cycleDefinitionId,
			String businessYear) {
		if (logger.isDebugEnabled())
			logger.debug("In businessCycle forceEnd EP");

		if (logger.isDebugEnabled())
			logger.debug("In BusinessCycle forceEnd EP, finding businessCycleDifinition object in db with id-> "
					+ cycleDefinitionId);
		BusinessCycleDefinition businessCycleDefinition = businessCycleDefinitionService.get(cycleDefinitionId);

		List<BusinessCycle> businessCycleList = businessCycleDTOMapper.updateDtoToEntityList(requestDTO,
				businessCycleDefinition);

		if (logger.isDebugEnabled())
			logger.debug(
					"Sending request to businessCycleDefinition service force to year end method to updating data into DB");

		businessCycleList = businessCycleService.updateForceToYearEnd(businessCycleList, cycleDefinitionId,
				businessYear);

		if (logger.isDebugEnabled())
			logger.debug("Sending request to businessYearDefinition dto mapper to map entity to responseDTO");
		List<BusinessCycleResponseDTO> responseDTO = businessCycleDTOMapper.entityListToDtoList(businessCycleList);

		if (businessCycleList.isEmpty()) {
			return ResponseEntity
					.status(HttpStatus.SERVICE_UNAVAILABLE).body(
							ResponseBuilder
									.builder().status(StatusEnum.FAILURE.getValue(),
											ErrorCode.SERVICE_UNAVAILABLE.getCode(), "Business Cycle not updated")
									.build());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
				.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Business Cycle updated successfully.")
				.results(responseDTO).build());
	}

}
