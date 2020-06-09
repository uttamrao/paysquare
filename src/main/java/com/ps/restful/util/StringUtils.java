package com.ps.restful.util;

public class StringUtils {

	public static boolean isValidString(String stringToValidate){
		if(stringToValidate != null && !stringToValidate.isBlank()) {
			return true;
		}
		
		return false;
	}
	
}
