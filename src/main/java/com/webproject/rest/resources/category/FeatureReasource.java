package com.webproject.rest.resources.category;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.common.DetailType;
import com.webproject.core.models.entities.Feature;
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureReasource extends ResourceSupport{
	
	private String name;// default in English
	private String msgCode;
	private boolean isActive;
	private Long oid;
	private DetailType type;
	private int showOrder;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public DetailType getType() {
		return type;
	}
	public void setType(DetailType type) {
		this.type = type;
	}
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	
	public Feature toFeature(){
		Feature f = new Feature();
		f.setActive(this.isActive());
		f.setMsgCode(this.getMsgCode());
		f.setName(this.getName());
		f.setType(this.getType());
		f.setShowOrder(this.getShowOrder());
		return f;
	}
}
