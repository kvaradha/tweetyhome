package com.learning.tweety.tweetyhome.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.learning.tweety.tweetyhome.securityconfig.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value 
			= { IllegalArgumentException.class })
	protected @ResponseBody ErrorResponse handleConflict(IllegalArgumentException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(ex.getLocalizedMessage());
		errorResponse.setResponseStatus(HttpStatus.BAD_REQUEST.toString());
		return errorResponse;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value 
			= { RuntimeException.class })
	protected @ResponseBody ErrorResponse handleConflict(RuntimeException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(ex.getLocalizedMessage());
		errorResponse.setResponseStatus(HttpStatus.BAD_REQUEST.toString());
		return errorResponse;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value 
			= { Exception.class })
	protected @ResponseBody ErrorResponse handleConflict(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(ex.getLocalizedMessage());
		errorResponse.setResponseStatus(HttpStatus.BAD_REQUEST.toString());
		return errorResponse;
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value 
			= { UnauthorizedException.class })
	protected @ResponseBody ErrorResponse handleConflict(UnauthorizedException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(ex.getLocalizedMessage());
		errorResponse.setResponseStatus(HttpStatus.UNAUTHORIZED.toString());
		return errorResponse;
	}
}
