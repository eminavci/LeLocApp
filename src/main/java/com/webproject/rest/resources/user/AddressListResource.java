package com.webproject.rest.resources.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class AddressListResource extends ResourceSupport{
	
	private List<AddressResource> addressListRes = new ArrayList<AddressResource>();

	public List<AddressResource> getAddressListRes() {
		return addressListRes;
	}
	public void setAddressListRes(List<AddressResource> addressListRes) {
		this.addressListRes = addressListRes;
	}
}
