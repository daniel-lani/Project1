package com.revature.exceptions;

public class ManagerNotFoundException extends Exception {
	private static final long serialVersionUID = -6003147175423298384L;

	public ManagerNotFoundException() {
		super();
	}
	public ManagerNotFoundException(String msg) {
		super(msg);
	}
	public ManagerNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
