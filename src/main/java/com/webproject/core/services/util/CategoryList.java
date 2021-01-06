package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.Category;

public class CategoryList {

	private List<Category> categories = new ArrayList<Category>();
	
	public CategoryList(List<Category> categories) {
		super();
		this.categories = categories == null ? new ArrayList<Category>() : categories;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
