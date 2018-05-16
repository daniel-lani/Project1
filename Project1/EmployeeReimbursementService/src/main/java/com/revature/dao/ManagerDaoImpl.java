package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.connection.ConnectionUtil;
import com.revature.employee.FinanceManager;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.ManagerNotFoundException;
import com.revature.exceptions.NoManagerException;
import com.revature.exceptions.PasswordHashException;

public class ManagerDaoImpl implements ManagerDao {
		private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
		private static ManagerDaoImpl instance;
			
			private ManagerDaoImpl() {}
			
			public static ManagerDaoImpl getInstance() {
				if(instance == null) {
					instance = new ManagerDaoImpl();
				}
				return instance;
			}

			@Override
			public List<FinanceManager> getManagers() throws NoManagerException {
				try(Connection conn = ConnectionUtil.getConnection()){
					List<FinanceManager> man= new ArrayList<>();
					PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE INNER JOIN FINANCE_MANAGER ON man_id = EMPLOYEE.EMP_ID ");
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						man.add(new FinanceManager(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Man_First_Name"),rs.getString("Man_Last_Name"), rs.getString("man_email"),rs.getString("man_address"),rs.getInt("emp_id")));
					}
					return man;
				} catch(SQLException sqle) {
					logger.error(sqle.getMessage(), sqle);
					logger.error(sqle.getSQLState(),sqle);
					logger.error(sqle.getErrorCode(),sqle);
				} 
				throw new NoManagerException();
			}

			@Override
			public FinanceManager getManager(String man) throws ManagerNotFoundException {
				int index = 0;
				try(Connection conn = ConnectionUtil.getConnection()){
					PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FINANCE_MANAGER INNER JOIN EMPLOYEE ON FINANCE_MANAGER.MAN_ID = EMPLOYEE.EMP_ID where man_id in (SELECT emp_id from EMPLOYEE where EMP_USERNAME = ?)");
					stmt.setString(++index, man);
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next())
						return new FinanceManager(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Man_First_Name"),rs.getString("Man_Last_Name"), rs.getString("man_email"),rs.getString("man_address"), rs.getInt("emp_id"));
				} catch(SQLException sqle) {
					logger.error(sqle.getMessage(), sqle);
					logger.error(sqle.getSQLState(),sqle);
					logger.error(sqle.getErrorCode(),sqle);
				} 
				throw new ManagerNotFoundException();
			}

			@Override
			public boolean updateInfo(int id,String fName,String lName, String email, String add) throws ManagerNotFoundException {
				int index = 0;
				try(Connection conn = ConnectionUtil.getConnection()){
					PreparedStatement stmt = conn.prepareStatement("{CALL update_man(?,?,?,?,?)}");
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
				throw new ManagerNotFoundException();
			}
			
			
			@Override
			public String getPasswordHash(FinanceManager man) throws PasswordHashException{
				int index = 0;
				try (Connection conn = ConnectionUtil.getConnection()) {
					PreparedStatement stmt = conn.prepareStatement("SELECT GET_EMP_HASH(?,?)AS HASH FROM dual");
					stmt.setString(++index, man.getUsername());
					stmt.setString(++index, man.getPassword());
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
