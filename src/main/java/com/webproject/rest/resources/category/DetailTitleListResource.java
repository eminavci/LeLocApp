package com.webproject.rest.resources.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class DetailTitleListResource extends ResourceSupport{
	
	List<DetailTitleResource> detailTitleListRes = new ArrayList<>();

	public List<DetailTitleResource> getDetailTitleListRes() {
		return detailTitleListRes;
	}
	public void setDetailTitleListRes(List<DetailTitleResource> detailTitleListRes) {
		this.detailTitleListRes = detailTitleListRes;
	}
}
