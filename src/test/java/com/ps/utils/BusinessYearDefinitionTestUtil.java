package com.ps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.util.DateUtils;

public class BusinessYearDefinitionTestUtil {

	public static BusinessYearDefinition add() {
		
		return createWithYearDefinition("01/01","31/12");
	}	

	public static List<BusinessYearDefinition> getAllActive() {
		
		List<BusinessYearDefinition> businessYearDefinitionList = new ArrayList<BusinessYearDefinition>();;

		businessYearDefinitionList.add(createWithYearDefinition("01/01","31/12"));
		businessYearDefinitionList.add(createWithYearDefinition("01/04","31/03"));
		
		return businessYearDefinitionList;		
	}
	
	public static BusinessYearDefinition createWithYearDefinition(String fromDate,String toDate) {
		
		BusinessYearDefinition businessYearDefinition = new BusinessYearDefinition();
		businessYearDefinition.setActive(true);
		businessYearDefinition.setCreateDateTime(new Date());
		businessYearDefinition.setCreatedBy("Ekta");
		businessYearDefinition.setFromDate(DateUtils.getDateMonth(fromDate));
		businessYearDefinition.setToDate(DateUtils.getDateMonth(toDate));
		
		return businessYearDefinition;		
	}

	

}
