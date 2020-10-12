package com.ps.services.dao.repository.master;

import java.util.Optional;

import com.ps.entities.master.GroupCompanyMaster;

public interface GroupCompanyMasterRepository extends AbstractRepository<GroupCompanyMaster, Integer> {
	
	Optional<GroupCompanyMaster> findByGroupName(String name);
	
}