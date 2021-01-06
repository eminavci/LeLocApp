package com.webproject.rest.resources.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class SubCategoryListResource extends ResourceSupport{
	
	private List<SubCategoryResource> subCategoryListRes = new  ArrayList<>();

	public List<SubCategoryResource> getSubCategoryListRes() {
		return subCategoryListRes;
	}
	public void setSubCategoryListRes(List<SubCategoryResource> subCategoryListRes) {
		this.subCategoryListRes = subCategoryListRes;
	}
}
