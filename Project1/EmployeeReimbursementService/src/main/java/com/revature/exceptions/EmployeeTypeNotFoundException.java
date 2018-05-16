package com.revature.exceptions;

public class EmployeeTypeNotFoundException extends Exception {

	private static final long serialVersionUID = 925607534058303810L;
	
	public EmployeeTypeNotFoundException() {
		super();
	}
	public EmployeeTypeNotFoundException(String msg) {
		super(msg);
	}
	public EmployeeTypeNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
