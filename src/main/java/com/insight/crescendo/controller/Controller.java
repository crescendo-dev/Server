package com.insight.crescendo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.insight.crescendo.domain.changePasswordUrl;
import com.insight.crescendo.domain.signInUrl;
import com.insight.crescendo.domain.signUpUrl;
import com.insight.crescendo.entity.account;
import com.insight.crescendo.repository.accountRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class Controller {
	private account account = new account();
	@Autowired
	private AccountService accountService;
	@Autowired
	private accountRepostitory accountRepostitory;
	@RequestMapping(value="/test")
	public String hello(HttpServletRequest httpServletRequest) {
		String id = httpServletRequest.getParameter("id");
		return id;
	}
	@PostMapping(value="/SignIn")
	public Map<String,Object> SignIn(@RequestBody signInUrl url){
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
		String id = (String)map.get("id");
		String name = (String)map.get("name");
		account = accountRepostitory.findByEmail(id);
		account.setName(name);
		accountRepostitory.save(account);
		map.put("code",0);
		return map;
	}
}
