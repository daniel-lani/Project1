package com.revature.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.connection.ConnectionUtil;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.NoEmployeesException;
import com.revature.exceptions.PasswordHashException;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	private static EmployeeDaoImpl instance;
		
		private EmployeeDaoImpl() {}
		
		public static EmployeeDaoImpl getInstance() {
			if(instance == null) {
				instance = new EmployeeDaoImpl();
			}
			return instance;
		}

		@Override
		public List<GenericEmployee> getEmployees() throws NoEmployeesException {
			try(Connection conn = ConnectionUtil.getConnection()){
				List<GenericEmployee> emp= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE INNER JOIN GENERIC_EMPLOYEE ON gen_emp_id = EMPLOYEE.EMP_ID");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					emp.add(new GenericEmployee(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Emp_First_Name"),rs.getString("Emp_Last_Name"),rs.getString("Emp_email"),rs.getString("Emp_address"), rs.getInt("emp_id")));
				}
				return emp;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new NoEmployeesException();
		}

		@Override
		public GenericEmployee getEmployee(String emp) throws EmployeeNotFoundException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Generic_employee INNER JOIN EMPLOYEE ON Generic_employee.gen_emp_ID = EMPLOYEE.EMP_ID where gen_emp_id in (SELECT emp_id from EMPLOYEE where EMP_USERNAME = ?)");
				stmt.setString(++index, emp);
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next())
					return new GenericEmployee(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Emp_First_Name"),rs.getString("Emp_Last_Name"),rs.getString("Emp_email"),rs.getString("Emp_address"), rs.getInt("emp_id"));
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new EmployeeNotFoundException();
		}

		@Override
		public boolean updateInfo(int id,String fName,String lName, String email, String add) throws EmployeeNotFoundException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("{CALL update_emp(?,?,?,?,?)}");
				stmt.setInt(++index, id);
				stmt.setString(++index,fName);
				stmt.setString(++index,lName);
				stmt.setString(++index,email);
				stmt.setString(++index,add);
				return stmt.execute();
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new EmployeeNotFoundException();
		}

		@Override
		public String getPasswordHash(GenericEmployee emp) throws PasswordHashException{
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("SELECT get_emp_hash(?,?)AS HASH FROM dual");
				stmt.setString(++index, emp.getUsername());
				stmt.setString(++index, emp.getPassword());
				ResultSet rs = stmt.executeQuery();
				if (rs.next())
					return rs.getString("HASH");
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new PasswordHashException();
		}
		
}