package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	 @ExceptionHandler(value = AdminNotFoundException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody Errormessage handleAdminException(AdminNotFoundException exception)
	{
		  return new Errormessage(HttpStatus.CONFLICT.value(), exception.getMessage());
		
	}
}
