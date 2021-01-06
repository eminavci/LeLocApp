package com.webproject.rest.resources.user;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.User;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource extends ResourceSupport{
	private Long oid;
	private String firstName;
	private String middleName;
	private String lastName;
	private boolean isActive;
	private int gender;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public User toUser(){
		User user = new User();
		user.setFirstName(this.getFirstName());
		user.setLastName(this.getLastName());
		user.setMiddleName(this.getMiddleName());
		user.setActive(this.isActive);
		user.setGender(this.getGender());
		return user;
	}
}
