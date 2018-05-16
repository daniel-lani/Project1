package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveReimbursementService {
private ApproveReimbursementService(){}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		return "/approveReimburstment.jsp";
	}
}
