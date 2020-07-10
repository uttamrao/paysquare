package com.ps.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.enums.FrequencyEnum;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.beans.BusinessCycleBean;
import com.ps.entities.tenant.BusinessCycle;
import com.ps.services.BusinessCycleCommand;
import com.ps.services.BusinessCycleInvoker;
import com.ps.services.BusinessCycleService;
import com.ps.services.dao.repository.tenant.BusinessCycleRepository;
import com.ps.util.LocalDateUtils;
import com.ps.util.RequestUtils;

@Service
public class BusinessCycleServiceImpl implements BusinessCycleService {

	Logger logger = Logger.getLogger(BusinessCycleServiceImpl.class);
	
	@Autowired
	BusinessCycleRepository businessCycleRepository;
	
	@Autowired
	RequestUtils requestUtils;
	
	@Autowired
	BusinessCycleInvoker businessCycleInvoker;
	
	static Map<FrequencyEnum,BusinessCycleCommand> businessCycleCommandObject = null;
	
	static {		
		if(CollectionUtils.isEmpty(businessCycleCommandObject)) {			
				businessCycleCommandObject = new HashMap<FrequencyEnum,BusinessCycleCommand>();			
				businessCycleCommandObject.put(FrequencyEnum.MONTHLY, new BusinessCycleMonthlyImpl());
				businessCycleCommandObject.put(FrequencyEnum.SEMI_MONTHLY, new BusinessCycleSemiMonthlyImpl());
				businessCycleCommandObject.put(FrequencyEnum.WEEKLY, new BusinessCycleWeeklyImpl());
				businessCycleCommandObject.put(FrequencyEnum.BI_WEEKLY, new BusinessCycleBiWeeklyImpl());
				businessCycleCommandObject.put(FrequencyEnum.ADHOC, new BusinessCycleAdhocImpl());
		}		
	}
	
	@Override
	public  void add(BusinessCycleBean businessCycleBean) {
		
		if(logger.isDebugEnabled()) logger.debug("In add BusinessCycleDefinition");
		validate(businessCycleBean);		
		
		FrequencyEnum frequency = null;
				
		if(businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster() != null)
			frequency = businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster().getName();
		else
			frequency = FrequencyEnum.ADHOC;
				
		if(logger.isDebugEnabled()) logger.debug("Initializing businessCycle creation for frequency-> "+frequency);					
		
		// check last created cycle year and create cycle from that year till the noOfYears requested
		//i.e set start year to the year last cycle was created
		//based on the the duration it shouled be decided if cycle should be created for this year or next
		Optional<BusinessCycle> businessCycle = businessCycleRepository.findTopByBusinessCycleDefinitionOrderByFromDateDesc(businessCycleBean.getBusinessCycleDefinition());
		if(businessCycle.isPresent()) {
			LocalDate fromDate = LocalDateUtils.convert(businessCycle.get().getToDate(), ZoneId.systemDefault());
			businessCycleBean.setLastCreatedYear(fromDate.plusDays(1).getYear());			
		}		
			
		BusinessCycleCommand businessCycleCommand = businessCycleCommandObject.get(frequency);		
		List<BusinessCycle> cycleList = businessCycleInvoker.createCycle(businessCycleCommand, businessCycleBean);
					
		if (logger.isDebugEnabled())
			logger.debug("Saving  cycles->" +cycleList.size()+" into db");
		
		if(!CollectionUtils.isEmpty(cycleList))
			businessCycleRepository.saveAll(cycleList);			
	}

	private void validate(BusinessCycleBean businessCycleBean) {
		
		if(logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean, object-> "+businessCycleBean);
		if(businessCycleBean == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle details not found!");
		
		if(logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean cycle definition, object-> "+businessCycleBean.getBusinessCycleDefinition());
		if(businessCycleBean.getBusinessCycleDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle definition not found!");		
		
		if(logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean cycle definition, object-> "+businessCycleBean.getBusinessCycleDefinition());
		if(businessCycleBean.getBusinessCycleDefinition().getBusinessYearDefinition() == null)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business year definition not found!");	

		if(logger.isDebugEnabled())
			logger.debug("Validating BusinessCycleBean cycle definition, object-> "+businessCycleBean.getBusinessCycleDefinition());
		if(businessCycleBean.getBusinessCycleDefinition().getReoccuranceDays() == 0) {			
			if(logger.isDebugEnabled())
				logger.debug("Validating if frequency is set in businessCycleDefinition, object-> "+businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster());
			if(businessCycleBean.getBusinessCycleDefinition().getFrequencyMaster() == null)
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle frequency not found!");		
		}	
	}
	
	@Override
	public List<BusinessCycle> getAll() {				
		if(logger.isDebugEnabled())
			logger.debug("In BusinessCycle getAll");
		
		List<BusinessCycle> businessCycleList = businessCycleRepository.findAll();
		if(businessCycleList.isEmpty())
			throw new InvalidRequestException(ErrorCode.RESOURCE_NOT_FOUND, "Business Cycle Definition not found!");
		
		return businessCycleList;
	}

	@Override
	public List<BusinessCycle> getAllByCycleDefinition(int id) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessCycle getByCycleDefinition method, businessCycleDefinitionId-> "+id);
		if(id == 0)
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");
		
		List<BusinessCycle> businessCycleList = businessCycleRepository.findAllByCycleDefinitionId(id);		
		
		return businessCycleList;
	}
	
	@Override
	public void deleteByCycleDefinitionIds(List<Integer> ids) {
		
		if(logger.isDebugEnabled())
			logger.debug("In BusinessCycle getByCycleDefinition method, businessCycleDefinitionId-> "+ids);
		if(CollectionUtils.isEmpty(ids))
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Business Cycle Definition is Invalid!");
		
		businessCycleRepository.deleteAllByBusinessCycleDefinitionId(ids);
	}

}
