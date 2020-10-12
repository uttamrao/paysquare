package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.BusinessCycleRequestDTO;
import com.ps.RESTful.dto.response.BusinessCycleResponseDTO;
import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.util.DateUtils;

@Component
public class BusinessCycleDTOMapper implements 
					AbstractDTOMapper<BusinessCycleRequestDTO, BusinessCycleResponseDTO, BusinessCycle> {

	Logger logger = Logger.getLogger(BusinessCycleDTOMapper.class);

	@Autowired
	BusinessCycleDefinitionDTOMapper businessCycleDefinitionDTOMapper;
	
	@Override
	public BusinessCycle dtoToEntity(BusinessCycleRequestDTO dto) {

		BusinessCycle businessCycle = null;
				
		return businessCycle;
	}
	
	@Override
	public BusinessCycleResponseDTO entityToDto(BusinessCycle businessCycle) {
		if(businessCycle == null)
			return null;
			
		BusinessCycleResponseDTO responseDTO = new BusinessCycleResponseDTO();

//			if(logger.isDebugEnabled())
//				logger.debug("Maping BusinessCycle"
//						+", period-> "+businessCycle.getPeriodId()
//						+", period name-> "+businessCycle.getPeriodName()
//						+", business-cycle-definition id-> "+businessCycle.getBusinessCycleDefinition().getId()
//						+", from date-> "+businessCycle.getFromDate()
//						+", to date-> "+businessCycle.getToDate()
//						+", createdBy-> "+businessCycle.getCreatedBy()
//						+", id-> "+businessCycle.getId()
//						+", createdDateTime-> "+businessCycle.getCreateDateTime());
			
			responseDTO.setLocked(businessCycle.isLocked());
			responseDTO.setPeriodId(businessCycle.getPeriodId());
			responseDTO.setPeriodName(businessCycle.getPeriodName());
			responseDTO.setFromDate(DateUtils.getDateTimeString(businessCycle.getFromDate()));
			responseDTO.setToDate(DateUtils.getDateTimeString(businessCycle.getToDate()));
			responseDTO.setBusinessCycleDefinition(businessCycleDefinitionDTOMapper.entityToDto(businessCycle.getBusinessCycleDefinition())); 
			responseDTO.setId(businessCycle.getId());
			//responseDTO.setCreateDateTime(DateUtils.getDateTimeString(businessCycle.getCreateDateTime()));		
		
		return responseDTO;
	}
	
	public List<BusinessCycleResponseDTO> entityListToDtoList(List<BusinessCycle> businessCycleList) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessYear dto mapper mapping entitylist to dtolist");
		List<BusinessCycleResponseDTO> responseDTOList = new ArrayList<BusinessCycleResponseDTO>();
		
		if(!CollectionUtils.isEmpty(businessCycleList)) {
			for (BusinessCycle businessCycle : businessCycleList) {
				BusinessCycleResponseDTO responseDTO = entityToDto(businessCycle); 
				if(responseDTO != null)
					responseDTOList.add(responseDTO);
			}
		}	
		return responseDTOList;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	
	public BusinessCycleBean dtoToBean(BusinessCycleRequestDTO dto) {

		BusinessCycleBean businessCycleBean = null;
		
		if(dto != null) {
		
			businessCycleBean = new BusinessCycleBean();
			businessCycleBean.setNoOfYears(dto.getNoOfYears());
		}			
		
		return businessCycleBean;
	}
	
	public BusinessCycle beanToEntity(BusinessCycleBean businessCycleBean) {
		return null;					
	}

}
