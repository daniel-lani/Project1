package com.revature.dao;

import java.util.List;

import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.NoEmployeesException;
import com.revature.exceptions.PasswordHashException;

public class EmployeeService {
private static EmployeeDao dao = EmployeeDaoImpl.getInstance();

public static List<GenericEmployee> getEmployees() throws NoEmployeesException{
	return dao.getEmployees();
}

public static GenericEmployee getEmployee(String emp) throws EmployeeNotFoundException{
	return dao.getEmployee(emp);
}

public static boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException{
	return dao.updateInfo(id,fName,lName,email,add);
}

public static String getPasswordHash(GenericEmployee emp) throws PasswordHashException {
	return dao.getPasswordHash(emp);
}
}
