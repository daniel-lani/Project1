package com.revature.exceptions;

public class EmployeeNotFoundException extends Exception {

		private static final long serialVersionUID = 925607534058303810L;
		
		public EmployeeNotFoundException() {
			super();
		}
		public EmployeeNotFoundException(String msg) {
			super(msg);
		}
		public EmployeeNotFoundException(String msg, Throwable cause) {
			super(msg,cause);
		}

	}


