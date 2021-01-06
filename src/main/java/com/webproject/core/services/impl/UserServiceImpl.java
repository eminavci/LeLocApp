package com.webproject.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webproject.core.common.LeLocAppCrypto;
import com.webproject.core.common.api.Const;
import com.webproject.core.common.api.ErrKeys;
import com.webproject.core.models.entities.Account;
import com.webproject.core.models.entities.Address;
import com.webproject.core.models.entities.City;
import com.webproject.core.models.entities.Corporative;
import com.webproject.core.models.entities.User;
import com.webproject.core.repositories.AccountRepo;
import com.webproject.core.repositories.AddressRepo;
import com.webproject.core.repositories.CorporativeRepo;
import com.webproject.core.repositories.UserRepo;
import com.webproject.core.services.CityService;
import com.webproject.core.services.UserService;
import com.webproject.core.services.exceptions.LeLocAppCoreException;
import com.webproject.core.services.exceptions.UserException;
import com.webproject.core.services.util.AddressList;
import com.webproject.spring.security.LoggedInChecker;
import com.webproject.spring.security.model.AppRole.RoleEnum;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	AddressRepo addressRepo;
	@Autowired
	CorporativeRepo companyRepo;
	@Autowired
	CityService cityService;
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private LoggedInChecker loggedInChecker;

	@Override
	@Cacheable(value="userCacheById", key="#id")
	public User findUserById(Long id) throws LeLocAppCoreException {
		User user = userRepo.findOne(id);
		if(user == null)
			throw new UserException(ErrKeys.USER_NOT_FOUND);
		return user;
	}

	@Override
	public User findUserByEmailAddress(String email) throws LeLocAppCoreException {
		User user = userRepo.findByAccountEmail(email);
		if(user == null)
			throw new UserException(ErrKeys.USER_NOT_FOUND);
		return user;
	}

	@Override
	public User findUserByEmailAndPassword(String email, String password) throws LeLocAppCoreException {
		User user = userRepo.findByAccountEmailAndAccountPassword(email, LeLocAppCrypto.encryptAES(password));
		if(user == null)
			throw new UserException(ErrKeys.USER_NOT_FOUND);
		return user;
	}

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public Account findAccountById(Long id) {
		Account acc = accountRepo.findOne(id);
		if(acc == null)
			throw new UserException("User account is not found by given id : " + id, ErrKeys.USER_NOT_FOUND);
		return acc;
	}

	@Override
	public Account saveAccount(Long userId, Account account) {
		User user = findUserById(userId);
		
		Account tempAcc = findAccountByEmail(account.getEmail());
		if(tempAcc != null)
			throw new UserException(ErrKeys.USER_EMAIL_EXIST_ALREADY, account.getEmail());
		account.setUser(user);
		account.setRole(RoleEnum.ROLE_USER);
		account.setPassword(encoder.encode(account.getPassword()));
		return accountRepo.save(account);
	}

	@Override
	public Account findAccountByUserId(Long userId) {
		return accountRepo.findByUser(findUserById(userId));
	}

	@Override
	public Account findAccountByEmail(String email) {
		return accountRepo.findByEmail(email);
	}

	@Override
	public Address findAddressById(Long addressId) {
		Address adr = addressRepo.findOne(addressId);
		if(adr == null)
			throw new UserException(ErrKeys.USER_ADDRESS_NOT_FOUND);
		return adr;
	}

	@Override
	public AddressList findAddressByUserId(Long userId) {
		return new AddressList(addressRepo.findByUser(findUserById(userId)));
	}

	@Override
	public Corporative findCompanyById(Long companyId) {
		Corporative company = companyRepo.findOne(companyId);
		if(company == null)
			throw new UserException(ErrKeys.USER_COMPANY_NOT_FOUND);
		return company;
	}

	@Override
	public Corporative findCompanyByUserId(Long userId) {
		User user = findUserById(userId);
		if(user.getAccount().getType() != Const.USER_TYPE_CORPORATIVE)
			throw new UserException("Useris not corporative type", ErrKeys.USER_IS_NOT_CORPORATIVE_TYPE);
		
		Corporative company = companyRepo.findByUser(user);
		if(company == null)
			throw new UserException(ErrKeys.USER_COMPANY_NOT_FOUND);
		return company;
	}

	@Override
	public boolean isCurrentUserLoggedIn() {
		return getLoggedInAccount() != null;
	}

	@Override
	public Account getLoggedInAccount() {
		return loggedInChecker.getLoggedInAccount();
	}

	@Override
	public RoleEnum getAccountRole() {
		RoleEnum role = loggedInChecker.getAccountRole();
		if(role == null)
			throw new UserException("No Session Found", ErrKeys.USER_NO_SESSION);
		return role;
	}

	@Override
	public Address saveUserAddress(Long userId, Long cityId, Address address) {
		User user = findUserById(userId);
		City city = cityService.findById(cityId);
		
		address.setCity(city);
		address.setRegion(city.getRegion());
		address.setCountry(city.getRegion().getCountry());
		address.setUser(user);
		
		return addressRepo.save(address);
	}

	@Override
	public Corporative saveCompany(Long userId, Corporative company) {
		User user = findUserById(userId);
		company.setUser(user);
		
		return companyRepo.save(company);
	}

	@Override
	public Corporative saveCompanyAddress(Long companyId, Long cityId, Address address) {
		Corporative company = findCompanyById(companyId);
		City city = cityService.findById(cityId);
		
		address.setCity(city);
		address.setRegion(city.getRegion());
		address.setCountry(city.getRegion().getCountry());
		
		company.setAddress(address);
		
		return companyRepo.save(company);
	}

	

}
