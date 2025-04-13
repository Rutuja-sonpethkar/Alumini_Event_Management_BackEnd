package com.example.demo.Exception;

public class AdminNotFoundException extends RuntimeException
{
    private String message;

	public AdminNotFoundException(String  message)
    {
		super(message);
    	this.message=message;
    }
}
