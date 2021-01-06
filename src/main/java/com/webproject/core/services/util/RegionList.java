package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.Region;

public class RegionList {

	private List<Region> regions = new ArrayList<>();

	public RegionList(List<Region> regions) {
		super();
		this.regions = regions == null ? new ArrayList<Region>() : regions;
	}
	public List<Region> getRegions() {
		return regions;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
}
