package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.webproject.core.common.DetailType;

@Entity
@Table(name = "FEATURE")
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 72, unique = true)
	private String name;// default in English
	@Column(nullable = false, length = 50)
	private String msgCode;
	private boolean isActive;
	@Column(nullable = true, length=12)
	@Enumerated(EnumType.STRING)
	private DetailType type;
	private int showOrder;
	
	@OneToMany(mappedBy = "feature", cascade = CascadeType.ALL)
	private List<FeatureOptions> featureOptions = new ArrayList<FeatureOptions>();
	

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
	public List<FeatureOptions> getFeatureOptions() {
		return featureOptions;
	}
	public void setFeatureOptions(List<FeatureOptions> featureOptions) {
		this.featureOptions = featureOptions;
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
}
