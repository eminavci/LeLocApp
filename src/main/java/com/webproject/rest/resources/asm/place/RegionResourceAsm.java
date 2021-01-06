package com.webproject.rest.resources.asm.place;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.Map;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.Region;
import com.webproject.rest.mvc.place.CityController;
import com.webproject.rest.mvc.place.CountryController;
import com.webproject.rest.mvc.place.RegionController;
import com.webproject.rest.resources.place.RegionResource;

public class RegionResourceAsm  extends ResourceAssemblerSupport<Region, RegionResource>{

	public RegionResourceAsm() {
		super(RegionController.class, RegionResource.class);
	}

	@Override
	public RegionResource toResource(Region region) {
		RegionResource regionRes = new RegionResource();
		regionRes.setName(region.getName());
		regionRes.setOid(region.getId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("hello", "World");
		
		regionRes.add(linkTo(methodOn(RegionController.class).findById(region.getId())).withSelfRel());
		regionRes.add(linkTo(methodOn(CountryController.class).findById(region.getCountry().getId())).withRel("country").expand(map));
		regionRes.add(linkTo(methodOn(CityController.class).findAllByRegionId(region.getId())).withRel("cities"));
		
		return regionRes;
	}

}
