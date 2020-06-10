package com.ps.RESTful.dto.mapper;

import com.ps.RESTful.dto.request.FrequencyMasterRequestDTO;
import com.ps.RESTful.dto.response.FrequencyMasterResponseDTO;
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.util.DateUtils;

public class FrequencyMasterDTOMapper implements 
					AbstractDTOMapper<FrequencyMasterRequestDTO, FrequencyMasterResponseDTO, FrequencyMaster> {

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
	public FrequencyMasterResponseDTO entityToDto(FrequencyMaster entity) {
		
		FrequencyMasterResponseDTO responseDTO = new FrequencyMasterResponseDTO();
		
		if(entity != null) {
			
			responseDTO.setActive(entity.isActive());
			responseDTO.setCreatedBy(entity.getCreatedBy());
			responseDTO.setId(entity.getId());
			responseDTO.setName(entity.getName());
			responseDTO.setCreatedDateTime(DateUtils.getDateString(entity.getCreateDateTime()));
			
		}
		
		return responseDTO;
	}

}
