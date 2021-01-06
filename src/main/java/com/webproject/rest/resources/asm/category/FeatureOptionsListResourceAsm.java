package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.FeatureOptionsList;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.FeatureOptionsListResource;

public class FeatureOptionsListResourceAsm extends ResourceAssemblerSupport<FeatureOptionsList, FeatureOptionsListResource>{

	public FeatureOptionsListResourceAsm() {
		super(SubCategoryController.class, FeatureOptionsListResource.class);
	}

	@Override
	public FeatureOptionsListResource toResource(FeatureOptionsList foList) {
		FeatureOptionsListResource featureOptionsListRes = instantiateResource(foList);
		featureOptionsListRes.setfOptionsRes(new FeatureOptionsResourceAsm().toResources(foList.getFeatureOptionList()));
		return featureOptionsListRes;
	}

}
