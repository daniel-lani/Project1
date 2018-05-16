package com.revature.exceptions;

public class NoManagerException extends Exception {
	private static final long serialVersionUID = -6003147175423298384L;

	public NoManagerException() {
		super();
	}
	public NoManagerException(String msg) {
		super(msg);
	}
	public NoManagerException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
