package com.webproject.rest.resources.asm.place;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.webproject.core.services.util.TownList;
import com.webproject.rest.mvc.place.TownController;
import com.webproject.rest.resources.place.TownListResource;

public class TownListResourceAsm   extends ResourceAssemblerSupport<TownList, TownListResource>{

	public TownListResourceAsm() {
		super(TownController.class, TownListResource.class);
	}

	@Override
	public TownListResource toResource(TownList entity) {
		TownListResource townListRes = new TownListResource();
		townListRes.setTowns(new TownResourceAsm().toResources(entity.getTowns()));
		return townListRes;
	}

}
