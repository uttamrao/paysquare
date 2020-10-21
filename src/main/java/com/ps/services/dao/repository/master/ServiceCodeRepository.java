package com.ps.services.dao.repository.master;

import com.ps.entities.master.ServiceCode;

public interface ServiceCodeRepository extends AbstractRepository<ServiceCode, Integer> {

	ServiceCode findByServiceName(String serviceName);

}
