package com.webproject.rest.resources.user;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Phone;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneResource extends ResourceSupport{
	
	private String areaCode;
	private String number;
	private int type;// 0:Mobile, 1: home, 2: work
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public Phone toPhone(){
		Phone phone = new Phone();
		phone.setAreaCode(this.getAreaCode());
		phone.setNumber(this.getNumber());
		phone.setType(this.getType());
		
		return phone;
	}
}
