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
import com.ps.RESTful.enums.FrequencyEnum;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.entities.tenant.FrequencyMaster;
import com.ps.services.dao.repository.tenant.FrequencyMasterRepository;
import com.ps.services.impl.FrequencyMasterServiceImpl;
import com.ps.util.RequestUtils;
import com.ps.utils.FrequencyMasterTestUtil;

@RunWith(SpringRunner.class)
public class FrequencyMasterServiceTest {

	 @TestConfiguration
	 static class FrequencyMasterServiceImplTestContextConfiguration {	  
	       
		 @Bean
	        public FrequencyMasterService service() {
	            return new FrequencyMasterServiceImpl();
	        }
	  }
	 
	@Autowired
	private FrequencyMasterService frequencyMasterService;
	
	@MockBean
	RequestUtils RequestUtils;
	
	@MockBean
	private FrequencyMasterRepository frequencyMasterRepository;
	
	Optional<FrequencyMaster> frequencyMaster;
	List<FrequencyMaster> frequencyMasterList;

	@Before
	public void setUp() throws Exception {		
		//creating object for add test scenario
			frequencyMaster = Optional.of(FrequencyMasterTestUtil.add());
			System.out.println("before mocking repository add");

			Mockito.when(frequencyMasterRepository.save(Mockito.any(FrequencyMaster.class)))
					.thenReturn(frequencyMaster.get());
			System.out.println("after mocking repository add");
		
			Mockito.when(frequencyMasterRepository.findByName(FrequencyEnum.WEEKLY))
			.thenReturn(frequencyMaster);
			
		//creating object for getALl test scenario
			System.out.println("before mocking repository getAll");

			frequencyMasterList = FrequencyMasterTestUtil.getAllActive();
			Mockito.when(frequencyMasterRepository.findAllByIsActive(true))
					.thenReturn(frequencyMasterList);
			
			System.out.println("before mocking repository getAll");	
	}
	
	@Test
	public void add_PositiveTest() {		
		Mockito.when(RequestUtils.getUserName())
				.thenReturn(Mockito.anyString());
		
		frequencyMasterService.add(frequencyMaster.get());
	}
	
	@Test
	public void add_CreatedBy_NegativeTest() {		
		try {
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			frequencyMaster.get().setCreatedBy(null);
			frequencyMasterService.add(frequencyMaster.get());
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Created by is Invalid!",ex.getDescription());
		}		
	}
	
	@Test
	public void add_Duplicate_NegativeTest() {		
		try {
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			frequencyMasterService.add(frequencyMaster.get());
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Frequency already exists!",ex.getDescription());
		}		
	}
	
	@Test
	public void add_name_NegativeTest() {		
		try {			
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			frequencyMaster.get().setName(null);
			frequencyMasterService.add(frequencyMaster.get());
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Frequency name not found!",ex.getDescription());
		}		
	}
	
	@Test
	public void add_NegativeTest() {		
		try {
			
			Mockito.when(RequestUtils.getUserName())
			.thenReturn("Test");
	
			frequencyMasterService.add(null);
	
		} catch (InvalidRequestException ex) {
			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
			assertEquals("Frequency not found!",ex.getDescription());
		}		
	}
	
	@Test
	public void getAll_PositiveTest() {

			List<FrequencyMaster> frequencyMasterList = frequencyMasterService.getAll();			
			assertEquals(3, frequencyMasterList.size());	
	}

}
