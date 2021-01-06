package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.webproject.core.models.entities.FeatureOptions;

public class FeatureOptionsList {

	private List<FeatureOptions> featureOptionList = new ArrayList<FeatureOptions>();

	public FeatureOptionsList(List<FeatureOptions> featureOptionList) {
		super();
		this.featureOptionList = featureOptionList;
	}
	
	public FeatureOptionsList(Set<FeatureOptions> featureOptionSet){
		this.featureOptionList = new ArrayList<FeatureOptions>(featureOptionSet);
	}

	public List<FeatureOptions> getFeatureOptionList() {
		return featureOptionList;
	}
	public void setFeatureOptionList(List<FeatureOptions> featureOptionList) {
		this.featureOptionList = featureOptionList;
	}
}
