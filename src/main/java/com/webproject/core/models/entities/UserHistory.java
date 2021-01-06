package com.webproject.core.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "USER_HISTORY")
public class UserHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
	
	@Column(nullable = false)
	private Date registrationDate;
	private Date activationDate;
	private Date passiveDate;
	private Date lastLoginDate;
	private Date lastPasswordChangeDate;
	private Date activationCodeExpireDate;
	private String activationCode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@JsonBackReference
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Date getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	public Date getPassiveDate() {
		return passiveDate;
	}
	public void setPassiveDate(Date passiveDate) {
		this.passiveDate = passiveDate;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getLastPasswordChangeDate() {
		return lastPasswordChangeDate;
	}
	public void setLastPasswordChangeDate(Date lastPasswordChangeDate) {
		this.lastPasswordChangeDate = lastPasswordChangeDate;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public Date getActivationCodeExpireDate() {
		return activationCodeExpireDate;
	}
	public void setActivationCodeExpireDate(Date activationCodeExpireDate) {
		this.activationCodeExpireDate = activationCodeExpireDate;
	}
}
