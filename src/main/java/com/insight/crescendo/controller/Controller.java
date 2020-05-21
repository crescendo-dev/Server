package com.insight.crescendo.controller;

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
}
