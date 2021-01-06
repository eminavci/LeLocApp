package com.webproject.rest.resources.place;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class CityListResource extends ResourceSupport{

	private List<CityResource> cities = new ArrayList<CityResource>();

	public List<CityResource> getCities() {
		return cities;
	}
	public void setCities(List<CityResource> cities) {
		this.cities = cities;
	}
}
