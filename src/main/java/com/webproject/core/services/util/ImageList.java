package com.webproject.core.services.util;

import java.util.ArrayList;
import java.util.List;

import com.webproject.core.models.entities.Image;

public class ImageList {

	private List<Image> images = new ArrayList<Image>();

	public ImageList(List<Image> images) {
		super();
		this.images = images;
	}

	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
}
