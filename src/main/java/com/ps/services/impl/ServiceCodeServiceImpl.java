package com.ps.services.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.entities.master.ServiceCode;
import com.ps.services.ServiceCodeService;
import com.ps.services.dao.repository.master.ServiceCodeRepository;

@Service
public class ServiceCodeServiceImpl implements ServiceCodeService {

	Logger logger = Logger.getLogger(ServiceCodeServiceImpl.class);

	@Autowired
	ServiceCodeRepository serviceCodeRepository;

	@Autowired
	ServiceCodeService serviceCodeService;

	@Override
	public List<ServiceCode> getAll() {

		if (logger.isDebugEnabled())
			logger.debug("Getting all Service code records from DB");
		return serviceCodeRepository.findAll();
	}

}
