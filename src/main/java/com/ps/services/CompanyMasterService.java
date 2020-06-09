package com.ps.services;

import com.ps.entities.master.CompanyMaster;

public interface CompanyMasterService {
	
	public CompanyMaster getById(int id);
	
	public CompanyMaster getByIdWithGroup(int id);

}
