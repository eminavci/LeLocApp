package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CATEGORY", uniqueConstraints=@UniqueConstraint(columnNames = {"name", "isActive"}))
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 72)
	private String name;// default in English
	@Column(nullable = false, length = 50)
	private String msgCode;
	private boolean isActive;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CatOpeType> catOpeType = new ArrayList<CatOpeType>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable
	(
	    name="CATEGORY_FEATURES",
	    joinColumns={ @JoinColumn(name="category_id", referencedColumnName="id") },
	    inverseJoinColumns={ @JoinColumn(name="detai_title_id", referencedColumnName="id")},
	    uniqueConstraints=@UniqueConstraint(columnNames={"category_id","detai_title_id"})
	)
	private Set<DetailTitle> detailTitles = new HashSet<DetailTitle>();// Those are common features amongs this category's subcategory

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
	@JsonManagedReference
	public List<CatOpeType> getCatOpeType() {
		return catOpeType;
	}
	public void setCatOpeType(List<CatOpeType> catOpeType) {
		this.catOpeType = catOpeType;
	}
	public Set<DetailTitle> getDetailTitles() {
		return detailTitles;
	}
	public void setDetailTitles(Set<DetailTitle> detailTitles) {
		this.detailTitles = detailTitles;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
//	@ElementCollection(fetch = FetchType.LAZY)
//    @MapKeyColumn(name="name", length = 50)
//    @Column(name="value", length = 96)
//    @CollectionTable(name="category_translation", joinColumns=@JoinColumn(name="id"))
//	private Map<String, String> translation = new HashMap<>();
	
}
