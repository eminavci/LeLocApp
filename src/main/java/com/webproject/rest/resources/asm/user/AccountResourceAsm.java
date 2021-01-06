package com.webproject.rest.resources.asm.user;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.Account;
import com.webproject.rest.mvc.user.UserController;
import com.webproject.rest.resources.user.AccountResource;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource>{

	public AccountResourceAsm() {
		super(UserController.class, AccountResource.class);
		
	}

	@Override
	public AccountResource toResource(Account account) {
		AccountResource accRes = new AccountResource();
		accRes.setEmail(account.getEmail());
		accRes.setRegType(account.getRegType());
		accRes.setRole(account.getRole());
		accRes.setType(account.getType());
		accRes.setOid(account.getId());
		accRes.setPassword(account.getPassword());
		
		accRes.add(linkTo(methodOn(UserController.class).findAccountById(account.getId())).withSelfRel());
		accRes.add(linkTo(methodOn(UserController.class).findUserById(account.getUser().getId())).withRel("user"));
		
		return accRes;
	}

}
