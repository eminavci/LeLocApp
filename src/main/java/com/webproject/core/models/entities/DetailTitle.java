package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.webproject.core.common.DetailType;

@Entity
@Table(name = "DETAIL_TITLE", uniqueConstraints=@UniqueConstraint(columnNames = {"name", "isActive", "showOrder"}))
public class DetailTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 72)
	private String name; // Default in English
	@Column(nullable = false, length = 50)	
	private String msgCode;
	@Column(nullable = false)
	private boolean isActive;
	@Column(nullable = false)
	private int showOrder;
	private boolean isForAd; // can be used when posting an realestate Ad
	
	@OneToMany(mappedBy = "detailTitle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<DetailDetail> detailDetail = new ArrayList<DetailDetail>();
	
	@Column(nullable = false, length=12)
	@Enumerated(EnumType.STRING)
	private DetailType type;

	@Column(nullable = false, length=12)
	@Enumerated(EnumType.STRING)
	private DetailType typeForAd;
	
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
	public int getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}
	@JsonManagedReference
	public List<DetailDetail> getDetailDetail() {
		return detailDetail;
	}
	public void setDetailDetail(List<DetailDetail> detailDetail) {
		this.detailDetail = detailDetail;
	}
	public DetailType getType() {
		return type;
	}
	public void setType(DetailType type) {
		this.type = type;
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
	
}
