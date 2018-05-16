package com.revature.exceptions;

public class NoEmployeesException extends Exception {
	private static final long serialVersionUID = -6003147175423298384L;

	public NoEmployeesException() {
		super();
	}
	public NoEmployeesException(String msg) {
		super(msg);
	}
	public NoEmployeesException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
