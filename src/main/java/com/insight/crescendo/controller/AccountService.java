package com.insight.crescendo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AccountService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertAccountInfo(String email,String password,String name) {
		int id =-1;
		String pwd_md5 = DigestUtils.md5DigestAsHex(password.getBytes());
		String sql_insert_account ="INSERT INTO account (email,password,name) VALUES(?,?,?) RETURNING id";
		id = jdbcTemplate.queryForObject(sql_insert_account, new Object[] {email,pwd_md5,name},Integer.class);
		return id;
	}
}
