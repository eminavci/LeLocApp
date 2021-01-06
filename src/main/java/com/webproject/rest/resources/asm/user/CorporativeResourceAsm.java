package com.webproject.rest.resources.asm.user;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.Corporative;
import com.webproject.rest.mvc.user.UserController;
import com.webproject.rest.resources.user.CorporativeResource;

public class CorporativeResourceAsm extends ResourceAssemblerSupport<Corporative, CorporativeResource>{

	public CorporativeResourceAsm() {
		super(UserController.class, CorporativeResource.class);
	}

	@Override
	public CorporativeResource toResource(Corporative corp) {
		CorporativeResource corpRes = new CorporativeResource();
		corpRes.setOfficialName(corp.getOfficialName());
		corpRes.setShortName(corp.getShortName());
		corpRes.setWebSite(corp.getWebSite());
		corpRes.setType(corp.getType());
		corpRes.setOid(corp.getId());
		
		corpRes.add(linkTo(methodOn(UserController.class).findCompanyById(corp.getId())).withSelfRel());
		corpRes.add(linkTo(methodOn(UserController.class).findUserById(corp.getUser().getId())).withRel("user"));
		if(corp.getAddress() != null)
			corpRes.add(linkTo(methodOn(UserController.class).findAddressById(corp.getAddress().getId())).withRel("address"));
		
		return corpRes;
	}

}
