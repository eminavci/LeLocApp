package com.webproject.rest.resources.asm.place;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.CityList;
import com.webproject.rest.mvc.place.CityController;
import com.webproject.rest.resources.place.CityListResource;

public class CityListResourceAsm extends ResourceAssemblerSupport<CityList, CityListResource>{

	public CityListResourceAsm() {
		super(CityController.class, CityListResource.class);
	}

	@Override
	public CityListResource toResource(CityList entity) {
		CityListResource cityListRes = new CityListResource();
		cityListRes.setCities(new CityResourceAsm().toResources(entity.getCities()));
		return cityListRes;
	}

}
