package com.ps.entities.common;

public class Statuses {

	public enum EmployeeJoiningStage{
		PRE_JOINING("Pre joining"),
		POST_JOINING("Post joining"),
		COMPLETE("Complete");
		
		private String value;
		
		EmployeeJoiningStage(String value){
			 this.value = value;
		}
		
		public static boolean isValid(String name) {			
			boolean isValid = false;
			
			for (EmployeeJoiningStage stage: EmployeeJoiningStage.values()) {
				
				if(stage.name().equals(name)) {
					isValid = true;
					break;
				}
			}			
			return isValid;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public enum EmployeeJoiningStatuses{
		APPROVED("Approved"),
		PENDING("Waiting for approval");
		
		private String value;

		EmployeeJoiningStatuses(String value){
			 this.value = value;
		}
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		public static boolean isValid(String name) {			
			boolean isValid = false;
			
			for (EmployeeJoiningStatuses status: EmployeeJoiningStatuses.values()) {
				
				if(status.name().equals(name)) {
					isValid = true;
					break;
				}
			}			
			return isValid;
		}
	}
	
	public enum EmployeeRelationshipStatuses{
		MOTHER("Mother"),
		FATHER("Father"),
		SISTER("Sister"),
		BROTHER("Brother"),
		GUARDIAN("Guardian");
		
		private String value;

		EmployeeRelationshipStatuses(String value){
			 this.value = value;
		}
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		public static boolean isValid(String name) {			
			boolean isValid = false;
			
			for (EmployeeRelationshipStatuses status: EmployeeRelationshipStatuses.values()) {
				
				if(status.name().equals(name)) {
					isValid = true;
					break;
				}
			}			
			return isValid;
		}
	}
}
