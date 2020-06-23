package com.ps.services;

import java.util.List;

import com.ps.entities.tenant.FrequencyMaster;

public interface FrequencyMasterService {

	public FrequencyMaster add(FrequencyMaster frequencyMaster);
	
	public List<FrequencyMaster> getAll();
	
	public FrequencyMaster getById(int id);

}
