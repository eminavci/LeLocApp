package com.webproject.rest.resources.realestate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class ImageListResource extends ResourceSupport{

	private List<ImageResource> imgListRes = new ArrayList<ImageResource>();

	public List<ImageResource> getImgListRes() {
		return imgListRes;
	}
	public void setImgListRes(List<ImageResource> imgListRes) {
		this.imgListRes = imgListRes;
	}
}
