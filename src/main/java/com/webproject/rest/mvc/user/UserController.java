package com.webproject.rest.mvc.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.core.common.CompanyType;
import com.webproject.core.common.LeLocAppCrypto;
import com.webproject.core.common.api.Const;
import com.webproject.core.common.api.Util;
import com.webproject.core.models.entities.Account;
import com.webproject.core.models.entities.Address;
import com.webproject.core.models.entities.Corporative;
import com.webproject.core.models.entities.Phone;
import com.webproject.core.models.entities.Town;
import com.webproject.core.models.entities.User;
import com.webproject.core.models.entities.UserHistory;
import com.webproject.core.services.CityService;
import com.webproject.core.services.CountryService;
import com.webproject.core.services.TownService;
import com.webproject.core.services.UserService;
import com.webproject.rest.resources.asm.user.AccountResourceAsm;
import com.webproject.rest.resources.asm.user.AddressListResourceAsm;
import com.webproject.rest.resources.asm.user.AddressResourceAsm;
import com.webproject.rest.resources.asm.user.CorporativeResourceAsm;
import com.webproject.rest.resources.asm.user.UserResourceAsm;
import com.webproject.rest.resources.user.AccountResource;
import com.webproject.rest.resources.user.AddressListResource;
import com.webproject.rest.resources.user.AddressResource;
import com.webproject.rest.resources.user.CorporativeResource;
import com.webproject.rest.resources.user.UserResource;
import com.webproject.spring.security.model.AppRole.RoleEnum;

