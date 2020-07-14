package com.insight.crescendo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.insight.crescendo.domain.signUpUrl;
import com.insight.crescendo.entity.account;
import com.insight.crescendo.repository.accountRepostitory;
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
	  @RequestMapping(value="/test")
		public String hello(HttpServletRequest httpServletRequest) {
			String id = httpServletRequest.getParameter("id");
			return id;
		}
	  @RequestMapping(value="/SignIn")
	  public Map<String,Object> SignIn(HttpServletRequest httpServletRequest){
		  Map<String,Object> map = new HashMap<>();
		  String id = httpServletRequest.getParameter("id");
		  String password = httpServletRequest.getParameter("password");

		  return map;
	  }
	  @PostMapping(value="/SignUp")
	  public Map<String,Object> SignUp(@RequestBody signUpUrl url){
		  Map<String, Object> map = new HashMap<String, Object>();
		  String email = url.getEmail();
		  String password = url.getPassword();
		  String name = url.getName();

		  account account = new account();
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
}
