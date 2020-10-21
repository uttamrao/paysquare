package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.ServiceCodeRequestDTO;
import com.ps.RESTful.dto.response.ServiceCodeResponseDTO;
import com.ps.entities.master.ServiceCode;

@Component
public class ServiceCodeDTOMapper
		implements AbstractDTOMapper<ServiceCodeRequestDTO, ServiceCodeResponseDTO, ServiceCode> {

	Logger logger = Logger.getLogger(ServiceCodeDTOMapper.class);

	@Override
	public ServiceCode dtoToEntity(ServiceCodeRequestDTO serviceCodeRequestDTO) {
		if (serviceCodeRequestDTO == null)
			return null;

		ServiceCode serviceCode = new ServiceCode();

		serviceCode.setServiceCodeId(serviceCodeRequestDTO.getServiceCodeId());
		serviceCode.setServiceName(serviceCodeRequestDTO.getServiceName());
		serviceCode.setServiceCode(serviceCodeRequestDTO.getServiceCode());
		serviceCode.setCreatedBy(serviceCodeRequestDTO.getCreatedBy());
		serviceCode.setIsActive(serviceCodeRequestDTO.isActive());
		return serviceCode;
	}

	@Override
	public ServiceCodeResponseDTO entityToDto(ServiceCode serviceCode) {
		if (serviceCode == null)
			return null;

		ServiceCodeResponseDTO serviceCodeResponseDTO = new ServiceCodeResponseDTO();

		serviceCodeResponseDTO.setServiceCodeId(serviceCode.getServiceCodeId());
		serviceCodeResponseDTO.setServiceName(serviceCode.getServiceName());
		serviceCodeResponseDTO.setServiceCode(serviceCode.getServiceCode());
		serviceCodeResponseDTO.setCreatedBy(serviceCode.getCreatedBy());
		serviceCodeResponseDTO.setActive(serviceCode.getIsActive());
		return serviceCodeResponseDTO;
	}

	public List<ServiceCodeResponseDTO> entityListToDtoList(List<ServiceCode> serviceCodeList) {

		if (logger.isDebugEnabled())
			logger.debug("In ServiceCode dto mapper mapping entitylist to dtolist");
		List<ServiceCodeResponseDTO> responseDTOList = new ArrayList<ServiceCodeResponseDTO>();

		if (!CollectionUtils.isEmpty(serviceCodeList)) {
			for (ServiceCode serviceCode : serviceCodeList) {
				ServiceCodeResponseDTO responseDTO = entityToDto(serviceCode);
				if (responseDTO != null)
					responseDTOList.add(responseDTO);
			}
		}
		return responseDTOList;
	}
}
