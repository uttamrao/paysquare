package com.ps.dto;

public class EmployeeAddressDTO extends AbstractDTO  {

		private String address;
		private String city;
		private String state;
		private String pincode;
		private EnumDTO type;
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public EnumDTO getType() {
			return type;
		}
		public void setType(EnumDTO type) {
			this.type = type;
		}
		
}
