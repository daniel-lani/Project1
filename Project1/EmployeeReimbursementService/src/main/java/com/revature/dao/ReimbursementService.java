package com.revature.dao;

import java.util.List;

import com.revature.exceptions.EmployeeNotFoundException;
import com.revature.exceptions.NoPendingReimbursmentException;
import com.revature.exceptions.NoReibursmentForIdException;
import com.revature.exceptions.NoReimbursementForEmployeeException;
import com.revature.exceptions.ReimbursmentApprovalException;
import com.revature.exceptions.ReimbursmentDenialException;
import com.revature.exceptions.ReimbursmentSubmissionException;
import com.revature.exceptions.noReimbursmentException;
import com.revature.reimbursement.Reimbursment;

public class ReimbursementService{
	private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
	
	public static boolean submitReimbursment(Reimbursment rebur) throws ReimbursmentSubmissionException {
		return dao.submitReimbursment(rebur);
	}
	
	public static boolean approveReimbursment(int appId, int reburId) throws ReimbursmentApprovalException {
	   return dao.approveReimbursment(appId, reburId);
	}
	
	public static boolean denyReimbursment(int appId, int reburId) throws ReimbursmentDenialException {
		return dao.denyReimbursment(appId, reburId);
	}
	
	public static List<Reimbursment> getReimbursemnts() throws noReimbursmentException{
		return dao.getReimbursemnts();
	}
	
	public static List<Reimbursment> getPendingReimbursemnts() throws NoPendingReimbursmentException{
		return dao.getPendingReimbursemnts();
	}

	public static List<Reimbursment> getReimbursmentForEmployee(String emp) throws NoReimbursementForEmployeeException{
		return dao.getReimbursmentForEmployee(emp);
	}
	public static Reimbursment getReimbursmentById(int id) throws NoReibursmentForIdException {
		return dao.getReimbursmentById(id);
	}
	public static List<Reimbursment> getReimbursmentByEmpId(int id) throws NoReibursmentForIdException {
		return dao.getReimbursmentByEmpId(id);
	} 
}
