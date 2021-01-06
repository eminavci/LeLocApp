package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.Country;

public class CountryList {

	private List<Country> countries = new ArrayList<>();

	
	public CountryList(List<Country> countries) {
		super();
		this.countries = countries == null ? new ArrayList<Country>() : countries;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}
