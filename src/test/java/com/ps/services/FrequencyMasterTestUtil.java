package com.ps.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ps.entities.tenant.FrequencyMaster;

public class FrequencyMasterTestUtil {

	public static FrequencyMaster add() {
		
		FrequencyMaster frequencyMaster = createWithActive(true, "YEARLY");
		
		return frequencyMaster;		
	}	

	public static List<FrequencyMaster> getAllActive() {
		
		List<FrequencyMaster> frequencyMasterList = new ArrayList<FrequencyMaster>();;

		frequencyMasterList.add(createWithActive(true,"YEARLY"));
		frequencyMasterList.add(createWithActive(true,"SEMI_MONTHLY"));
		frequencyMasterList.add(createWithActive(true,"MONTHLY"));
		
		return frequencyMasterList;		
	}
	
	
	public static FrequencyMaster createWithActive(boolean isActive, String name) {
		
		FrequencyMaster frequencyMaster = new FrequencyMaster();
		frequencyMaster.setActive(true);
		frequencyMaster.setCreateDateTime(new Date());
		frequencyMaster.setCreatedBy("Ekta");
		frequencyMaster.setName(name);
		
		return frequencyMaster;		
	}

	

}
