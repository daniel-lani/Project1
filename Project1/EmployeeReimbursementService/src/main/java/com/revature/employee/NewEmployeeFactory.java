package com.revature.employee;

import com.revature.exceptions.EmployeeTypeNotFoundException;

public class NewEmployeeFactory{

	public static Employee getUser(String type,String name,String password) throws EmployeeTypeNotFoundException {
		switch(type.toLowerCase()) {
		case "manager":
			return new FinanceManager(name,password);
		case "employee":
			return new GenericEmployee(name,password);
		default:
			throw new EmployeeTypeNotFoundException("That is not an existing user type");
		}
	}
}
