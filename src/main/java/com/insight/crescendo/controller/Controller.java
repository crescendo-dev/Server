package com.insight.crescendo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class Controller {
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
}
