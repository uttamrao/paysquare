package com.ps.services.repository.global;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sun.xml.bind.v2.model.core.ID;

@NoRepositoryBean
public interface GlobalEntitiesAbstractRepository<T,ID> extends JpaRepository<T, ID>{

}
