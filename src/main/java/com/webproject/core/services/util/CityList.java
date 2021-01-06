package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.City;

public class CityList {

	private List<City> cities = new ArrayList<City>();
	
	public CityList(List<City> cities) {
		super();
		this.cities = cities == null ? new ArrayList<City>() : cities;
	}
	public List<City> getCities() {
		return cities;
	}
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}
