package com.webproject.rest.resources.place;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class TownListResource extends ResourceSupport{

	private List<TownResource> towns = new ArrayList<TownResource>();

	public List<TownResource> getTowns() {
		return towns;
	}

	public void setTowns(List<TownResource> towns) {
		this.towns = towns;
	}

	
}
