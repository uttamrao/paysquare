package com.ps.services.repository.global;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps.entities.global.ClientOrganizations;

public interface ClientOrganizationsRepository extends JpaRepository<ClientOrganizations, Long> {
	ClientOrganizations findByName(String name);
}