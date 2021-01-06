package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.FeatureList;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.FeatureListResource;

public class FeatureListResourceAsm extends ResourceAssemblerSupport<FeatureList, FeatureListResource>{

	public FeatureListResourceAsm() {
		super(SubCategoryController.class, FeatureListResource.class);
	}

	@Override
	public FeatureListResource toResource(FeatureList featureList) {
		FeatureListResource featureListRes = instantiateResource(featureList);
		featureListRes.setFeatureListRes(new FeatureReasourceAsm().toResources(featureList.getFeatures()));
		return featureListRes;
	}

}
