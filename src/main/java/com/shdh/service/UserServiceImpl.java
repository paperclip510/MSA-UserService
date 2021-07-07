package com.shdh.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shdh.dto.UserDto;
import com.shdh.jpa.UserEntity;
import com.shdh.jpa.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	UserRepository userRepsitory;
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepsitory, BCryptPasswordEncoder passwordEncoder) {
		// 생성자로 의존성 주입.
		// BCryptPasswordEncoder 는 한번도 선언 된 적이 없기 때문에 @Service 생성자 파라미터로 추가 할수 없다.
		// 이를 해결하기 위해 @Service가 실행 되기 전에 @Bean으로 등록 해주어야 한다. -> UserServiceApplication
		this.userRepsitory = userRepsitory;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		userDto.setUserId(UUID.randomUUID().toString());

		// 데이터 베이스에 저장하기 위해 UserEntity가 필요함.
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);
		
		// 패스워드 암호화
		userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
		
		// insert into database
		userRepsitory.save(userEntity);

		UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

		return returnUserDto;
	}

}
