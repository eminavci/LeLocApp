package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.Feature;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.FeatureReasource;

public class FeatureReasourceAsm extends ResourceAssemblerSupport<Feature, FeatureReasource>{

	public FeatureReasourceAsm() {
		super(SubCategoryController.class, FeatureReasource.class);
	}

	@Override
	public FeatureReasource toResource(Feature feature) {
		
		FeatureReasource featureRes = new FeatureReasource();
		featureRes.setActive(feature.isActive());
		featureRes.setMsgCode(feature.getMsgCode());
		featureRes.setName(feature.getName());
		featureRes.setOid(feature.getId());
		featureRes.setType(feature.getType());
		featureRes.setShowOrder(feature.getShowOrder());
		
		featureRes.add(linkTo(methodOn(SubCategoryController.class).findFeatureById(feature.getId())).withSelfRel());
		featureRes.add(linkTo(methodOn(SubCategoryController.class).findOptionsOfFeature(feature.getId())).withRel("featureoptions"));
		
		return featureRes;
	}

}
