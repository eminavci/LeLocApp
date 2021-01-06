package com.webproject.core.models.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "SUB_CATEGORY", uniqueConstraints=@UniqueConstraint(columnNames = {"cat_ope_type", "name", "isActive"}))
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 50)
	private String msgCode;
	@Column(nullable = false, length = 72)
	private String name; //Default in English
	private boolean isActive;
	
	@Transient
	public int[] opeids;
	
	@ManyToOne
	private CatOpeType catOpeType;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable
	(
	    name="SUBCATEGORY_FEATURE",
	    joinColumns={ @JoinColumn(name="subcategory_id", referencedColumnName="id") },
	    inverseJoinColumns={ @JoinColumn(name="feature_id", referencedColumnName="id")},
	    uniqueConstraints=@UniqueConstraint(columnNames={"subcategory_id","feature_id"})
	)
	private Set<Feature> features = new HashSet<Feature>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable
	(
	    name="SUBCATEGORY_DETAIL_TITLE",
	    joinColumns={ @JoinColumn(name="subcategory_id", referencedColumnName="id") },
	    inverseJoinColumns={ @JoinColumn(name="detail_title_id", referencedColumnName="id")},
	    uniqueConstraints=@UniqueConstraint(columnNames={"subcategory_id","detail_title_id"})
	)
	private Set<DetailTitle> detailTitles = new HashSet<DetailTitle>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public CatOpeType getCatOpeType() {
		return catOpeType;
	}
	public void setCatOpeType(CatOpeType catOpeType) {
		this.catOpeType = catOpeType;
	}
	public Set<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}
	public Set<DetailTitle> getDetailTitles() {
		return detailTitles;
	}
	public void setDetailTitles(Set<DetailTitle> detailTitles) {
		this.detailTitles = detailTitles;
	}
}
