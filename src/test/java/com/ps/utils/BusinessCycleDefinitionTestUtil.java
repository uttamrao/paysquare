package com.ps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.entities.tenant.FrequencyMaster;

public class BusinessCycleDefinitionTestUtil {

	public static BusinessCycleDefinition add() {
		
		return createWithYearAndFrequency(FrequencyMasterTestUtil.add(), BusinessYearDefinitionTestUtil.add());
	}	

	public static List<BusinessCycleDefinition> getAllActive() {
		
		List<BusinessCycleDefinition> businessCycleDefinitionList = new ArrayList<BusinessCycleDefinition>();;

		businessCycleDefinitionList.add(add());
		businessCycleDefinitionList.add(add());
		
		return businessCycleDefinitionList;		
	}
	
	public static BusinessCycleDefinition createWithYearAndFrequency(FrequencyMaster frequencyMaster,
			BusinessYearDefinition businessYearDefinition) {
		
		BusinessCycleDefinition businessCycleDefinition = new BusinessCycleDefinition();
		businessCycleDefinition.setActive(true);
		businessCycleDefinition.setCreateDateTime(new Date());
		businessCycleDefinition.setCreatedBy("Ekta");
		businessCycleDefinition.setName("Angola");
		businessCycleDefinition.setFrequencyMaster(frequencyMaster);
		businessCycleDefinition.setBusinessYearDefinition(businessYearDefinition);
		
		return businessCycleDefinition;		
	}

	

}
