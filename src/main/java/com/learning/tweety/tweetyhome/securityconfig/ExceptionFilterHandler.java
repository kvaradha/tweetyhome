package com.learning.tweety.tweetyhome.securityconfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.tweety.tweetyhome.exceptionhandler.ErrorResponse;

@Component
public class ExceptionFilterHandler extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (UnauthorizedException e) {
			constructErrorResponseForFilter(response,HttpStatus.UNAUTHORIZED, e);
		} catch (RuntimeException e) {
			constructErrorResponseForFilter(response,HttpStatus.INTERNAL_SERVER_ERROR, e);
		} catch (Exception e) {
			constructErrorResponseForFilter(response,HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	public void constructErrorResponseForFilter(HttpServletResponse response, HttpStatus code, Exception e)
			throws JsonProcessingException, IOException {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(e.getLocalizedMessage());
		errorResponse.setResponseStatus(code.toString());
		response.setStatus(code.value());
		response.getWriter().write(convertObjectToJson(errorResponse));
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
