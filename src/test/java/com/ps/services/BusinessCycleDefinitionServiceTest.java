package com.ps.services;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.RESTful.enums.ErrorCode;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.BusinessCycleDefinition;
import com.ps.services.dao.repository.tenant.BusinessCycleDefinitionRepository;
import com.ps.services.impl.BusinessCycleDefinitionServiceImpl;
import com.ps.util.RequestUtils;
import com.ps.utils.BusinessCycleDefinitionTestUtil;

@RunWith(SpringRunner.class)
public class BusinessCycleDefinitionServiceTest {

	 @TestConfiguration
	 static class BusinessCycleDefinitionServiceImplTestContextConfiguration {  
	       
		 @Bean
	        public BusinessCycleDefinitionService service() {
	            return new BusinessCycleDefinitionServiceImpl();
	        }
	  }
	 
	@Autowired
	private BusinessCycleDefinitionService businessCycleDefinitionService;
	
	@MockBean
	RequestUtils RequestUtils;
	
	@MockBean
	private BusinessCycleDefinitionRepository businessCycleDefinitionRepository;
	
	Optional<BusinessCycleDefinition> businessCycleDefinitionOptional;
	BusinessCycleDefinition businessCycleDefinition;

	List<BusinessCycleDefinition> businessCycleDefinitionList;

	@Before
	public void setUp() throws Exception {		
		//creating object for add test scenario
		businessCycleDefinition = BusinessCycleDefinitionTestUtil.add();
			System.out.println("before mocking repository add");

			Mockito.when(businessCycleDefinitionRepository.save(Mockito.any(BusinessCycleDefinition.class)))
					.thenReturn(businessCycleDefinition);
			System.out.println("after mocking repository add");
		
		//creating object for getALl test scenario
			System.out.println("before mocking repository getAll");

			businessCycleDefinitionList = BusinessCycleDefinitionTestUtil.getAllActive();
			Mockito.when(businessCycleDefinitionRepository.findAllByIsActive(true))
					.thenReturn(businessCycleDefinitionList);
			
			System.out.println("before mocking repository getAll");	
	}
	
	@Test
	public void add_PositiveTest() {		
		Mockito.when(RequestUtils.getUserName())
				.thenReturn(Mockito.anyString());
		
		businessCycleDefinitionService.add(businessCycleDefinition);
	}
	
	@Test
	public void add_CreatedBy_NegativeTest() {		
		try {
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			businessCycleDefinition.setCreatedBy(null);
			businessCycleDefinitionService.add(businessCycleDefinition);
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Created by is Invalid!",ex.getDescription());
		}		
	}
	
	@Test
	public void add_name_NegativeTest() {		
		try {			
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			businessCycleDefinition.setName(null);
			businessCycleDefinitionService.add(businessCycleDefinition);
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Cycle Definition name not found!",ex.getDescription());
		}		
	}
	
	@Test
	public void add_frequency_NegativeTest() {		
		try {			
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			businessCycleDefinition.setFrequencyMaster(null);
			businessCycleDefinitionService.add(businessCycleDefinition);
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Frequency not found!",ex.getDescription());
		}		
	}
	
	@Test
	public void add_businessYear_NegativeTest() {		
		try {			
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			businessCycleDefinition.setBusinessYearDefinition(null);
			businessCycleDefinitionService.add(businessCycleDefinition);
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Business Year not found!",ex.getDescription());
		}		
	}
	
	@Test
	public void add_NegativeTest() {		
		try {
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			businessCycleDefinitionService.add(null);
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Cycle Definition not found!",ex.getDescription());
		}		
	}
	
	@Test
	public void getAll_PositiveTest() {

			List<BusinessCycleDefinition> businessCycleDefinitionList = businessCycleDefinitionService.getAll();			
			assertEquals(2, businessCycleDefinitionList.size());	
	}

}
