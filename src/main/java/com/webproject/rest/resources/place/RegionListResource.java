package com.webproject.rest.resources.place;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class RegionListResource extends ResourceSupport{

	private List<RegionResource> regions = new ArrayList<>();

	public List<RegionResource> getRegions() {
		return regions;
	}
	public void setRegions(List<RegionResource> regions) {
		this.regions = regions;
	}
	
}
