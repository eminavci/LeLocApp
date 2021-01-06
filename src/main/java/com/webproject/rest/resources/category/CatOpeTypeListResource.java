package com.webproject.rest.resources.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class CatOpeTypeListResource extends ResourceSupport{
	
	private List<CatOpeTypeResource> catOpeTypeListRes = new ArrayList<>();

	public List<CatOpeTypeResource> getCatOpeTypeListRes() {
		return catOpeTypeListRes;
	}
	public void setCatOpeTypeListRes(List<CatOpeTypeResource> catOpeTypeListRes) {
		this.catOpeTypeListRes = catOpeTypeListRes;
	}
}
