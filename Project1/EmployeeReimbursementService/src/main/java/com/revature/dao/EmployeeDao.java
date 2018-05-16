package com.revature.dao;

import java.util.List;

import com.revature.employee.Employee;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.NoEmployeesException;
import com.revature.exceptions.PasswordHashException;

public interface EmployeeDao {

	public List<GenericEmployee> getEmployees() throws NoEmployeesException;

	public GenericEmployee getEmployee(String emp) throws EmployeeNotFoundException;
	
	public boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException;

	String getPasswordHash(GenericEmployee emp) throws PasswordHashException;
}
