package com.ps.services.dao.repository.tenant;

import java.util.List;

import com.ps.entities.tenant.FrequencyMaster;

public interface FrequencyMasterRepository extends AbstractRepository<FrequencyMaster, Integer> {
	
	List<FrequencyMaster> findAllByIsActive(boolean isActive);
}