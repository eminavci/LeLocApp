package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.Town;

public class TownList {

	private List<Town> towns = new ArrayList<>();

	public TownList(List<Town> towns) {
		super();
		this.towns = towns == null ? new ArrayList<Town>() : towns;
	}
	public List<Town> getTowns() {
		return towns;
	}

	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}
	
	
}
