package com.webproject.rest.resources.asm.category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.models.entities.FeatureOptions;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.FeatureOptionsResource;

public class FeatureOptionsResourceAsm  extends ResourceAssemblerSupport<FeatureOptions, FeatureOptionsResource>{

	public FeatureOptionsResourceAsm() {
		super(SubCategoryController.class, FeatureOptionsResource.class);
	}

	@Override
	public FeatureOptionsResource toResource(FeatureOptions fo) {
//		FeatureOptionsResource featureOpeitonsRes = new FeatureOptionsResource();
//		featureOpeitonsRes.setMsgCode(fo.getMsgCode());
//		featureOpeitonsRes.setName(fo.getName());
		
		FeatureOptionsResource featureOpeitonsRes = instantiateResource(fo);
		featureOpeitonsRes.setMsgCode(fo.getMsgCode());
		featureOpeitonsRes.setName(fo.getName());
		featureOpeitonsRes.setOid(fo.getId());
		
		featureOpeitonsRes.add(linkTo(methodOn(SubCategoryController.class).findFeatureOptionsById(fo.getId())).withSelfRel());
		featureOpeitonsRes.add(linkTo(methodOn(SubCategoryController.class).findFeatureById(fo.getFeature().getId())).withRel("feature"));
		
		return featureOpeitonsRes;
	}

}
