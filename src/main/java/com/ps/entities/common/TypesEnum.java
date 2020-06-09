package com.ps.entities.common;

public class TypesEnum {

	public enum AddressType{
		PERMANENT("Permanent Address"),
		CURRENT("Current Address");
		
		private String value;

		AddressType(String value){
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
			
			for (AddressType address: AddressType.values()) {
				
				if(address.name().equals(name)) {
					isValid = true;
					break;
				}
			}			
			return isValid;
		}
	}
	
}
