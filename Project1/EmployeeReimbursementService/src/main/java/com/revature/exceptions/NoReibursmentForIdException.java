package com.revature.exceptions;

public class NoReibursmentForIdException extends Exception {

	private static final long serialVersionUID = -6003147175423298384L;

	public NoReibursmentForIdException() {
		super();
	}
	public NoReibursmentForIdException(String msg) {
		super(msg);
	}
	public NoReibursmentForIdException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
