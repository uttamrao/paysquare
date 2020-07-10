package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.BusinessCycleDefinitionRequestDTO;
import com.ps.RESTful.dto.response.BusinessCycleDefinitionResponseDTO;
import com.ps.RESTful.enums.WeeksEnum;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.util.DateUtils;

@Component
public class BusinessCycleDefinitionDTOMapper implements 
					AbstractDTOMapper<BusinessCycleDefinitionRequestDTO, BusinessCycleDefinitionResponseDTO, BusinessCycleDefinition> {

	Logger logger = Logger.getLogger(BusinessCycleDefinitionDTOMapper.class);

	@Autowired
	BusinessYearDefinitionDTOMapper businessYearDefinitionDTOMapper;
	@Autowired
	FrequencyMasterDTOMapper frequencyMasterDTOMapper;
	
	@Override
	public BusinessCycleDefinition dtoToEntity(BusinessCycleDefinitionRequestDTO dto) {

		BusinessCycleDefinition businessCycleDefinition = null;
		
		if(dto != null) {
			businessCycleDefinition = new BusinessCycleDefinition();			
			businessCycleDefinition.setActive(dto.isActive());
			businessCycleDefinition.setCreatedBy(dto.getCreatedBy());
			businessCycleDefinition.setName(dto.getName());
			businessCycleDefinition.setServiceName(dto.getServiceName());
			
			if(!StringUtils.isBlank(dto.getWeekStartDefinition()) && WeeksEnum.isValid(dto.getWeekStartDefinition()))
				businessCycleDefinition.setWeekStartDefinition(WeeksEnum.valueOf(dto.getWeekStartDefinition()));
			
			businessCycleDefinition.setReoccuranceDays(dto.getReOccuranceDays());
			businessCycleDefinition.setForceToBusinessYearEnd(dto.isForceToBusinessYearEnd());
			
			if(logger.isDebugEnabled())
				logger.debug("After Maping dto to entity BusinessYearDefinition"
						+", name-> "+businessCycleDefinition.getName()
						+", serviceName-> "+businessCycleDefinition.getServiceName()
						+", createdBy-> "+businessCycleDefinition.getCreatedBy());
		}
		
		return businessCycleDefinition;
	}

	@Override
	public BusinessCycleDefinitionResponseDTO entityToDto(BusinessCycleDefinition businessCycleDefinition) {
		if(businessCycleDefinition == null)
			return null;
			
		BusinessCycleDefinitionResponseDTO responseDTO = new BusinessCycleDefinitionResponseDTO();

			if(logger.isDebugEnabled())
				logger.debug("Maping BusinessYearDefinition"
						+", name-> "+businessCycleDefinition.getName()
						+", createdBy-> "+businessCycleDefinition.getCreatedBy()
						+", id-> "+businessCycleDefinition.getId()
						+", createdDateTime-> "+businessCycleDefinition.getCreateDateTime());
			
			responseDTO.setActive(businessCycleDefinition.isActive());
			responseDTO.setReOccuranceDays(businessCycleDefinition.getReoccuranceDays());
			responseDTO.setForceToBusinessYearEnd(businessCycleDefinition.isForceToBusinessYearEnd());
			responseDTO.setCreatedBy(businessCycleDefinition.getCreatedBy());
			responseDTO.setName(businessCycleDefinition.getName());
			responseDTO.setServiceName(businessCycleDefinition.getServiceName());
			responseDTO.setBusinessYearDefinition(
					businessYearDefinitionDTOMapper.entityToDto(businessCycleDefinition.getBusinessYearDefinition()));
			responseDTO.setFrequency(frequencyMasterDTOMapper.entityToDto(businessCycleDefinition.getFrequencyMaster()));
			responseDTO.setId(businessCycleDefinition.getId());
			responseDTO.setCreateDateTime(DateUtils.getDateTimeString(businessCycleDefinition.getCreateDateTime()));		
		
		return responseDTO;
	}
	
	public List<BusinessCycleDefinitionResponseDTO> entityListToDtoList(List<BusinessCycleDefinition> businessCycleDefinitionList) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessYearDefinition dto mapper mapping entitylist to dtolist");
		List<BusinessCycleDefinitionResponseDTO> responseDTOList = new ArrayList<BusinessCycleDefinitionResponseDTO>();
		
		if(!CollectionUtils.isEmpty(businessCycleDefinitionList)) {
			for (BusinessCycleDefinition businessCycleDefinition : businessCycleDefinitionList) {
				BusinessCycleDefinitionResponseDTO responseDTO = entityToDto(businessCycleDefinition); 
				if(responseDTO != null)
					responseDTOList.add(responseDTO);
			}
		}	
		return responseDTOList;
	}

}
