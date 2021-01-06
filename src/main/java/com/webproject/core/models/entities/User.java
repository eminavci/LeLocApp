package com.webproject.core.models.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column( nullable = false, length = 48)
	private String firstName;
	@Column(length = 48)
	private String middleName;
	@Column( nullable = false, length = 48)
	private String lastName;
	private boolean isActive;
	private int gender;
	//private int gender;
	//private Date birthDate;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Address> addresses = new ArrayList<Address>();
	
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Account account;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Phone> phone = new ArrayList<Phone>();	
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private UserHistory history;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@Fetch(value = FetchMode.SUBSELECT) // To have eagerly fetch mutiple list in an entity ad this
	private List<RealEstate> realEstates = new ArrayList<RealEstate>();
	
	@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<Message>();
	
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Corporative company;
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "USER_FAVORITE_LIST",
//			joinColumns=@JoinColumn(name = "USER_ID"),
//			inverseJoinColumns=@JoinColumn(name = "REAL_ESTATE_ID"))
//	private List<RealEstate> favoriteList;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserWatchList> watchList = new ArrayList<UserWatchList>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@JsonManagedReference
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	@JsonManagedReference
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@JsonManagedReference
	public List<Phone> getPhone() {
		return phone;
	}
	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}
	@JsonManagedReference
	public UserHistory getHistory() {
		return history;
	}
	public void setHistory(UserHistory history) {
		this.history = history;
	}
	@JsonManagedReference
	public List<RealEstate> getRealEstates() {
		return realEstates;
	}
	public void setRealEstates(List<RealEstate> realEstates) {
		this.realEstates = realEstates;
	}
	@JsonManagedReference
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	@JsonManagedReference
	public List<UserWatchList> getWatchList() {
		return watchList;
	}
	public void setWatchList(List<UserWatchList> watchList) {
		this.watchList = watchList;
	}
	@JsonManagedReference
	public Corporative getCompany() {
		return company;
	}
	public void setCompany(Corporative company) {
		this.company = company;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
}
