package com.shdh.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shdh.dto.UserDto;
import com.shdh.jpa.UserEntity;
import com.shdh.jpa.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepsitory;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());
		
		//데이터 베이스에 저장하기 위해 UserEntity가 필요함.
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);
		userEntity.setEncryptedPwd("encrpted_password"); //temp value
		
		userRepsitory.save(userEntity);
		
		UserDto returnUserDto = mapper.map(userEntity, UserDto.class);
		
		return returnUserDto;
	}
	
	
	
}
