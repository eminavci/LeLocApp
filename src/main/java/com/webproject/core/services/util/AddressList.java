package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.Address;

public class AddressList {

	private List<Address> addresses = new ArrayList<Address>();

	public AddressList(List<Address> addresses) {
		super();
		this.addresses = (addresses == null) ? new ArrayList<Address>() : addresses;
	}

	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
