package com.webproject.rest.resources.user;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;
import com.webproject.core.models.entities.Account;
import com.webproject.spring.security.model.AppRole;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResource extends ResourceSupport{

	private Long oid;
	private int regType; // 0: Email account, 1: Facebook
	private String email;
	private int type; // 0: individual, 1: corporative
	private String password;
	private AppRole.RoleEnum role;
	
	public int getRegType() {
		return regType;
	}
	public void setRegType(int regType) {
		this.regType = regType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public AppRole.RoleEnum getRole() {
		return role;
	}
	public void setRole(AppRole.RoleEnum role) {
		this.role = role;
	}	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account toAccount(){
		Account acc = new Account();
		acc.setEmail(this.getEmail());
		acc.setRegType(this.getRegType());
		acc.setRole(this.getRole());
		acc.setType(this.getType());
		acc.setPassword(this.getPassword());
		
		return acc;
	}
}
