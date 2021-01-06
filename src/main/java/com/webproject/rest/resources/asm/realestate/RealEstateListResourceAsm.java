package com.webproject.rest.resources.asm.realestate;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.RealEstateList;
import com.webproject.rest.mvc.realestate.RealEstateController;
import com.webproject.rest.resources.realestate.RealEstateListResource;

public class RealEstateListResourceAsm extends ResourceAssemblerSupport<RealEstateList, RealEstateListResource>{

	public RealEstateListResourceAsm() {
		super(RealEstateController.class, RealEstateListResource.class);
	}

	@Override
	public RealEstateListResource toResource(RealEstateList entity) {
		RealEstateListResource realEstateListRes = instantiateResource(entity);
		realEstateListRes.setRealEstateListRes(new RealEstateResourceAsm().toResources(entity.getRealEstateList()));
		
		return realEstateListRes;
	}

}
