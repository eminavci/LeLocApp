package com.webproject.rest.resources.asm.place;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.webproject.core.models.entities.City;
import com.webproject.rest.mvc.place.CityController;
import com.webproject.rest.mvc.place.RegionController;
import com.webproject.rest.mvc.place.TownController;
import com.webproject.rest.resources.place.CityResource;

public class CityResourceAsm extends ResourceAssemblerSupport<City, CityResource>{

	public CityResourceAsm() {
		super(CityController.class, CityResource.class);
		
	}

	@Override
	public CityResource toResource(City city) {
		CityResource cityRes = new CityResource();
		
		cityRes.setCode(city.getCode());
		cityRes.setLatitude(city.getLatitude());
		cityRes.setLatitudeMax(city.getLatitudeMax());
		cityRes.setLatitudeMin(city.getLatitudeMin());
		cityRes.setLongitude(city.getLongitude());
		cityRes.setLongitudeMax(city.getLongitudeMax());
		cityRes.setLongitudeMin(city.getLongitudeMin());
		cityRes.setName(city.getName());
		cityRes.setOid(city.getId());
		
		cityRes.add(linkTo(methodOn(CityController.class).findById(city.getId())).withSelfRel());
		
		cityRes.add(linkTo(methodOn(RegionController.class).findById(city.getRegion().getId())).withRel("region"));
		
		cityRes.add(linkTo(methodOn(TownController.class).findAllByCity(city.getId())).withRel("towns"));
		
		return cityRes;
	}

}
