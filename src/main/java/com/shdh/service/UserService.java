package com.shdh.service;

import com.shdh.dto.UserDto;

public interface UserService {
	// 사용자 생
	UserDto createUser(UserDto userDto);
}
