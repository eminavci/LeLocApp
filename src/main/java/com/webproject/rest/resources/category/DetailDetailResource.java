package com.webproject.rest.resources.category;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webproject.core.models.entities.DetailDetail;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailDetailResource extends ResourceSupport {
	private Long oid;
	private String name;// Default in english
	private String msgCode;
	private boolean isActive;
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
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public DetailDetail toDetailDetail() {
		DetailDetail dd = new DetailDetail();
		dd.setActive(this.isActive());
		dd.setMsgCode(this.getMsgCode());
		dd.setName(this.getName());
		dd.setShowOrder(this.getShowOrder());
		return dd;
	}
}
