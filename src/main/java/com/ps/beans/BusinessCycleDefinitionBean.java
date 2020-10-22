package com.ps.beans;

import java.util.List;

import com.ps.RESTful.dto.request.BusinessCycleDefinitionRequestDTO;
import com.ps.dto.AbstractTimeDTO;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.entities.tenant.BusinessYearDefinition;
import com.ps.entities.tenant.FrequencyMaster;

public class BusinessCycleDefinitionBean extends AbstractTimeDTO {

	private int id;

	private String name;

	private List<String> serviceName;

	private FrequencyMaster frequencyMaster;

	private BusinessYearDefinition businessYearDefinition;

	private boolean isUsed;

	private String description;

	private boolean isActive;

	public BusinessCycleDefinitionBean() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getServiceName() {
		return serviceName;
	}

	public void setServiceName(List<String> serviceName) {
		this.serviceName = serviceName;
	}

	public boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FrequencyMaster getFrequencyMaster() {
		return frequencyMaster;
	}

	public void setFrequencyMaster(FrequencyMaster frequencyMaster) {
		this.frequencyMaster = frequencyMaster;
	}

	public BusinessYearDefinition getBusinessYearDefinition() {
		return businessYearDefinition;
	}

	public void setBusinessYearDefinition(BusinessYearDefinition businessYearDefinition) {
		this.businessYearDefinition = businessYearDefinition;
	}

	public BusinessCycleDefinitionBean dtoToBean(BusinessCycleDefinitionRequestDTO dto) {
		BusinessCycleDefinitionBean businessCycleDefinitionBean = null;

		if (dto != null) {
			businessCycleDefinitionBean = new BusinessCycleDefinitionBean();

			businessCycleDefinitionBean.setId(dto.getId());
			businessCycleDefinitionBean.setIsActive(dto.isActive());
			businessCycleDefinitionBean.setCreatedBy(dto.getCreatedBy());
			businessCycleDefinitionBean.setName(dto.getName());
			businessCycleDefinitionBean.setServiceName(dto.getServiceName());

//			if(!StringUtils.isBlank(dto.getWeekStartDefinition()) && WeeksEnum.isValid(dto.getWeekStartDefinition()))
//				businessCycleDefinition.setWeekStartDefinition(WeeksEnum.valueOf(dto.getWeekStartDefinition()));

//			businessCycleDefinition.setReoccuranceDays(dto.getReOccuranceDays());
//			businessCycleDefinition.setForceToBusinessYearEnd(dto.isForceToBusinessYearEnd());
			businessCycleDefinitionBean.setDescription(dto.getDescription());
			businessCycleDefinitionBean.setIsUsed(dto.getIsUsed());
		}

		return businessCycleDefinitionBean;
	}

	public BusinessCycleDefinition beanToEntity(BusinessCycleDefinitionBean businessCycleDefinitionBean) {
		BusinessCycleDefinition businessCycleDefinition = null;

		if (businessCycleDefinitionBean != null) {
			businessCycleDefinition = new BusinessCycleDefinition();

			businessCycleDefinition.setId(businessCycleDefinitionBean.getId());
			businessCycleDefinition.setActive(businessCycleDefinitionBean.getIsActive());
			businessCycleDefinition.setCreatedBy(businessCycleDefinitionBean.getCreatedBy());
			businessCycleDefinition.setName(businessCycleDefinitionBean.getName());
			businessCycleDefinition.setDescription(businessCycleDefinitionBean.getDescription());
			businessCycleDefinition.setIsUsed(businessCycleDefinitionBean.getIsUsed());
			businessCycleDefinition.setBusinessYearDefinition(businessCycleDefinitionBean.getBusinessYearDefinition());
			businessCycleDefinition.setFrequencyMaster(businessCycleDefinitionBean.getFrequencyMaster());
		}

		return businessCycleDefinition;
	}
}
