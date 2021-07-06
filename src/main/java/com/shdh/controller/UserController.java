package com.shdh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shdh.vo.Greeting;

@RestController
@RequestMapping("/")
public class UserController {
	Environment env;
	
	@Autowired
	public UserController(Environment env) {
		this.env = env;
	}

	@Autowired
	private Greeting greeting;

	@GetMapping("/health_check")
	public String healthCheck() {
		return "It's Working in User Servie";
	}
	
	
	@GetMapping("/welcome")
	public String welcome() {
		//return env.getProperty("greeting.message");
		return greeting.getMessage();
	}
	
	
	
	
}
