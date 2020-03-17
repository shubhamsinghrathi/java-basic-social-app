package com.indi.basicsocialapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.indi.basicsocialapp.model.RestErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<RestErrorResponse> handleException(Exception exc) {
		RestErrorResponse error = new RestErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<RestErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
}
