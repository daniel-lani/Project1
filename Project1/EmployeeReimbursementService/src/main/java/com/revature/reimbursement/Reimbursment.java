package com.revature.reimbursement;

import java.io.InputStream;
import java.sql.Date;

public class Reimbursment {
private Category cat;
private InputStream photo;
private int approverId,sumbitterId,reimburseId,amount;
private Date timeApproved,timeSubmitted;
private boolean approved;



public Reimbursment() {
	
}

public Reimbursment(Category cat, int approverId, int submitterId, int reimburseId, int amount, Date timeApproved,
		Date timeSubmitted, int approved,InputStream photo) {
	super();
	this.cat = cat;
	this.approverId = approverId;
	this.sumbitterId = submitterId;
	this.reimburseId = reimburseId;
	this.amount = amount;
	this.timeApproved = timeApproved;
	this.timeSubmitted = timeSubmitted;
	this.approved = approved == 1;
	this.photo = photo;
}

public Reimbursment(Category cat, int approverId, int submitterId, int reimburseId, int amount, Date timeApproved,
		Date timeSubmitted, int approved) {
	super();
	this.cat = cat;
	this.approverId = approverId;
	this.sumbitterId = submitterId;
	this.reimburseId = reimburseId;
	this.amount = amount;
	this.timeApproved = timeApproved;
	this.timeSubmitted = timeSubmitted;
	this.approved = approved == 1;
}

public Reimbursment(Category cat, int amount,int reimburseId, int approved) {
	super();
	
	this.cat = cat;
	this.amount = amount;
	this.reimburseId = reimburseId;
	this.approved = approved == 1;
}
public Reimbursment(Category cat, int amount,int sumbitterId,InputStream photo) {
	super();
	this.photo = photo;
	this.cat = cat;
	this.amount = amount;
	this.sumbitterId = sumbitterId;
}

public InputStream getphoto() {
	return photo;
}

public void setphoto(InputStream photo) {
	this.photo = photo;
}

public Category getCat() {
	return cat;
}
public void setCat(Category cat) {
	this.cat = cat;
}
public int getApproverId() {
	return approverId;
}
public void setApproverId(int approverId) {
	this.approverId = approverId;
}
public int getSumbitterId() {
	return sumbitterId;
}
public void setSumbitterId(int sumbitterId) {
	this.sumbitterId = sumbitterId;
}
public int getReimburseId() {
	return reimburseId;
}
public void setReimburseId(int reimburseId) {
	this.reimburseId = reimburseId;
}
public boolean isApproved() {
	return approved;
}
public void setApproved(boolean approved) {
	this.approved = approved;
}

public int getAmount() {
	return amount;
}


public void setAmount(int amount) {
	this.amount = amount;
}


public Date getTimeApproved() {
	return timeApproved;
}


public void setTimeApproved(Date timeApproved) {
	this.timeApproved = timeApproved;
}


public Date getTimeSubmitted() {
	return timeSubmitted;
}


public void setTimeSubmitted(Date timeSubmitted) {
	this.timeSubmitted = timeSubmitted;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (approved ? 1231 : 1237);
	result = prime * result + approverId;
	result = prime * result + ((cat == null) ? 0 : cat.hashCode());
	result = prime * result + reimburseId;
	result = prime * result + sumbitterId;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Reimbursment other = (Reimbursment) obj;
	if (approved != other.approved)
		return false;
	if (approverId != other.approverId)
		return false;
	if (cat != other.cat)
		return false;
	if (reimburseId != other.reimburseId)
		return false;
	if (sumbitterId != other.sumbitterId)
		return false;
	return true;
}
}
