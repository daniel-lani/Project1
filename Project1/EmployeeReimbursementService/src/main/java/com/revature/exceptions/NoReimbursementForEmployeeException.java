package com.revature.exceptions;

public class NoReimbursementForEmployeeException extends Exception {

	private static final long serialVersionUID = -6003147175423298384L;

	public NoReimbursementForEmployeeException() {
		super();
	}
	public NoReimbursementForEmployeeException(String msg) {
		super(msg);
	}
	public NoReimbursementForEmployeeException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
