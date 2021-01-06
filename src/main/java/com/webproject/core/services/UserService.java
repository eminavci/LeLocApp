package com.webproject.core.services;

import com.webproject.core.models.entities.Account;
import com.webproject.core.models.entities.Address;
import com.webproject.core.models.entities.Corporative;
import com.webproject.core.models.entities.User;
import com.webproject.core.services.exceptions.LeLocAppCoreException;
import com.webproject.core.services.util.AddressList;
import com.webproject.spring.security.model.AppRole.RoleEnum;

public interface UserService {

	public User findUserById(Long id) throws LeLocAppCoreException;
	public User findUserByEmailAddress(String email) throws LeLocAppCoreException;
	public User findUserByEmailAndPassword(String email, String password) throws LeLocAppCoreException;
	public User saveUser(User user);
	
	public Account findAccountById(Long id);
	public Account saveAccount(Long userId, Account account);
	public Account findAccountByUserId(Long userId);
	public Account findAccountByEmail(String email);
	
	public Address findAddressById(Long addressId);
	public AddressList findAddressByUserId(Long userId);
	public Address saveUserAddress(Long userId, Long cityId, Address address);
	
	public Corporative saveCompany(Long userId, Corporative company);
	public Corporative saveCompanyAddress(Long companyId, Long cityId, Address address);
	public Corporative findCompanyById(Long companyId);
	public Corporative findCompanyByUserId(Long userId);
	
	
	public boolean isCurrentUserLoggedIn();
	public Account getLoggedInAccount();
	public RoleEnum getAccountRole();

}
