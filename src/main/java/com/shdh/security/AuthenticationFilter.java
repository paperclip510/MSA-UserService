package com.shdh.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shdh.dto.UserDto;
import com.shdh.service.UserService;
import com.shdh.vo.RequestLogin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private Environment env;
	private UserService userService;

	public AuthenticationFilter(AuthenticationManager authenticationManager, Environment env, UserService userService) {
		super.setAuthenticationManager(authenticationManager);
		this.env = env;
		this.userService = userService;
	}

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
		// super.successfulAuthentication(request, response, chain, authResult);
		log.debug(SPRING_SECURITY_FORM_PASSWORD_KEY);
		log.debug(((User) authResult.getPrincipal()).getUsername());
		// 로그인 성공시 어떤 처리를 해줄 것인지에 관한 로직.

		String userName = ((User) authResult.getPrincipal()).getUsername();
		UserDto userDetails = userService.getUserDetailsByEmail(userName);

		String token = Jwts.builder().setSubject(userDetails.getUserId())
				.setExpiration(
						new Date(
								System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))
								)
						)
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
				.compact();

		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getUserId());
	}

}
