package com.webproject.rest.resources.asm.place;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.CountryList;
import com.webproject.rest.mvc.place.CountryController;
import com.webproject.rest.resources.place.CountryListResource;

public class CountryListResourceAsm extends ResourceAssemblerSupport<CountryList, CountryListResource>{

	public CountryListResourceAsm() {
		super(CountryController.class, CountryListResource.class);
	}

	@Override
	public CountryListResource toResource(CountryList countryList) {
		CountryListResource countryListRes = new CountryListResource();
		countryListRes.setCountries(new CountryResourceAsm().toResources(countryList.getCountries()));
		return countryListRes;
	}

}

