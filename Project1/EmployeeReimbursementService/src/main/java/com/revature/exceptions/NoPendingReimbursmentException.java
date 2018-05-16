package com.revature.exceptions;

public class NoPendingReimbursmentException extends Exception {
	
	private static final long serialVersionUID = -6003147175423298384L;

	public NoPendingReimbursmentException() {
		super();
	}
	public NoPendingReimbursmentException(String msg) {
		super(msg);
	}
	public NoPendingReimbursmentException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
