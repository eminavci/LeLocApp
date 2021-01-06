package com.webproject.rest.resources.asm.place;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.RegionList;
import com.webproject.rest.mvc.place.RegionController;
import com.webproject.rest.resources.place.RegionListResource;
import com.webproject.rest.resources.place.RegionResource;

public class RegionListResourceAsm extends ResourceAssemblerSupport<RegionList, RegionListResource>{

	public RegionListResourceAsm() {
		super(RegionController.class, RegionListResource.class);
	}

	@Override
	public RegionListResource toResource(RegionList region) {
		RegionListResource regionListRest = instantiateResource(region);
		regionListRest.setRegions(new RegionResourceAsm().toResources(region.getRegions()));
		return regionListRest;
	}

}
