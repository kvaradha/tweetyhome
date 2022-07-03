package com.learning.tweety.tweetyhome.securityconfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.learning.tweety.tweetyhome.services.token.JWTTokenService;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	public JWTTokenService jwtTokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
			FilterChain filterChain) throws IOException, ServletException {
		jwtTokenService.isValidToken(request);
		filterChain.doFilter(request, response);
	}
}
