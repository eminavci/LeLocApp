package com.webproject.rest.resources.asm.category;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.SubCategoryList;
import com.webproject.rest.mvc.category.SubCategoryController;
import com.webproject.rest.resources.category.SubCategoryListResource;

public class SubCategoryListResourceAsm extends ResourceAssemblerSupport<SubCategoryList, SubCategoryListResource>{

	public SubCategoryListResourceAsm() {
		super(SubCategoryController.class, SubCategoryListResource.class);
	}

	@Override
	public SubCategoryListResource toResource(SubCategoryList entity) {
		SubCategoryListResource subCategoryListRes = instantiateResource(entity);
		subCategoryListRes.setSubCategoryListRes(new SubCategoryResourceAsm().toResources(entity.getSubCategories()));
		return subCategoryListRes;
	}
}
