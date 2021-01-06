package com.webproject.rest.resources.asm.user;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.AddressList;
import com.webproject.rest.mvc.user.UserController;
import com.webproject.rest.resources.user.AddressListResource;

public class AddressListResourceAsm extends ResourceAssemblerSupport<AddressList, AddressListResource>{

	public AddressListResourceAsm() {
		super(UserController.class, AddressListResource.class);
	}

	@Override
	public AddressListResource toResource(AddressList addressList) {
		AddressListResource adrListRes = new AddressListResource();
		adrListRes.setAddressListRes(new AddressResourceAsm().toResources(addressList.getAddresses()));
		return adrListRes;
	}

}
