package ersTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.dao.EmployeeService;
import com.revature.dao.ManagerService;
import com.revature.employee.FinanceManager;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.ManagerNotFoundException;
import com.revature.exceptions.PasswordHashException;


public class EmployeeTest {
	@Test(expected = EmployeeNotFoundException.class)
	public void empNotFoundExceaptionTest() throws EmployeeNotFoundException {
		EmployeeService.getEmployee("notAnEmployee");
	}
	@Test
	public void getEmployeeTest() throws EmployeeNotFoundException {
		 assertEquals(EmployeeService.getEmployee("david").getUsername(),"david");
	}
	@Test
	public void updateEmpTest() throws EmployeeNotFoundException {
		GenericEmployee david = EmployeeService.getEmployee("david");
		assertEquals(david.getFirstName(),"david");
		EmployeeService.updateInfo(david.getId(),"dave", null, null, null);
		assertEquals(david.getFirstName(),"dave");
		EmployeeService.updateInfo(david.getId(),"david", null, null, null);
	}
	@Test
	public void passwordHashTest() throws EmployeeNotFoundException, PasswordHashException {
		GenericEmployee david = EmployeeService.getEmployee("david");
		String password = EmployeeService.getPasswordHash(new GenericEmployee(david.getUsername(),"password"));
		assertEquals(david.getPassword(),password);
	}
	@Test(expected = ManagerNotFoundException.class)
	public void getManagerNotFoundTest() throws ManagerNotFoundException, EmployeeNotFoundException{
		FinanceManager matt = ManagerService.getManager("notAManager");
	}
	@Test
	public void getManagerTest() throws ManagerNotFoundException, EmployeeNotFoundException{
		FinanceManager matt = ManagerService.getManager("matt");
		assertEquals(matt.getFirstName(),"matt");
	}
}
