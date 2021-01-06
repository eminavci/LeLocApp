package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.CatOpeType;

public class CatOpeTypeList {

	private List<CatOpeType> catOpeTypes = new ArrayList<CatOpeType>();

	public CatOpeTypeList(List<CatOpeType> catOpeTypes) {
		super();
		this.catOpeTypes = catOpeTypes;
	}

	public List<CatOpeType> getCatOpeTypes() {
		return catOpeTypes;
	}
	public void setCatOpeTypes(List<CatOpeType> catOpeTypes) {
		this.catOpeTypes = catOpeTypes;
	}
}
