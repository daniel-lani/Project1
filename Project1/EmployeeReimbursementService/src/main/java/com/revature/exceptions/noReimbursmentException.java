package com.revature.exceptions;

public class noReimbursmentException extends Exception {

	private static final long serialVersionUID = -6003147175423298384L;

	public noReimbursmentException() {
		super();
	}
	public noReimbursmentException(String msg) {
		super(msg);
	}
	public noReimbursmentException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
