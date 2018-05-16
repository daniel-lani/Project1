package com.revature.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	private static final Logger logger = Logger.getLogger(ConnectionUtil.class);
	private ConnectionUtil() {
		
	}
	public static Connection getConnection(){
		String url ="jdbc:oracle:thin:@project1.cwcyxr5bvd9v.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username="DLani";
		String password="password";
		Connection conn;
		
		try {
			DriverManager.registerDriver(new
					oracle.jdbc.driver.OracleDriver());
					conn = DriverManager.getConnection(url,username,password);
			return conn;
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
			
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle.getSQLState(),sqle);
			logger.error(sqle.getErrorCode(),sqle);
		}
		return null;
		}


}
