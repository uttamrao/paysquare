package com.ps.services;

//@RunWith(SpringRunner.class)
public class BusinessYearDefinitionServiceTest {

//	 @TestConfiguration
//	 static class BusinessYearDefinitionServiceImplTestContextConfiguration {  
//	       
//		 @Bean
//	        public BusinessYearDefinitionService service() {
//	            return new BusinessYearDefinitionServiceImpl();
//	        }
//	  }
//	 
//	@Autowired
//	private BusinessYearDefinitionService businessYearDefinitionService;
//	
//	@MockBean
//	RequestUtils RequestUtils;
//	
//	@MockBean
//	private BusinessYearDefinitionRepository businessYearDefinitionRepository;
//	
//	BusinessYearDefinition businessYearDefinition;
//	List<BusinessYearDefinition> businessYearDefinitionList;
//
//	@Before
//	public void setUp() throws Exception {		
//		//creating object for add test scenario
//		businessYearDefinition = BusinessYearDefinitionTestUtil.add();
//			System.out.println("before mocking repository add");
//
//			Mockito.when(businessYearDefinitionRepository.save(Mockito.any(BusinessYearDefinition.class)))
//					.thenReturn(businessYearDefinition);
//			System.out.println("after mocking repository add");
//		
//		//creating object for getALl test scenario
//			System.out.println("before mocking repository getAll");
//
//			businessYearDefinitionList = BusinessYearDefinitionTestUtil.getAllActive();
//			Mockito.when(businessYearDefinitionRepository.findAllByIsActive(true))
//					.thenReturn(businessYearDefinitionList);
//			
//			System.out.println("before mocking repository getAll");	
//	}
//	
//	@Test
//	public void add_PositiveTest() {		
//		Mockito.when(RequestUtils.getUserName())
//				.thenReturn(Mockito.anyString());
//		
//		businessYearDefinitionService.add(businessYearDefinition);
//	}
//	
//	@Test
//	public void add_CreatedBy_NegativeTest() {		
//		try {
//			
//			Mockito.when(RequestUtils.getUserName())
//			.thenReturn("Test");
//	
//			businessYearDefinition.setCreatedBy(null);
//			businessYearDefinitionService.add(businessYearDefinition);
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Created by is Invalid!",ex.getDescription());
//		}		
//	}
//	
//	@Test
//	public void add_fromDate_NegativeTest() {		
//		try {			
//			
//			Mockito.when(RequestUtils.getUserName())
//			.thenReturn("Test");
//	
//			businessYearDefinition.setFromDate(null);
//			businessYearDefinitionService.add(businessYearDefinition);
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Business Year Definition from date not found!",ex.getDescription());
//		}		
//	}
//	
//	@Test
//	public void add_toDate_NegativeTest() {		
//		try {			
//			
//			Mockito.when(RequestUtils.getUserName())
//			.thenReturn("Test");
//	
//			businessYearDefinition.setToDate(null);
//			businessYearDefinitionService.add(businessYearDefinition);
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Business Year Definition to date not found!",ex.getDescription());
//		}		
//	}
//	
//	@Test
//	public void add_NegativeTest() {		
//		try {
//			
//			Mockito.when(RequestUtils.getUserName())
//			.thenReturn("Test");
//	
//			businessYearDefinitionService.add(null);
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Business Year Definition not found!",ex.getDescription());
//		}		
//	}
//	
//	@Test
//	public void getAll_PositiveTest() {
//
//			List<BusinessYearDefinition> businessYearDefinitionList = businessYearDefinitionService.getAll();			
//			assertEquals(2, businessYearDefinitionList.size());	
//	}

}
