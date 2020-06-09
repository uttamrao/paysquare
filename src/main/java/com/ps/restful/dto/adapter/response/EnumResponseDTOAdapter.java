package com.ps.restful.dto.adapter.response;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.util.CollectionUtils;

import com.ps.dto.EnumDTO;
import com.ps.entities.global.ClientOrganizations;
import com.ps.restful.dto.response.ClientOrganizationsResponseDTO;
import com.ps.restful.resources.impl.EmployeeDetailsResourceImpl;

public class EnumResponseDTOAdapter {

public List<EnumDTO> buildResponseFrom(Object contents) {
	
	Logger logger = Logger.getLogger(EnumResponseDTOAdapter.class);

		List<EnumDTO> enumResponseDTOList = new ArrayList<EnumDTO>();
		
		Enum[] enums = (Enum[])contents;
		
		for(Enum myEnum: enums){
			
			String shortCode = myEnum.name();
			String displayText = null;
			
			try {
			
				Method method = myEnum.getClass().getDeclaredMethod("getValue", null);
				
				if(!method.isAccessible()){
					method.setAccessible(true);
				}
				
				displayText = (String)method.invoke(myEnum, null);
			} catch (Exception e) {
				logger.error("Exception !", e);
			}
			
			enumResponseDTOList.add(new EnumDTO(displayText,shortCode));
			
		}
		
		return enumResponseDTOList;
	}
}
