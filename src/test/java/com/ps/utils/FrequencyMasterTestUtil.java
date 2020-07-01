package com.ps.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ps.RESTful.enums.FrequencyEnum;
import com.ps.entities.tenant.FrequencyMaster;

public class FrequencyMasterTestUtil {

	public static FrequencyMaster add() {
		
		FrequencyMaster frequencyMaster = createWithActive(true, FrequencyEnum.WEEKLY);
		
		return frequencyMaster;		
	}	

	public static List<FrequencyMaster> getAllActive() {
		
		List<FrequencyMaster> frequencyMasterList = new ArrayList<FrequencyMaster>();;

		frequencyMasterList.add(createWithActive(true,FrequencyEnum.MONTHLY));
		frequencyMasterList.add(createWithActive(true,FrequencyEnum.BI_WEEKLY));
		frequencyMasterList.add(createWithActive(true,FrequencyEnum.SEMI_MONTHLY));
		
		return frequencyMasterList;		
	}
	
	
	public static FrequencyMaster createWithActive(boolean isActive, FrequencyEnum name) {
		
		FrequencyMaster frequencyMaster = new FrequencyMaster();
		frequencyMaster.setActive(true);
		frequencyMaster.setCreateDateTime(new Date());
		frequencyMaster.setCreatedBy("Ekta");
		frequencyMaster.setName(name);
		
		return frequencyMaster;		
	}

	

}
