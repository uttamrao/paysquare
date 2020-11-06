package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.BusinessCycleRequestDTO;
import com.ps.RESTful.dto.request.BusinessCycleUpdateRequestDTO;
import com.ps.RESTful.dto.response.BusinessCycleResponseDTO;
import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.util.DateUtils;

@Component
public class BusinessCycleDTOMapper
		implements AbstractDTOMapper<BusinessCycleRequestDTO, BusinessCycleResponseDTO, BusinessCycle> {

	Logger logger = Logger.getLogger(BusinessCycleDTOMapper.class);

	@Autowired
	BusinessCycleDefinitionDTOMapper businessCycleDefinitionDTOMapper;

	@Override
	public BusinessCycle dtoToEntity(BusinessCycleRequestDTO dto) {

		BusinessCycle businessCycle = null;

		return businessCycle;
	}

	@Override
	public BusinessCycleResponseDTO entityToDto(BusinessCycle businessCycle) {
		if (businessCycle == null)
			return null;

		BusinessCycleResponseDTO responseDTO = new BusinessCycleResponseDTO();

		responseDTO.setId(businessCycle.getId());
		responseDTO.setPeriodId(businessCycle.getPeriodId());
		responseDTO.setPeriodName(businessCycle.getPeriodName());
		responseDTO.setLocked(businessCycle.isLocked());
		responseDTO.setFromDate(DateUtils.getDateTimeString(businessCycle.getFromDate()));
		responseDTO.setToDate(DateUtils.getDateTimeString(businessCycle.getToDate()));
		responseDTO.setBusinessYear(businessCycle.getBusinessYear());
		responseDTO.setNoOfDays(businessCycle.getNoOfDays());
		responseDTO.setNoOfCycles(businessCycle.getNoOfCycles());
		responseDTO.setForceToYearEnd(businessCycle.isForceToYearEnd());
		responseDTO.setUsed(businessCycle.isUsed());
		responseDTO.setActive(businessCycle.isActive());
		responseDTO.setRemark(businessCycle.getRemark());

		responseDTO.setBusinessCycleDefinition(
				businessCycleDefinitionDTOMapper.entityToDto(businessCycle.getBusinessCycleDefinition()));
		responseDTO.setId(businessCycle.getId());

		return responseDTO;
	}

	public List<BusinessCycleResponseDTO> entityListToDtoList(List<BusinessCycle> businessCycleList) {

		if (logger.isDebugEnabled())
			logger.debug("In BusinessYear dto mapper mapping entitylist to dtolist");
		List<BusinessCycleResponseDTO> responseDTOList = new ArrayList<BusinessCycleResponseDTO>();

		if (!CollectionUtils.isEmpty(businessCycleList)) {
			for (BusinessCycle businessCycle : businessCycleList) {
				BusinessCycleResponseDTO responseDTO = entityToDto(businessCycle);
				if (responseDTO != null)
					responseDTOList.add(responseDTO);
			}
		}
		return responseDTOList;
	}

	////////////////////////////////////////////////////////////////////////////////

	public BusinessCycleBean dtoToBean(BusinessCycleRequestDTO dto) {

		BusinessCycleBean businessCycleBean = null;

		if (dto != null) {

			businessCycleBean = new BusinessCycleBean();
			businessCycleBean.setNoOfYears(dto.getNoOfYears());
			businessCycleBean.setBusinessYear(dto.getBusinessYear());
		}

		return businessCycleBean;
	}

	public BusinessCycle beanToEntity(BusinessCycleBean businessCycleBean) {
		return null;
	}

	// for update method
	public BusinessCycle updateResponseDtoToEntity(BusinessCycleResponseDTO dto) {
		BusinessCycle businessCycle = new BusinessCycle();

		if (dto != null) {
			businessCycle.setId(dto.getId());
			businessCycle.setPeriodId(dto.getPeriodId());
			businessCycle.setPeriodName(dto.getPeriodName());
			businessCycle.setLocked(dto.isLocked());
			businessCycle.setFromDate(DateUtils.stringToDate(dto.getFromDate()));
			businessCycle.setToDate(DateUtils.stringToDate(dto.getToDate()));
			businessCycle.setBusinessYear(dto.getBusinessYear());
			businessCycle.setNoOfDays(dto.getNoOfDays());
			businessCycle.setNoOfCycles(dto.getNoOfCycles());
			businessCycle.setRemark(dto.getRemark());
			businessCycle.setUsed(dto.isUsed());
			businessCycle.setActive(dto.isActive());

		}
		return businessCycle;
	}

	public List<BusinessCycle> updateDtoToEntityList(BusinessCycleUpdateRequestDTO dto,
			BusinessCycleDefinition businessCycleDefinition) {

		if (logger.isDebugEnabled())
			logger.debug("dto: -->" + dto);

		if (logger.isDebugEnabled())
			logger.debug("In BusinessYear dto mapper mapping dto to entity list");
		List<BusinessCycle> businessCycleList = new ArrayList<>();

		if (dto != null) {

			// businessCycleList = dto.getBusinessCycleList();
			for (int i = 0; i < dto.getBusinessCycleList().size(); i++) {
				BusinessCycle businessCycle = updateResponseDtoToEntity(dto.getBusinessCycleList().get(i));
				businessCycle.setBusinessCycleDefinition(businessCycleDefinition);
				businessCycle.setForceToYearEnd(dto.isForceToBusinessYearEnd());
				businessCycle.setAdjustedToNextCycle(dto.isAdjustedToNextCycle());
				businessCycleList.add(businessCycle);
			}
		}

		return businessCycleList;
	}
}
