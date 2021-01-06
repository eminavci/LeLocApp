package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.webproject.core.common.CompanyType;

@Entity
@Table(name = "REAL_ESTATE")
public class RealEstate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column( nullable = false, length = 9)
	private String no;
	@Column( nullable = false, length = 146)
	private String name;
	@Column(precision = 12, scale = 2)
	private double meterSquare;
	
	@Lob
	@Column(length = 900000)
	private String explanation;
	
	@Column(precision = 12, scale = 2)
	private Double price;
	@Column(nullable = false, length = 5)
	private String currency;
	@Column(nullable = false)
	private Date publishDate;
	@Column(length = 2, nullable = false)
	private int status;// 0 - passive by System, 1: in publishment(Active), 2: waiting for moderator acceptance,  3: passive by user, 4: deleted, 5: PROCESS COMPLETED
	private long viewCount;
	
	// ******** This is just used to have fromwho attribute of Realeaste. so that it can be used in search criterias
	@Column(nullable = false, length=20)
	@Enumerated(EnumType.STRING)
	private CompanyType fromWho;
	
	@OneToOne
	private Address address;
	
	@OneToOne
	private SubCategory subCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User owner;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable
	(
	    name="REAL_ESTATE_DETAIL",
	    joinColumns={ @JoinColumn(name="real_estate_id", referencedColumnName="id") },
	    inverseJoinColumns={ @JoinColumn(name="detail_detail_id", referencedColumnName="id")},
	    uniqueConstraints=@UniqueConstraint(columnNames={"real_estate_id","detail_detail_id"})
	)
	private Set<DetailDetail> detailDetails = new HashSet<DetailDetail>(); // a join table will create by default
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable
	(
	    name="REAL_ESTATE_FEATURE",
	    joinColumns={ @JoinColumn(name="real_estate_id", referencedColumnName="id") },
	    inverseJoinColumns={ @JoinColumn(name="feature_option_id", referencedColumnName="id")},
	    uniqueConstraints=@UniqueConstraint(columnNames={"real_estate_id","feature_option_id"})
	)
	private Set<FeatureOptions> features = new HashSet<FeatureOptions>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "realEstate")
	private List<Image> images = new ArrayList<Image>();
	
//	@ManyToMany(mappedBy = "favoriteList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<User> usersHaveInFavoriteList;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "realEstate")
	private List<UserWatchList> watchList = new ArrayList<UserWatchList>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "realEstate")
	private List<RealEstateTransactionHistory> transactions = new ArrayList<RealEstateTransactionHistory>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	public User getOwner() {
		return owner;
	}
	public long getViewCount() {
		return viewCount;
	}
	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Set<DetailDetail> getDetailDetails() {
		return detailDetails;
	}
	public void setDetailDetails(Set<DetailDetail> detailDetails) {
		this.detailDetails = detailDetails;
	}
	public Set<FeatureOptions> getFeatures() {
		return features;
	}
	public void setFeatures(Set<FeatureOptions> features) {
		this.features = features;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public List<UserWatchList> getWatchList() {
		return watchList;
	}
	public void setWatchList(List<UserWatchList> watchList) {
		this.watchList = watchList;
	}
	public List<RealEstateTransactionHistory> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<RealEstateTransactionHistory> transactions) {
		this.transactions = transactions;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public CompanyType getFromWho() {
		return fromWho;
	}
	public void setFromWho(CompanyType fromWho) {
		this.fromWho = fromWho;
	}
	public double getMeterSquare() {
		return meterSquare;
	}
	public void setMeterSquare(double meterSquare) {
		this.meterSquare = meterSquare;
	}
	
	public void addImage(Image image){
		this.images.add(image);
	}
}
