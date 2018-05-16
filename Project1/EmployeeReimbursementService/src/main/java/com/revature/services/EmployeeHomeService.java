package com.revature.services;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeHomeService {
	private EmployeeHomeService(){}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		return "/employeeHome.jsp";
	}
}