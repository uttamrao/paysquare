package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import com.ps.RESTful.enums.FrequencyEnum;
import com.ps.entities.tenant.FrequencyMaster;

public interface FrequencyMasterRepository extends AbstractRepository<FrequencyMaster, Integer> {
	
	List<FrequencyMaster> findAllByIsActive(boolean isActive);
	
	Optional<FrequencyMaster> findByName(FrequencyEnum name);
}