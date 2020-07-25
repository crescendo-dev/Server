package com.insight.crescendo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.insight.crescendo.domain.changePasswordUrl;
import com.insight.crescendo.domain.reservationUrl;
import com.insight.crescendo.domain.signInUrl;
import com.insight.crescendo.domain.signUpUrl;
import com.insight.crescendo.entity.account;
import com.insight.crescendo.entity.customer_user_info;
import com.insight.crescendo.repository.accountRepostitory;
import com.insight.crescendo.repository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class Controller {
	@Autowired
	private AccountService accountService;
	@Autowired
	private accountRepostitory accountRepostitory;
	@Autowired
	private customerRepository customerRepository;
	@RequestMapping(value="/test")
	public String hello(HttpServletRequest httpServletRequest) {
		String id = httpServletRequest.getParameter("id");
		return id;
	}
	@PostMapping(value="/SignIn")
	public Map<String,Object> SignIn(@RequestBody signInUrl url){
		account account = new account();
		Map<String,Object> map = new HashMap<>();
		String id = url.getId();
		String password = url.getPassword();
		String md5_pwd = DigestUtils.md5DigestAsHex(password.getBytes());
		account = accountRepostitory.findByPasswordAndEmail(md5_pwd,id);
		if(account.getId()==0) map.put("code",0);
		else map.put("code",account.getId());
		return map;
	}
	@PostMapping(value="/SignUp")
	public Map<String,Object> SignUp(@RequestBody signUpUrl url){
		account account = new account();
		Map<String, Object> map = new HashMap<String, Object>();
		String email = url.getEmail();
		String password = url.getPassword();
		String name = url.getName();

		account.setEmail(email);
		String md5_pwd = DigestUtils.md5DigestAsHex(password.getBytes());
		account.setPassword(md5_pwd);
		account.setName(name);
		accountRepostitory.save(account);
		//int id = accountService.insertAccountInfo(email, password, name);
		Long id = account.getId();
		if(id ==0){
			map.put("code",-1);
		}
		else map.put("code", 0);
		return map;
	}
	@PostMapping(value="/ChangePassword")
	public Map<String, Object> ChangePassword(@RequestBody changePasswordUrl url){
		account account = new account();
		Map<String,Object> map = new HashMap<>();
		String id = url.getId();
		String password = url.getPassword();
		String changePassword = url.getNewpassword();

		String md5_pwd = DigestUtils.md5DigestAsHex(password.getBytes());
		String change_md5_pwd = DigestUtils.md5DigestAsHex(changePassword.getBytes());
		account = accountRepostitory.findByPasswordAndEmail(md5_pwd,id);
		account.setPassword(change_md5_pwd);
		accountRepostitory.save(account);
		map.put("code",0);
		return map;
	}
	@PostMapping(value="/ChangeName")
	public Map<String,Object> ChangeName(@RequestBody HashMap<String,Object> map){
		account account = new account();
		String id = (String)map.get("id");
		String name = (String)map.get("name");
		account = accountRepostitory.findByEmail(id);
		account.setName(name);
		accountRepostitory.save(account);
		map.put("code",0);
		return map;
	}
	@PostMapping(value="/Reservation")
	public Map<String,Object> insertReservation(@RequestBody reservationUrl url){
		customer_user_info customerUserInfo = new customer_user_info();
		Map<String,Object> map = new HashMap<>();
		String name = url.getName();
		String phone_number = url.getPhone_number();
		customerUserInfo.setName(name);
		customerUserInfo.setPhone_number(phone_number);
		customerRepository.save(customerUserInfo);
		map.put("code",0);
		return map;

	}
}
