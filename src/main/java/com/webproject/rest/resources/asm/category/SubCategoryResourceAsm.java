package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.webproject.core.models.entities.SubCategory;
import com.webproject.rest.mvc.category.CategoryController;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.SubCategoryResource;

public class SubCategoryResourceAsm extends ResourceAssemblerSupport<SubCategory, SubCategoryResource>{

	public SubCategoryResourceAsm() {
		super(SubCategoryController.class, SubCategoryResource.class);
	}

	@Override
	public SubCategoryResource toResource(SubCategory subCat) {
		SubCategoryResource subCatRes = instantiateResource(subCat);
		
		subCatRes.setActive(subCat.isActive());
		subCatRes.setMsgCode(subCat.getMsgCode());
		subCatRes.setName(subCat.getName());
		subCatRes.setOid(subCat.getId());
		
		subCatRes.add(linkTo(methodOn(SubCategoryController.class).findSubCategoryById(subCat.getId())).withSelfRel());
		subCatRes.add(linkTo(methodOn(CategoryController.class).findCatOpeTypeByd(subCat.getCatOpeType().getId())).withRel("catopetype"));
		subCatRes.add(linkTo(methodOn(SubCategoryController.class).findDetailTitlesOfSubCategory(subCat.getId())).withRel("detailtitles"));
		subCatRes.add(linkTo(methodOn(SubCategoryController.class).findFeaturesOfSubCategory(subCat.getId())).withRel("features"));
		
		return subCatRes;
	}

}
