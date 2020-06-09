package com.ps.services;

import java.util.List;

import com.ps.entities.master.GroupCompanyMaster;

public interface GroupCompanyMasterService {

	public List<GroupCompanyMaster> getAll();
	
	public GroupCompanyMaster getById(int id);
	
}
