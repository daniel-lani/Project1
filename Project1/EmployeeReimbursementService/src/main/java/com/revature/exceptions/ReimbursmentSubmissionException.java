package com.revature.exceptions;

public class ReimbursmentSubmissionException extends Exception {
	private static final long serialVersionUID = -6003147175423298384L;

	public ReimbursmentSubmissionException() {
		super();
	}
	public ReimbursmentSubmissionException(String msg) {
		super(msg);
	}
	public ReimbursmentSubmissionException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
