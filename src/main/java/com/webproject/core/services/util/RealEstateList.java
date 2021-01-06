package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.RealEstate;

public class RealEstateList {

	private List<RealEstate> realEstateList = new ArrayList<>();

	public RealEstateList(List<RealEstate> realEstateList) {
		super();
		this.realEstateList = realEstateList;
	}

	public List<RealEstate> getRealEstateList() {
		return realEstateList;
	}

	public void setRealEstateList(List<RealEstate> realEstateList) {
		this.realEstateList = realEstateList;
	}
}
