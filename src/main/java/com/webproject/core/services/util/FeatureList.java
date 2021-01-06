package com.webproject.core.services.util;

import java.util.HashSet;
import java.util.Set;

import com.webproject.core.models.entities.Feature;

public class FeatureList {

	private Set<Feature> features = new HashSet<>();

	public FeatureList(Set<Feature> features) {
		super();
		this.features = features == null ? new HashSet<>() : features;
	}

	public Set<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
}
