package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.BusinessYearDefinitionRequestDTO;
import com.ps.RESTful.dto.response.BusinessYearDefinitionResponseDTO;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.util.DateUtils;

@Component
public class BusinessYearDefinitionDTOMapper implements 
					AbstractDTOMapper<BusinessYearDefinitionRequestDTO, BusinessYearDefinitionResponseDTO, BusinessYearDefinition> {

	Logger logger = Logger.getLogger(BusinessYearDefinitionDTOMapper.class);

	@Override
	public BusinessYearDefinition dtoToEntity(BusinessYearDefinitionRequestDTO dto) {
		
		BusinessYearDefinition businessYearDefinition = null;
		
		if(dto != null) {
			businessYearDefinition = new BusinessYearDefinition();			
			businessYearDefinition.setActive(dto.isActive());
			businessYearDefinition.setCreatedBy(dto.getCreatedBy());
			businessYearDefinition.setFromDate(DateUtils.getDateMonth(dto.getFromDate()));
			businessYearDefinition.setToDate(DateUtils.getDateMonth(dto.getToDate()));
			businessYearDefinition.setDescription(dto.getDescription());
			
			if(logger.isDebugEnabled())
				logger.debug("After Maping dto to entity BusinessYearDefinition fromDate-> "+businessYearDefinition.getFromDate()
						+", toDate-> "+businessYearDefinition.getToDate()
						+", description-> "+businessYearDefinition.getDescription()
						+", createdBy-> "+businessYearDefinition.getCreatedBy());
		}
		
		return businessYearDefinition;
	}

	@Override
	public BusinessYearDefinitionResponseDTO entityToDto(BusinessYearDefinition businessYearDefinition) {
		if(businessYearDefinition == null)
			return null;
			
		BusinessYearDefinitionResponseDTO responseDTO = new BusinessYearDefinitionResponseDTO();

			if(logger.isDebugEnabled())
				logger.debug("Maping BusinessYearDefinition fromDate-> "+businessYearDefinition.getFromDate()
						+", toDate-> "+businessYearDefinition.getToDate()
						+", createdBy-> "+businessYearDefinition.getCreatedBy()
						+", id-> "+businessYearDefinition.getId()
						+", createdDateTime-> "+businessYearDefinition.getCreateDateTime());
			
			responseDTO.setActive(businessYearDefinition.isActive());
			responseDTO.setCreatedBy(businessYearDefinition.getCreatedBy());
			responseDTO.setDescription(businessYearDefinition.getDescription());
			responseDTO.setId(businessYearDefinition.getId());
			responseDTO.setFromDate(DateUtils.getDateMonthString(businessYearDefinition.getFromDate()));
			responseDTO.setToDate(DateUtils.getDateMonthString(businessYearDefinition.getToDate()));			
			responseDTO.setCreatedDateTime(DateUtils.getDateMonthString(businessYearDefinition.getCreateDateTime()));		
		
		return responseDTO;
	}
	
	
	public List<BusinessYearDefinitionResponseDTO> entityListToDtoList(List<BusinessYearDefinition> BusinessYearDefinitionList) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessYearDefinition dto mapper mapping entitylist to dtolist");
		List<BusinessYearDefinitionResponseDTO> responseDTOList = new ArrayList<BusinessYearDefinitionResponseDTO>();
		
		if(!CollectionUtils.isEmpty(BusinessYearDefinitionList)) {
			for (BusinessYearDefinition businessYearDefinition : BusinessYearDefinitionList) {
				BusinessYearDefinitionResponseDTO responseDTO = entityToDto(businessYearDefinition); 
				if(responseDTO != null)
					responseDTOList.add(responseDTO);
			}
		}	
		return responseDTOList;
	}

}
