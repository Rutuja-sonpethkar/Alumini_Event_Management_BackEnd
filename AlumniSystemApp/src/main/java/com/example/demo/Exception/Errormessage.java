
package com.example.demo.Exception;

public class Errormessage
{
    private int statusCode;
    public int getStatusCode() 
    {
		return statusCode;
	}

	public void setStatusCode(int statusCode) 
	{
		this.statusCode = statusCode;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	private String message;
    
    public Errormessage( int  statusCode ,String message)
    {
    	this.message=message;
    	this. statusCode= statusCode;
    }
}
