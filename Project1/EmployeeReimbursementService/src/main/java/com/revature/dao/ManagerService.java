package com.revature.dao;

import java.util.List;

import com.revature.employee.FinanceManager;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.ManagerNotFoundException;
import com.revature.exceptions.NoManagerException;
import com.revature.exceptions.PasswordHashException;

public class ManagerService {
private static ManagerDao dao = ManagerDaoImpl.getInstance();

public static List<FinanceManager> getManagers() throws NoManagerException{
	return dao.getManagers();
}

public static FinanceManager getManager(String man) throws EmployeeNotFoundException, ManagerNotFoundException{
	return dao.getManager(man);
}

public static boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException, ManagerNotFoundException{
	return dao.updateInfo(id,fName,lName,email,add);
}

public static String getPasswordHash(FinanceManager man) throws PasswordHashException {
	return dao.getPasswordHash(man);
}


}

