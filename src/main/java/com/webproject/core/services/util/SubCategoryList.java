package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.SubCategory;

public class SubCategoryList {

	private List<SubCategory> subCategories = new ArrayList<SubCategory>();

	public SubCategoryList(List<SubCategory> subCategories) {
		super();
		this.subCategories = subCategories == null ? new ArrayList<SubCategory>() : subCategories;
	}

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
}
