package com.cognizant.truyum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class UserNotExistsException extends Exception{
	public UserNotExistsException(String msg) {
		super(msg);
	}
	public UserNotExistsException(){
		super();
	}
}
