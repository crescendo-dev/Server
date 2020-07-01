package com.insight.crescendo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.insight.crescendo.entity.account;
import com.insight.crescendo.repository.accountRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	  @RequestMapping(value="/SignUp")
	  public Map<String,Object> SignUp(HttpServletRequest httpServletRequest){
		  Map<String, Object> map = new HashMap<String, Object>();
		  String email = httpServletRequest.getParameter("email");
		  String password = httpServletRequest.getParameter("password");
		  String name = httpServletRequest.getParameter("name");

		  account account = new account();
		  account.setEmail(email);
		  account.setPassword(password);
		  account.setName(name);
		  accountRepostitory.save(account);
		  //int id = accountService.insertAccountInfo(email, password, name);
		  Long id = account.getId();
		  map.put("id", id);
		  return map;
	  }
}
