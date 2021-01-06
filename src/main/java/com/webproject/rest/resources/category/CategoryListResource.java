package com.webproject.rest.resources.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class CategoryListResource extends ResourceSupport{
	
	private List<CategoryResource> categoryRes = new ArrayList<>();

	public List<CategoryResource> getCategoryRes() {
		return categoryRes;
	}

	public void setCategoryRes(List<CategoryResource> categoryRes) {
		this.categoryRes = categoryRes;
	}
}
