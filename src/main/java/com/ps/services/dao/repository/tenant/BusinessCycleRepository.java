package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;

public interface BusinessCycleRepository extends AbstractRepository<BusinessCycle, Integer> {

	Optional<BusinessCycle> findTopByBusinessCycleDefinitionOrderByFromDateDesc(
			BusinessCycleDefinition businessCycleDefinition);

	@Query("Select c from BusinessCycle c where c.businessCycleDefinition.id = :id and c.businessYear=:businessYear and c.isActive=true")
	List<BusinessCycle> findAllByCycleDefinitionIdAndBusinessYear(@Param("id") int id,
			@Param("businessYear") String businessYear);

	@Query("Select c from BusinessCycle c where c.businessCycleDefinition.id = :id")
	List<BusinessCycle> findAllByCycleDefinitionId(@Param("id") int id);

	@Query("Select c from BusinessCycle c where c.isLocked = false and c.businessCycleDefinition.businessYearDefinition.id = :yearDefinitionId")
	List<BusinessCycle> findAllByBusinessYearDefinitionId(@Param("yearDefinitionId") int yearDefinitionId);

	// hard delete
	@Modifying
	@Query("DELETE FROM BusinessCycle c where c.businessCycleDefinition.id in ?1")
	void deleteAllByBusinessCycleDefinitionIds(List<Integer> cycleDefinitionIds);

	// soft delete by business year and business cycle definition id
	@Modifying
	@Query("UPDATE BusinessCycle c set c.isActive=false where c.businessCycleDefinition.id = :id and c.businessYear=:businessYear")
	void softdeleteByCycleDefinitionIdAndBusinessYear(@Param("id") int id, @Param("businessYear") String businessYear);

	// soft delete by business year and business cycle definition id
	@Modifying
	@Query("DELETE FROM BusinessCycle c where c.businessCycleDefinition.id = :id and c.businessYear=:businessYear")
	void hardDeleteByCycleDefinitionIdAndBusinessYear(@Param("id") int id, @Param("businessYear") String businessYear);

	// Get all by period id and isActive
	@Query("Select c from BusinessCycle c where c.isActive = true and c.periodId=1")
	List<BusinessCycle> findAllByPeriodIdByIsActive();

}