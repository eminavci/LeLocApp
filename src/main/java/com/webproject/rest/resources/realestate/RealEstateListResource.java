package com.webproject.rest.resources.realestate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class RealEstateListResource extends ResourceSupport{

	private List<RealEstateResource> realEstateListRes = new ArrayList<>();

	public List<RealEstateResource> getRealEstateListRes() {
		return realEstateListRes;
	}

	public void setRealEstateListRes(List<RealEstateResource> realEstateListRes) {
		this.realEstateListRes = realEstateListRes;
	}
	
	
}
