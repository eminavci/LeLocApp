package com.webproject.rest.resources.category;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.common.DetailType;
import com.webproject.core.models.entities.DetailTitle;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailTitleResource extends ResourceSupport{
	private Long oid;
	private String name; // Default in English	
	private String msgCode;
	private boolean isActive;
	private int showOrder;
	private DetailType type;
	private DetailType typeForAd;
	private boolean isForAd; // can be used when posting an realestate Ad
	
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
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	public DetailType getType() {
		return type;
	}
	public void setType(DetailType type) {
		this.type = type;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public boolean isForAd() {
		return isForAd;
	}
	public void setForAd(boolean isForAd) {
		this.isForAd = isForAd;
	}
	public DetailType getTypeForAd() {
		return typeForAd;
	}
	public void setTypeForAd(DetailType typeForAd) {
		this.typeForAd = typeForAd;
	}
	public DetailTitle toDetailTitle(){
		DetailTitle dt = new DetailTitle();
		dt.setActive(this.isActive());
		dt.setMsgCode(this.getMsgCode());
		dt.setName(this.getName());
		dt.setShowOrder(this.getShowOrder());
		dt.setType(this.getType());
		dt.setForAd(this.isForAd());
		dt.setTypeForAd(this.getTypeForAd());
		return dt;
	}
	
}
