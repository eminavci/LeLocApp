package com.webproject.rest.resources.asm.user;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.Phone;
import com.webproject.rest.mvc.user.UserController;
import com.webproject.rest.resources.user.PhoneResource;

public class PhoneResourceAsm extends ResourceAssemblerSupport<Phone, PhoneResource>{

	public PhoneResourceAsm() {
		super(UserController.class, PhoneResource.class);
	}

	@Override
	public PhoneResource toResource(Phone phone) {
		PhoneResource phoneRes = new PhoneResource();
		phoneRes.setAreaCode(phone.getAreaCode());
		phoneRes.setNumber(phone.getNumber());
		phoneRes.setType(phone.getType());
		
		return phoneRes;
	}

}
