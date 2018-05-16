package com.revature.exceptions;

public class ReimbursmentApprovalException extends Exception {
	private static final long serialVersionUID = -6003147175423298384L;

	public ReimbursmentApprovalException() {
		super();
	}
	public ReimbursmentApprovalException(String msg) {
		super(msg);
	}
	public ReimbursmentApprovalException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
