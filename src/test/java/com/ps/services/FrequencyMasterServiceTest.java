package com.ps.services;

//@RunWith(SpringRunner.class)
public class FrequencyMasterServiceTest {

//	 @TestConfiguration
//	 static class FrequencyMasterServiceImplTestContextConfiguration {	  
//	       
//		 @Bean
//	        public FrequencyMasterService service() {
//	            return new FrequencyMasterServiceImpl();
//	        }
//	  }
//	 
//	@Autowired
//	private FrequencyMasterService frequencyMasterService;
//	
//	@MockBean
//	RequestUtils RequestUtils;
//	
//	@MockBean
//	private FrequencyMasterRepository frequencyMasterRepository;
//	
//	Optional<FrequencyMaster> frequencyMaster;
//	List<FrequencyMaster> frequencyMasterList;
//
//	@Before
//	public void setUp() throws Exception {		
//		//creating object for add test scenario
//			frequencyMaster = Optional.of(FrequencyMasterTestUtil.add());
//			System.out.println("before mocking repository add");
//
//			Mockito.when(frequencyMasterRepository.save(Mockito.any(FrequencyMaster.class)))
//					.thenReturn(frequencyMaster.get());
//			System.out.println("after mocking repository add");
//		
//			Mockito.when(frequencyMasterRepository.findByNameAndIsActive(FrequencyEnum.WEEKLY,true))
//			.thenReturn(frequencyMaster);
//			
//		//creating object for getALl test scenario
//			System.out.println("before mocking repository getAll");
//
//			frequencyMasterList = FrequencyMasterTestUtil.getAllActive();
//			Mockito.when(frequencyMasterRepository.findAllByIsActive(true))
//					.thenReturn(frequencyMasterList);
//			
//			System.out.println("before mocking repository getAll");	
//	}
//	
//	@Test
//	public void add_PositiveTest() {		
//		Mockito.when(RequestUtils.getUserName())
//				.thenReturn(Mockito.anyString());
//		
//		frequencyMasterService.add(frequencyMaster.get());
//	}
//	
//	@Test
//	public void add_CreatedBy_NegativeTest() {		
//		try {
//			
//			Mockito.when(RequestUtils.getUserName())
//			.thenReturn("Test");
//	
//			frequencyMaster.get().setCreatedBy(null);
//			frequencyMasterService.add(frequencyMaster.get());
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Created by is Invalid!",ex.getDescription());
//		}		
//	}
//	
//	@Test
//	public void add_Duplicate_NegativeTest() {		
//		try {
//			
//			Mockito.when(RequestUtils.getUserName())
//			.thenReturn("Test");
//	
//			frequencyMasterService.add(frequencyMaster.get());
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Frequency already exists!",ex.getDescription());
//		}		
//	}
//	
//	@Test
//	public void add_name_NegativeTest() {		
//		try {			
//			
//			Mockito.when(RequestUtils.getUserName())
//			.thenReturn("Test");
//	
//			frequencyMaster.get().setName(null);
//			frequencyMasterService.add(frequencyMaster.get());
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Frequency name not found!",ex.getDescription());
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
//			frequencyMasterService.add(null);
//	
//		} catch (InvalidRequestException ex) {
//			assertEquals(ErrorCode.BAD_REQUEST, ex.getErrorCode());
//			assertEquals("Frequency not found!",ex.getDescription());
//		}		
//	}
//	
//	@Test
//	public void getAll_PositiveTest() {
//
//			List<FrequencyMaster> frequencyMasterList = frequencyMasterService.getAll();			
//			assertEquals(3, frequencyMasterList.size());	
//	}

}
