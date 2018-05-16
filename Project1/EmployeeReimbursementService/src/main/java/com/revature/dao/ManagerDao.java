package com.revature.dao;

import java.util.List;

import com.revature.employee.FinanceManager;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.ManagerNotFoundException;
import com.revature.exceptions.NoManagerException;
import com.revature.exceptions.PasswordHashException;

public interface ManagerDao {


	public List<FinanceManager> getManagers() throws NoManagerException;

	public FinanceManager getManager(String man) throws EmployeeNotFoundException, ManagerNotFoundException;
	
	public boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException, ManagerNotFoundException;

	String getPasswordHash(FinanceManager man) throws PasswordHashException;

	
}
