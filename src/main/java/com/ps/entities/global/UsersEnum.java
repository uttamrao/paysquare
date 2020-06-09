package com.ps.entities.global;

public class UsersEnum {

	public enum UserRoles{
		CLIENT("Client"),
		ADMIN("Admin"),
		SUPER_ADMIN("Super Admin");
		
		
		private String value;

		UserRoles(String value){
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
			
			for (UserRoles roles: UserRoles.values()) {
				
				if(roles.name().equals(name)) {
					isValid = true;
					break;
				}
			}			
			return isValid;
		}
	}
	
	public enum UserState{
		ACTIVE("Client"),
		INACTIVE("Admin"),
		NEW("Super Admin");
		
		
		private String value;

		UserState(String value){
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
			
			for (UserState state: UserState.values()) {
				
				if(state.name().equals(name)) {
					isValid = true;
					break;
				}
			}			
			return isValid;
		}
	}
}
