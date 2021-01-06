package com.webproject.rest.resources.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class FeatureListResource extends ResourceSupport{
	
	private List<FeatureReasource> featureListRes = new ArrayList<>();

	public List<FeatureReasource> getFeatureListRes() {
		return featureListRes;
	}
	public void setFeatureListRes(List<FeatureReasource> featureListRes) {
		this.featureListRes = featureListRes;
	}
}
