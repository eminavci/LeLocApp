package com.webproject.rest.resources.user;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.Address;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResource extends ResourceSupport{
	
	private String line1;
	private String line2;
	private String postCode;
	private double longitude;
	private double latitude;
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public Address toAddress(){
		Address adr = new Address();
		adr.setLatitude(this.getLatitude());
		adr.setLongitude(this.getLongitude());
		adr.setLine1(this.getLine1());
		adr.setLine2(this.getLine2());
		adr.setPostCode(this.getPostCode());
		
		return adr;
	}
}