@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=UTF-8")
public class UserController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder encoder;
	
	@RequestMapping(value = "/byemail")
	public ResponseEntity<UserResource> findUserByEmail(@RequestParam("email") String email){
		return new ResponseEntity<UserResource>(new UserResourceAsm().toResource(userService.findUserByEmailAddress(email)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public  ResponseEntity<UserResource> findUserById(@PathVariable Long userId){
		return new ResponseEntity<UserResource>(new UserResourceAsm().toResource(userService.findUserById(userId)), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public  ResponseEntity<UserResource> saveUser(@RequestBody UserResource userRes){
		User user = userService.saveUser(userRes.toUser());
		return new ResponseEntity<UserResource>(new UserResourceAsm().toResource(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> findAccountById(@PathVariable Long accountId){
		return new ResponseEntity<AccountResource>(new AccountResourceAsm().toResource(userService.findAccountById(accountId)),  HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account/byuserid/{userId}", method = RequestMethod.GET)
	public ResponseEntity<AccountResource> findAccountByUserId(@PathVariable Long userId){
		return new ResponseEntity<AccountResource>(new AccountResourceAsm().toResource(userService.findAccountByUserId(userId)),  HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account/{userId}", method = RequestMethod.POST)
	public ResponseEntity<AccountResource> saveAccount(@PathVariable Long userId, @RequestBody AccountResource accRes){
		return new ResponseEntity<AccountResource>(new AccountResourceAsm().toResource(userService.saveAccount(userId, accRes.toAccount())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/address/{addressId}", method = RequestMethod.GET)
	public ResponseEntity<AddressResource> findAddressById(@PathVariable Long addressId){
		return new ResponseEntity<AddressResource>(new AddressResourceAsm().toResource(userService.findAddressById(addressId)), HttpStatus.OK);
	}
	@RequestMapping(value = "/address/byuserid/{userId}", method = RequestMethod.GET)
	public ResponseEntity<AddressListResource> findAddressByUserId(@PathVariable Long userId){
		return new ResponseEntity<AddressListResource>(new AddressListResourceAsm().toResource(userService.findAddressByUserId(userId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/corporative/{userId}", method = RequestMethod.POST)
	public ResponseEntity<CorporativeResource> saveCompany(@PathVariable Long userId, @RequestBody CorporativeResource companyRes){
		return new ResponseEntity<CorporativeResource>(new CorporativeResourceAsm().toResource(userService.saveCompany(userId, companyRes.toCorporative())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/corporative/{companyId}", method = RequestMethod.GET)
	public ResponseEntity<CorporativeResource> findCompanyById(@PathVariable Long companyId){
		return new ResponseEntity<CorporativeResource>(new CorporativeResourceAsm().toResource(userService.findCompanyById(companyId)), HttpStatus.OK);
	}
	@RequestMapping(value = "/corporative/byuser/{userId}", method = RequestMethod.GET)
	public ResponseEntity<CorporativeResource> findCompanyByUserId(@PathVariable Long userId){
		return new ResponseEntity<CorporativeResource>(new CorporativeResourceAsm().toResource(userService.findCompanyByUserId(userId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/address/{userId}/{cityId}", method = RequestMethod.POST)
	public ResponseEntity<AddressResource> saveUserAddress(@PathVariable Long userId, @PathVariable Long cityId, @RequestBody AddressResource addressRes){
		Address address = addressRes.toAddress();
		return new ResponseEntity<AddressResource>(new AddressResourceAsm().toResource(userService.saveUserAddress(userId, cityId, address)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/address/company/{companyId}/{cityId}", method = RequestMethod.POST)
	public ResponseEntity<CorporativeResource> saveCompanyAddress(@PathVariable Long companyId, @PathVariable Long cityId, @RequestBody AddressResource addressRes){
		Address address = addressRes.toAddress();
		return new ResponseEntity<CorporativeResource>(new CorporativeResourceAsm().toResource(userService.saveCompanyAddress(companyId, cityId, address)), HttpStatus.OK);
	}

	@Autowired
	CountryService cauntryService;
	@Autowired
	CityService cityService;
	@Autowired
	TownService townService;
	
	
	@RequestMapping(value = "/userData", method = RequestMethod.GET)
	public String addingUserData() throws Exception{
		
		User user = new User();
		user.setActive(true);
		user.setFirstName("Emin");
		user.setLastName("AVCI");
		
		userService.saveUser(user);
		
		Town town = townService.findById(31L);
		List<Address> adreses = new ArrayList<>();
		
		Address adres = new Address();
		adres.setCity(town.getCity());
		adres.setCountry(town.getCity().getRegion().getCountry());
		adres.setRegion(town.getCity().getRegion());
		adres.setTown(town);
		adres.setLine1("Montplaisir Reu 55");
		adres.setPostCode("35785");
		adres.setUser(user);
		adreses.add(adres);
		
		adres = new Address();
		adres.setCity(town.getCity());
		adres.setCountry(town.getCity().getRegion().getCountry());
		adres.setRegion(town.getCity().getRegion());
		adres.setTown(town);
		adres.setLine1("Servran boudettas 55");
		adres.setPostCode("93750");
		adres.setUser(user);
		adreses.add(adres);
		
		List<Phone> phones = new ArrayList<>();
		Phone phone = new Phone();
		phone.setAreaCode("33");
		phone.setNumber("9056844555");
		phone.setType(Const.PHONE_TYPE_MOBILE);
		phone.setUser(user);
		phones.add(phone);
		
		phone = new Phone();
		phone.setAreaCode("33");
		phone.setNumber("111111111");
		phone.setType(Const.PHONE_TYPE_HOME);
		phone.setUser(user);
		phones.add(phone);
		
		Corporative company = new Corporative();
		
		Address a = new Address();
		a.setCity(town.getCity());
		a.setCountry(town.getCity().getRegion().getCountry());
		a.setRegion(town.getCity().getRegion());
		a.setTown(town);
		a.setLine1("Servran boudettas 55");
		a.setPostCode("93750");
		a.setUser(user);
		
		company.setAddress(a);
		company.setOfficialName("Big Dana Corp.");
		company.setShortName("BD");
		company.setType(CompanyType.REAL_ESTATE_AGENCY);
		company.setWebSite("http://www.bigdana.com");
		company.setUser(user);
		
		Account acc = new Account();
		acc.setEmail("eminavci@gmail.com");
		
		//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		acc.setPassword(encoder.encode("123456"));
		acc.setRegType(Const.ACCOUNT_REG_EMAIL);
		acc.setRole(RoleEnum.ROLE_USER);
		acc.setUser(user);
		
		
		UserHistory history = new UserHistory();
		history.setActivationCode(LeLocAppCrypto.encryptAES(acc.getEmail()));
		history.setActivationCodeExpireDate(Util.getNextPrevDateForDay(2, 0)); // in 2 days it should be activated
		history.setRegistrationDate(new Date());
		history.setUser(user);
		
		user.setAccount(acc);
		user.setAddresses(adreses);
		user.setCompany(company);
		user.setHistory(history);
		user.setPhone(phones);
		
		
		userService.saveUser(user);
	
			
		return "'hel':{'type':'USer data Added'}";
	}
}




















