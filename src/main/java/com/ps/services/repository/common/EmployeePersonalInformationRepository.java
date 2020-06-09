package com.ps.services.repository.common;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.common.EmployeePersonalInformation;

public interface EmployeePersonalInformationRepository extends AbstractRepository<EmployeePersonalInformation, Integer> {

	@Query("SELECT pi FROM EmployeePersonalInformation pi where pi.employee.id = :employeeId")
	Optional<EmployeePersonalInformation> findByEmployeeId(@Param("employeeId") int employeeId);

	@Modifying
	@Query("DELETE FROM EmployeePersonalInformation pi where pi.employee.id = :employeeID")
	void deleteByEmployeeId(@Param("employeeID") int employeeID);
}
