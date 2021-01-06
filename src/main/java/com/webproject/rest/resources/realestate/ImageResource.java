package com.webproject.rest.resources.realestate;

import org.springframework.hateoas.ResourceSupport;

import com.webproject.core.models.entities.Image;

public class ImageResource extends ResourceSupport{

	private Long oid;
	private String name;
	private String thumb;
	private boolean isActive;
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getName() {
		return "http://localhost:8080/static/adimages/" + name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public Image toImage(){
		Image i = new Image();
		i.setActive(this.isActive());
		i.setName(this.getName());
		i.setThumb(this.getThumb());
		
		return i;
	}
	
}
