package com.ps.main;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoAssignmentApplication {
	
	static Logger logger = Logger.getLogger(DemoAssignmentApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoAssignmentApplication.class, args);
		logger.error("Ekta");		
	}

}
