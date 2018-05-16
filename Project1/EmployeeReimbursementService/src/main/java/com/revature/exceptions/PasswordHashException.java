package com.revature.exceptions;

public class PasswordHashException extends Exception {
	
	private static final long serialVersionUID = -6003147175423298384L;

	public PasswordHashException() {
		super();
	}
	public PasswordHashException(String msg) {
		super(msg);
	}
	public PasswordHashException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
