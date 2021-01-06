package com.webproject.core.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "DETAIL_DETAIL", uniqueConstraints=@UniqueConstraint(columnNames = {"detail_title", "name", "isActive", "showOrder"}))
public class DetailDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 72)
	private String name;// Default in english
	@Column(nullable = false, length = 50)
	private String msgCode;
	@Column(nullable = false)
	private boolean isActive;
	@Column(nullable = false)
	private int showOrder;
	
	@ManyToOne
	private DetailTitle detailTitle;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	@JsonBackReference
	public DetailTitle getDetailTitle() {
		return detailTitle;
	}
	public void setDetailTitle(DetailTitle detailTitle) {
		this.detailTitle = detailTitle;
	}
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
}
