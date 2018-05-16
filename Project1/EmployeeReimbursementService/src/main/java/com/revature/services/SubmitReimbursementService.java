package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitReimbursementService {
	
private SubmitReimbursementService(){}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		return "/managerHome.jsp";
	}

}
