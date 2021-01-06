package com.webproject.rest.resources.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class FeatureOptionsListResource extends ResourceSupport{
	
	List<FeatureOptionsResource> fOptionsRes = new ArrayList<>();

	public List<FeatureOptionsResource> getfOptionsRes() {
		return fOptionsRes;
	}
	public void setfOptionsRes(List<FeatureOptionsResource> fOptionsRes) {
		this.fOptionsRes = fOptionsRes;
	}
}
