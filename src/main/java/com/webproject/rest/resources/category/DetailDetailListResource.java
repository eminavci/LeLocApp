package com.webproject.rest.resources.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class DetailDetailListResource extends ResourceSupport{
	
	private List<DetailDetailResource> detailDetailListRes = new ArrayList<>();

	public List<DetailDetailResource> getDetailDetailListRes() {
		return detailDetailListRes;
	}
	public void setDetailDetailListRes(List<DetailDetailResource> detailDetailListRes) {
		this.detailDetailListRes = detailDetailListRes;
	}
}
