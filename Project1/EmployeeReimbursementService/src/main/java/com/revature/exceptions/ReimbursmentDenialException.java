package com.revature.exceptions;

public class ReimbursmentDenialException extends Exception {
	
	private static final long serialVersionUID = -6003147175423298384L;

	public ReimbursmentDenialException() {
		super();
	}
	public ReimbursmentDenialException(String msg) {
		super(msg);
	}
	public ReimbursmentDenialException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
