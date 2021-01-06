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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "OPE_TYPE", uniqueConstraints=@UniqueConstraint(columnNames = {"category", "name"}))
public class CatOpeType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 26)
	private String name;
	@Column(nullable = false, length = 50)
	private String msgCode;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "catOpeType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SubCategory> subCategories = new ArrayList<SubCategory>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable
	(
	    name="CAT_OPE_TYPE_FEATURES",
	    joinColumns={ @JoinColumn(name="cat_ope_type_id", referencedColumnName="id") },
	    inverseJoinColumns={ @JoinColumn(name="detail_title_id", referencedColumnName="id")},
	    uniqueConstraints=@UniqueConstraint(columnNames={"cat_ope_type_id","detail_title_id"})
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
	@JsonBackReference
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@JsonManagedReference
	public List<SubCategory> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
	public Set<DetailTitle> getDetailTitles() {
		return detailTitles;
	}
	public void setDetailTitles(Set<DetailTitle> detailTitles) {
		this.detailTitles = detailTitles;
	}
}
