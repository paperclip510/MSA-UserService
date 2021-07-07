package com.shdh.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // 다른 빈 보다 우선 등된다.
@EnableWebSecurity // 웹 시큐리티 용도로 사용.
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 권한.
		http.csrf().disable();
		// /users/** URI에 대해서 모두 허가한다.
		http.authorizeRequests().antMatchers("/users/**").permitAll();
		
		// 프레임으로 나눠진 http에서 정상 구동 되도록 한다.
		http.headers().frameOptions().disable();
		
//		Using generated security password: 8d21b939-eb78-42c3-8acb-1bbb9d1f5a1f
	}

}
