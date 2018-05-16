package com.revature.services;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerHomeService {
	private ManagerHomeService(){}
	
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		return "/managerHome.jsp";
	}
}
