package com.webproject.rest.resources.place;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class CountryListResource extends ResourceSupport{

	List<CountryResource> countries = new ArrayList<>();

	public List<CountryResource> getCountries() {
		return countries;
	}
	public void setCountries(List<CountryResource> countries) {
		this.countries = countries;
	}
}
