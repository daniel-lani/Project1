package com.revature.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.connection.ConnectionUtil;
import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.NoPendingReimbursmentException;
import com.revature.exceptions.NoReibursmentForIdException;
import com.revature.exceptions.NoReimbursementForEmployeeException;
import com.revature.exceptions.ReimbursmentApprovalException;
import com.revature.exceptions.ReimbursmentDenialException;
import com.revature.exceptions.ReimbursmentSubmissionException;
import com.revature.exceptions.noReimbursmentException;
import com.revature.reimbursement.Category;
import com.revature.reimbursement.Reimbursment;

public class ReimbursementDaoImpl implements ReimbursementDao {
	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	private static ReimbursementDaoImpl instance;
		
		private ReimbursementDaoImpl() {}
		
		public static ReimbursementDaoImpl getInstance() {
			if(instance == null) {
				instance = new ReimbursementDaoImpl();
			}
			return instance;
		}

		@Override
		public boolean submitReimbursment(Reimbursment rebur) throws ReimbursmentSubmissionException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?,?,?,?)}");
				stmt.setString(++index, rebur.getCat().toString());
				stmt.setInt(++index, rebur.getAmount());
				stmt.setInt(++index, rebur.getSumbitterId());
				stmt.setBlob(++index, rebur.getphoto());
				return stmt.executeUpdate() > 0;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new ReimbursmentSubmissionException();
		}

		@Override
		public boolean approveReimbursment(int appId, int rebId) throws ReimbursmentApprovalException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL UPDATE_REIMBURSEMENT(?,?,?)}");
				stmt.setInt(++index, appId);
				stmt.setInt(++index, rebId);
				stmt.setInt(++index, 1);
				return stmt.executeUpdate() > 0;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new ReimbursmentApprovalException();
		}

		@Override
		public boolean denyReimbursment(int appId, int reburId) throws ReimbursmentDenialException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL UPDATE_REIMBURSEMENT(?,?,?)}");
				stmt.setInt(++index, appId);
				stmt.setInt(++index, reburId);
				stmt.setInt(++index, -1);
				return stmt.executeUpdate() > 0;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new ReimbursmentDenialException();
		}

		@Override
		public Reimbursment getReimbursmentByName(String emp) throws EmployeeNotFoundException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REIMBURSEMENT WHERE SUBMITTER_ID IN (SELECT EMP_ID FROM EMPLOYEE WHERE EMPLOYEE.EMP_USERNAME = ?)");
				stmt.setString(++index, emp);
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next())
					if(rs.getBlob("photo") != null) {
					return new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"),rs.getBlob("photo").getBinaryStream());
					}
					else
						return new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"));	
					} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new EmployeeNotFoundException();
		}

		@Override
		public List<Reimbursment> getReimbursemnts() throws noReimbursmentException {
			try(Connection conn = ConnectionUtil.getConnection()){
				List<Reimbursment> rebur= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement ORDER BY rebur_id");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getBlob("photo") != null) {
						rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"),rs.getBlob("photo").getBinaryStream()));
						}
						else {
						rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));	
						}
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new noReimbursmentException();
		}

		@Override
		public List<Reimbursment> getPendingReimbursemnts() throws NoPendingReimbursmentException {
			try(Connection conn = ConnectionUtil.getConnection()){
				List<Reimbursment> rebur= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement WHERE approved = 0  ORDER BY rebur_id");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getBlob("photo")!= null) {
						rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"),rs.getBlob("photo").getBinaryStream()));
						}
						else {
						rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));	
						}
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new NoPendingReimbursmentException();
		}


		@Override
		public List<Reimbursment> getReimbursmentForEmployee(String emp) throws NoReimbursementForEmployeeException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				List<Reimbursment> rebur= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REIMBURSEMENT WHERE SUBMITTER_ID IN (SELECT EMP_ID FROM EMPLOYEE WHERE EMPLOYEE.EMP_USERNAME = ?)");
				stmt.setString(++index, emp);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getBlob("photo") != null) {
						rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"),rs.getBlob("photo").getBinaryStream()));
						}
						else {
						rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));	
						}
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new NoReimbursementForEmployeeException();
		}
		@Override
		public Reimbursment getReimbursmentById(int id) throws NoReibursmentForIdException {
			int index = 0;
			Reimbursment rebur = null;
			try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REIMBURSEMENT WHERE rebur_ID = ?");
				stmt.setInt(++index, id);
				ResultSet rs = stmt.executeQuery();
				
				if(rs.getBlob("photo")!= null) {
					return new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"),rs.getBlob("photo").getBinaryStream());
					}
					else
						return new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"));	
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new NoReibursmentForIdException();
		}
		@Override
		public List<Reimbursment> getReimbursmentByEmpId(int id) throws NoReibursmentForIdException {
			int index = 0;
			List<Reimbursment> rebur= new ArrayList<>();
			try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM REIMBURSEMENT WHERE submitter_Id = ?");
				stmt.setInt(++index, id);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					if(rs.getBlob("photo") != null) {
					rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved"),rs.getBlob("photo").getBinaryStream()));
					}
					else {
					rebur.add(new Reimbursment(Category.stringToCat(rs.getString("category")), rs.getInt("approver_id"), rs.getInt("submitter_id"),rs.getInt("rebur_id"),rs.getInt("amount"),rs.getDate("timeApproved"),rs.getDate("timeSubmitted"),rs.getInt("approved")));	
					}
				}
				return rebur;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new NoReibursmentForIdException();
		}
		
		
}
