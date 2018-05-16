package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.exceptions.ManagerApprovingOwnRequestException;

public class MasterDispatcher {
private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
private MasterDispatcher() {
		
	}
	
	public static String process(HttpServletRequest request, HttpServletResponse responce) {
		switch(request.getRequestURI()) {
		case "/EmployeeReimbursementService/login.do": 
			return LoginService.login(request, responce);
		case"/EmployeeReimbursementService/logout.do":
			return LoginService.logout(request, responce);
		case "/EmployeeReimbursementService/managerHome.do": 
			return ManagerHomeService.home(request, responce);
		case "/EmployeeReimbursementService/employeeHome.do": 
			return EmployeeHomeService.home(request, responce);
		case"/EmployeeReimbursementService/updateEmployee.do":
			return UpdateService.updateEmployee(request, responce);
		case"/EmployeeReimbursementService/updateManager.do":
			return UpdateService.updateManager(request, responce);	
		case"/EmployeeReimbursementService/managerRefresh.do":
			return UpdateService.refreshManager(request, responce);
		case"/EmployeeReimbursementService/employeeRefresh.do":
			return UpdateService.refreshEmployee(request, responce);
		case"/EmployeeReimbursementService/reburMan.do":
			return ReburService.submitManagerRebur(request, responce);
		case"/EmployeeReimbursementService/reburEmp.do":
			return ReburService.submitEmployeeRebur(request, responce);
		case"/EmployeeReimbursementService/approve.do":
			if(request.getParameter("contact").equals("approve")){
			try {
				return ReburService.approve(request, responce);
			} catch (ManagerApprovingOwnRequestException maore) {
				logger.error(maore.getMessage(),maore);
				return"/approveReimburstment.jsp";
			}
			}
			else {
				try {
					return ReburService.deny(request, responce);
				} catch (ManagerApprovingOwnRequestException maore) {
					logger.error(maore.getMessage(),maore);
					return"/approveReimburstment.jsp";
				}
			}
		case"/EmployeeReimbursementService/newPending.do":
			return ReburService.getNewPending(request, responce);
			 
		default: 
			System.out.println("default");
			return "404.jsp";
		}
	}
}
