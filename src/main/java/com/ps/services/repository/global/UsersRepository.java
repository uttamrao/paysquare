package com.ps.services.repository.global;

import java.util.Optional;

import com.ps.entities.global.Users;

public interface UsersRepository extends GlobalEntitiesAbstractRepository<Users, Integer> {
	Users findByName(String name);
	
	Optional<Users> findByLogin(String login);
}