package com.webproject.rest.resources.asm.place;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.Country;
import com.webproject.rest.mvc.place.CountryController;
import com.webproject.rest.mvc.place.RegionController;
import com.webproject.rest.resources.place.CountryResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class CountryResourceAsm extends ResourceAssemblerSupport<Country, CountryResource>{

	public CountryResourceAsm() {
		super(CountryController.class, CountryResource.class);
	}

	@Override
	public CountryResource toResource(Country country) {

		CountryResource countryRes = new CountryResource();
		
		countryRes.setCurrencyCode(country.getCurrencyCode());
		countryRes.setLocaleName(country.getLocaleName());
		countryRes.setName(country.getName());
		countryRes.setPhoneCode(country.getPhoneCode());
		countryRes.setTranslation(country.getTranslation());
		countryRes.setContinentName(country.getContinentName());
		countryRes.setIcon(country.getIcon());
		countryRes.setTimeZone(country.getTimeZone());
		countryRes.setTranslation(country.getTranslation());
		countryRes.setOid(country.getId());
		//countryRes.setRegions(country.getRegions());
		
		countryRes.add(linkTo(methodOn(CountryController.class).findById(country.getId())).withSelfRel());
		countryRes.add(linkTo(methodOn(RegionController.class).findAll()).withRel("regions"));
		
		return countryRes;
	}

}
