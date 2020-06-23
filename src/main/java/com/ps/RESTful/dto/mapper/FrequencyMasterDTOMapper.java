package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.FrequencyMasterRequestDTO;
import com.ps.RESTful.dto.response.FrequencyMasterResponseDTO;
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.util.DateUtils;

@Component
public class FrequencyMasterDTOMapper implements 
					AbstractDTOMapper<FrequencyMasterRequestDTO, FrequencyMasterResponseDTO, FrequencyMaster> {

	Logger logger = Logger.getLogger(FrequencyMasterDTOMapper.class);

	@Override
	public FrequencyMaster dtoToEntity(FrequencyMasterRequestDTO dto) {
		
		FrequencyMaster frequencyMaster = null;
		
		if(dto != null) {
			frequencyMaster = new FrequencyMaster();
			
			frequencyMaster.setActive(dto.isActive());
			frequencyMaster.setName(dto.getName());
			frequencyMaster.setCreatedBy(dto.getCreatedBy());
		}
		
		return frequencyMaster;
	}

	@Override
	public FrequencyMasterResponseDTO entityToDto(FrequencyMaster frequencyMaster) {
		
		if(frequencyMaster == null)
			return null;
			
			FrequencyMasterResponseDTO responseDTO = new FrequencyMasterResponseDTO();

			if(logger.isDebugEnabled())
				logger.debug("Maping Frequency master name-> "+frequencyMaster.getName()
						+", createdBy-> "+frequencyMaster.getCreatedBy()
						+", id-> "+frequencyMaster.getId()
						+", createdDateTime-> "+frequencyMaster.getCreateDateTime());
			
			responseDTO.setActive(frequencyMaster.isActive());
			responseDTO.setCreatedBy(frequencyMaster.getCreatedBy());
			responseDTO.setId(frequencyMaster.getId());
			responseDTO.setName(frequencyMaster.getName());
			responseDTO.setCreateDateTime(DateUtils.getDateTimeString(frequencyMaster.getCreateDateTime()));
		
		
		return responseDTO;
	}
	
	public List<FrequencyMasterResponseDTO> entityListToDtoList(List<FrequencyMaster> frequencyMasterList) {
		
		if(logger.isDebugEnabled())
			logger.debug("In frequency master dto mapper mapping entitylist to dtolist");
		List<FrequencyMasterResponseDTO> frequencyMasterResponseDTOList = new ArrayList<FrequencyMasterResponseDTO>();
		
		if(!CollectionUtils.isEmpty(frequencyMasterList)) {
			for (FrequencyMaster frequencyMaster : frequencyMasterList) {
				FrequencyMasterResponseDTO frequencyMasterResponseDTO = entityToDto(frequencyMaster); 
				if(frequencyMasterResponseDTO != null)
					frequencyMasterResponseDTOList.add(frequencyMasterResponseDTO);
			}
		}	
		return frequencyMasterResponseDTOList;
	}

}
