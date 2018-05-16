package com.revature.exceptions;

public class ManagerApprovingOwnRequestException extends Exception {

	private static final long serialVersionUID = 925607534058303810L;
	
	public ManagerApprovingOwnRequestException() {
		super();
	}
	public ManagerApprovingOwnRequestException(String msg) {
		super(msg);
	}
	public ManagerApprovingOwnRequestException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
