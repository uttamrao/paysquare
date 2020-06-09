package com.ps.services.repository.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;


public class CustomRepositoryImpl<T,ID> implements CustomRepository<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;
	

	  @Override
	  @Transactional
	  public void refreshEntity(T t) {
	    entityManager.refresh(t);
	  }
}
