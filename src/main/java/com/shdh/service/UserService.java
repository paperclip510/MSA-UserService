package com.shdh.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.shdh.dto.UserDto;
import com.shdh.jpa.UserEntity;

public interface UserService extends UserDetailsService{
	// 사용자 생성
	UserDto createUser(UserDto userDto);

	// 사용자 조회 
	UserDto getUserByUserId(String userId);
	
	// 전체 사용자 조회
	Iterable<UserEntity> getUserByAll();

	
}
