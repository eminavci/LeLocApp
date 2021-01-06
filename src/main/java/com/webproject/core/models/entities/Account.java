package com.webproject.core.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webproject.spring.security.model.AppRole;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints=@UniqueConstraint(columnNames = {"email", "regType"}))
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private int regType; // 0: Email account, 1: Facebook
	@Column(nullable = false, length=48)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private int type; // 0: individual, 1: corporative
	
	@OneToOne
	private User user;
	
	@Column(nullable = false, length=16)
	@Enumerated(EnumType.STRING)
	private AppRole.RoleEnum role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRegType() {
		return regType;
	}
	public void setRegType(int regType) {
		this.regType = regType;
	}
	public AppRole.RoleEnum getRole() {
		return role;
	}
	public void setRole(AppRole.RoleEnum role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", regType=" + regType + ", email=" + email + ", password=" + password + ", type="
				+ type + ", user=" + user + ", role=" + role + "]";
	}
	
	
}
