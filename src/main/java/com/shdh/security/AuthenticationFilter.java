package com.shdh.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shdh.vo.RequestLogin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			// credential
			RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

			// getAuthenticationManager : 인증처리 매니저.
			return getAuthenticationManager().authenticate(
					// spring security 에서 사용할수 있도록 오브젝트 변환.
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));


		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//super.successfulAuthentication(request, response, chain, authResult);
		log.debug(SPRING_SECURITY_FORM_PASSWORD_KEY);
		log.debug(((User)authResult.getPrincipal()).getUsername());
		// 로그인 성공시 어떤 처리를 해줄 것인지에 관한 로직.
		
		
	}

}
