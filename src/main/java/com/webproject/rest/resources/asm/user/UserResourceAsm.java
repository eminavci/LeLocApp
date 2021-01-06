package com.webproject.rest.resources.asm.user;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.User;
import com.webproject.rest.mvc.user.UserController;
import com.webproject.rest.resources.user.UserResource;

public class UserResourceAsm extends ResourceAssemblerSupport<User, UserResource>{

	public UserResourceAsm() {
		super(UserController.class, UserResource.class);
		
	}

	@Override
	public UserResource toResource(User user) {
		UserResource userRes = new UserResource();
		userRes.setActive(user.isActive());
		userRes.setFirstName(user.getFirstName());
		userRes.setLastName(user.getLastName());
		userRes.setMiddleName(user.getMiddleName());
		userRes.setGender(user.getGender());
		userRes.setOid(user.getId());
		
		
		return userRes;
	}

}
