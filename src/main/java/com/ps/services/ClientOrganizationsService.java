package com.ps.services;

import java.util.List;

import com.ps.entities.global.ClientOrganizations;

public interface ClientOrganizationsService {

	public void add(ClientOrganizations clientOrganizations);
	public List<ClientOrganizations> getAllClientOrganizations();
	public void deleteById(long id);

}
