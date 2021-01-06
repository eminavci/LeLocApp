package com.webproject.rest.resources.asm.category;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.webproject.core.services.util.CategoryList;
import com.webproject.rest.mvc.category.CategoryController;
import com.webproject.rest.resources.category.CategoryListResource;

public class CategoryListResourceAsm extends ResourceAssemblerSupport<CategoryList, CategoryListResource>{

	public CategoryListResourceAsm() {
		super(CategoryController.class, CategoryListResource.class);
	}

	@Override
	public CategoryListResource toResource(CategoryList catList) {
		CategoryListResource catListRes = new CategoryListResource();
		catListRes.setCategoryRes(new CategoryResourceAsm().toResources(catList.getCategories()));
		return catListRes;
	}

	
}
