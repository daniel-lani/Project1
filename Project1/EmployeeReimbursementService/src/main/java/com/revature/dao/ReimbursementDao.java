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

public interface ReimbursementDao {
	public boolean submitReimbursment(Reimbursment rebur) throws ReimbursmentSubmissionException;
	
	public boolean approveReimbursment(int appId, int reburId) throws ReimbursmentApprovalException;
	
	public boolean denyReimbursment(int appId, int reburId) throws ReimbursmentDenialException;
	
	public Reimbursment getReimbursmentByName(String emp) throws EmployeeNotFoundException;
	
	public List<Reimbursment> getReimbursemnts() throws noReimbursmentException;
	
	public List<Reimbursment> getPendingReimbursemnts() throws NoPendingReimbursmentException;
	
	public List<Reimbursment> getReimbursmentForEmployee(String emp) throws NoReimbursementForEmployeeException;

	public Reimbursment getReimbursmentById(int id) throws NoReibursmentForIdException;

	public List<Reimbursment> getReimbursmentByEmpId(int id) throws NoReibursmentForIdException;
}
