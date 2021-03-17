package com.pokedex.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pokedex.errorUtils.MessageErrorUtils;

@ControllerAdvice
public class ExceptionHandlingController  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Object> handleGenericInternalServerErrorException(InternalServerError e){
		ErrorReponse error = new ErrorReponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				MessageErrorUtils.INTERNAL_SERVER_ERROR, e.getMessage(), e.getPath());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
