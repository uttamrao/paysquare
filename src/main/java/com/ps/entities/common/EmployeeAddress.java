package com.ps.entities.common;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ps.entities.common.TypesEnum.AddressType;

@Entity
public class EmployeeAddress extends AbstractEntity  {

	@ManyToOne
	@JoinColumn(name="employeeId",referencedColumnName = "id")
    private EmployeeDetails employee;
	@ManyToOne
	@JoinColumn(name="employeePersonalInformationId",referencedColumnName = "id")
    private EmployeePersonalInformation employeePersonalInformation;
	private String address;
	private String city;
	private String state;
	private String pincode;
	 
	@Enumerated(EnumType.STRING)
	private AddressType type;
	
	public EmployeeDetails getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDetails employee) {
		this.employee = employee;
	}
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
	public AddressType getType() {
		return type;
	}
	public void setType(AddressType type) {
		this.type = type;
	}
	
}
