package com.shdh.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shdh.dto.UserDto;
import com.shdh.service.UserService;
import com.shdh.vo.Greeting;
import com.shdh.vo.RequestUser;

@RestController
@RequestMapping("/")
public class UserController {
	Environment env;
	private UserService userSerivce;

	@Autowired
	public UserController(Environment env, UserService userService) {
		this.env = env;
		this.userSerivce = userService;
	}

	@Autowired
	private Greeting greeting;

	@GetMapping("/health_check")
	public String healthCheck() {
		return "It's Working in User Servie";
	}

	@GetMapping("/welcome")
	public String welcome() {
		// return env.getProperty("greeting.message");
		return greeting.getMessage();
	}

	@PostMapping("/users")
	public String createUser(@RequestBody RequestUser user) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserDto userDto = mapper.map(user, UserDto.class);

		userSerivce.createUser(userDto);

		return "Create user method is called";

	}

}
