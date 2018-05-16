package ersTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.revature.dao.ReimbursementService;
import com.revature.exceptions.NoReibursmentForIdException;
import com.revature.exceptions.NoReimbursementForEmployeeException;
import com.revature.reimbursement.Reimbursment;

public class ReimbursmentTest {

	@Test
	public void getRebByName() throws NoReimbursementForEmployeeException {
		List<Reimbursment> rebs = ReimbursementService.getReimbursmentForEmployee("matt");
		
		assertEquals(rebs.get(0).getReimburseId(),61);
		assertEquals(rebs.get(1).getReimburseId(),41);
		assertEquals(rebs.get(2).getReimburseId(),42);
	}
	@Test(expected = NoReimbursementForEmployeeException.class)
	public void getRebByNameNotAName() throws NoReimbursementForEmployeeException {
		List<Reimbursment> rebs = ReimbursementService.getReimbursmentForEmployee("notAName");
	}

	@Test
	public void getRebByEmpId() throws NoReibursmentForIdException {
		List<Reimbursment> rebs = ReimbursementService.getReimbursmentByEmpId(1);
		
		assertEquals(rebs.get(0).getReimburseId(),61);
		assertEquals(rebs.get(1).getReimburseId(),41);
		assertEquals(rebs.get(2).getReimburseId(),42);
	}
	@Test(expected = NoReibursmentForIdException.class)
	public void getRebByEmpIdBadId() throws NoReibursmentForIdException {
		List<Reimbursment> reb = ReimbursementService.getReimbursmentByEmpId(-2);
	}
	
	@Test
	public void getRebById() throws NoReibursmentForIdException {
		Reimbursment reb = ReimbursementService.getReimbursmentById(62);
		
		assertEquals(reb.getCat().toString(),"lodging");
		assertEquals(reb.getAmount(),561);
		assertEquals(reb.getApproverId(),2);
	}
	@Test(expected = NoReibursmentForIdException.class)
	public void getRebByIdBadId() throws NoReibursmentForIdException {
		Reimbursment reb = ReimbursementService.getReimbursmentById(-2);
	}

}
