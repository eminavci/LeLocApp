package com.webproject.rest.resources.asm.place;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.Town;
import com.webproject.rest.mvc.place.CityController;
import com.webproject.rest.mvc.place.TownController;
import com.webproject.rest.resources.place.TownResource;

public class TownResourceAsm extends ResourceAssemblerSupport<Town, TownResource>{

	public TownResourceAsm() {
		super(TownController.class, TownResource.class);
	}

	@Override
	public TownResource toResource(Town town) {
		
		TownResource townRes = new TownResource();
		townRes.setLatitude(town.getLatitude());
		townRes.setLatitudeMax(town.getLatitudeMax());
		townRes.setLatitudeMin(town.getLatitudeMin());
		townRes.setLongitude(town.getLongitude());
		townRes.setLongitudeMax(town.getLongitudeMax());
		townRes.setLongitudeMin(town.getLongitudeMin());
		townRes.setName(town.getName());
		townRes.setPostCode(town.getPostCode());
		townRes.setOid(town.getId());
		
		townRes.add(linkTo(methodOn(TownController.class).findById(town.getId())).withSelfRel());
		townRes.add(linkTo(methodOn(CityController.class).findById(town.getCity().getId())).withRel("city"));
		
		return townRes;
	}

}
