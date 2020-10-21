package com.ps.RESTful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.ServiceCodeDTOMapper;
import com.ps.RESTful.dto.response.ServiceCodeResponseDTO;
import com.ps.RESTful.enums.StatusEnum;
import com.ps.RESTful.enums.SuccessCode;
import com.ps.RESTful.resources.ServiceCodeResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.entities.master.ServiceCode;
import com.ps.services.ServiceCodeService;

@RestController
@RequestMapping(path = { ServiceCodeResource.RESOURCE_PATH })
public class ServiceCodeResourceImpl extends AbstractResourceImpl implements ServiceCodeResource {

	Logger logger = Logger.getLogger(ServiceCodeResourceImpl.class);

	@Autowired
	ServiceCodeDTOMapper serviceCodeDTOMapper;

	@Autowired
	ServiceCodeService serviceCodeService;

	@Override
	public ResponseEntity<Response> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In service code getAll EP");

		if (logger.isDebugEnabled())
			logger.debug("Sending request to service code service getAll method");
		List<ServiceCode> serviceCodeList = serviceCodeService.getAll();

		if (logger.isDebugEnabled())
			logger.debug("Sending request to Mapp entity list to responseDTO list");
		List<ServiceCodeResponseDTO> responseDTOList = serviceCodeDTOMapper.entityListToDtoList(serviceCodeList);

		return ResponseEntity
				.status(HttpStatus.OK).body(
						ResponseBuilder.builder()
								.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(),
										"Service code records retrieved successfully")
								.results(responseDTOList).build());
	}

}
