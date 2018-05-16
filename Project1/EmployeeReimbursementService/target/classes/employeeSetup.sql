CREATE TABLE employee(
emp_username VARCHAR2(30),
emp_password VARCHAR2(100),
emp_id     NUMBER,
CONSTRAINT uk_emp_username UNIQUE (emp_username),
CONSTRAINT pk_emp_id PRIMARY KEY(emp_id));


CREATE TABLE generic_employee(
emp_first_name VARCHAR2(30) default '',
emp_last_name VARCHAR2(30) default '',
emp_email VARCHAR2(50) default '',
emp_address VARCHAR2(50) default'',
gen_emp_id NUMBER,
CONSTRAINT fk_gen_id FOREIGN KEY(gen_emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE,
CONSTRAINT pk_gen_id PRIMARY KEY(gen_emp_id));




CREATE TABLE finance_manager(
man_first_name VARCHAR2(30)default '',
man_last_name VARCHAR2(30) default '',
man_email VARCHAR2(50) default '',
man_address VARCHAR2(50) default'',
man_id NUMBER ,
CONSTRAINT FK_man_id FOREIGN KEY(man_id) REFERENCES employee(emp_id) ON DELETE CASCADE,
CONSTRAINT pk_man_id PRIMARY KEY(man_id));
drop table reimbursement;
CREATE TABLE reimbursement(
rebur_id NUMBER,
category VARCHAR2(20),
approved NUMBER(1) default 0,
approver_id NUMBER default 0,
submitter_id NUMBER,
timeSubmitted Date,
timeApproved Date,
amount number(10),
photo blob,
CONSTRAINT ckUserApproved check (approved in (0,1,-1)),
CONSTRAINT FK_submit FOREIGN KEY(submitter_id) REFERENCES employee(emp_id) ON DELETE CASCADE,
CONSTRAINT FK_approve FOREIGN KEY(approver_id) REFERENCES finance_manager(man_id) ON DELETE CASCADE,
CONSTRAINT pk_rebur_id PRIMARY KEY(rebur_id));

ALTER TABLE reimbursement
  DROP COLUMN url;

CREATE OR REPLACE PROCEDURE insert_employee (new_name IN VARCHAR2, new_pass IN VARCHAR2)
AS
BEGIN
    INSERT INTO EMPLOYEE(emp_username,emp_password,emp_id) VALUES(new_name,new_pass,NULL);
END;
/

CREATE SEQUENCE emp_id_sequence
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1;   

CREATE OR REPLACE TRIGGER emp_insert
BEFORE INSERT 
ON employee
FOR EACH ROW 
BEGIN
    IF :new.emp_id IS NULL THEN
    SELECT emp_id_sequence.nextval INTO : new.emp_id FROM dual;
    END IF;
    Update EMPLOYEE SET EMP_PASSWORD = get_emp_hash(:new.emp_username,:new.emp_password) where EMP_USERNAME = :new.emp_username;
END;
/


CREATE OR REPLACE TRIGGER gen_emp_insert
After INSERT 
ON employee
FOR EACH ROW 
BEGIN
    insert into GENERIC_EMPLOYEE(gen_emp_id) values(:new.emp_id);
END;
/

CREATE OR REPLACE FUNCTION GET_EMP_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/


CREATE OR REPLACE PROCEDURE insert_reimbursement (new_category IN VARCHAR2, new_amount IN NUMBER,new_submitter_id IN NUMBER)
AS
BEGIN
    INSERT INTO reimbursement(category,amount,SUBMITTER_ID) VALUES(new_category,new_amount,new_submitter_id);
END;
/


CREATE OR REPLACE PROCEDURE update_reimbursement(new_approver_id IN NUMBER,new_rebur_id IN NUMBER,new_status IN NUMBER)
AS
BEGIN
    UPDATE reimbursement SET approver_id = new_approver_id WHERE REBUR_ID = new_rebur_id;
    UPDATE reimbursement set timeApproved = CURRENT_TIMESTAMP WHERE REBUR_ID = new_rebur_id;
    UPDATE reimbursement set approved = new_status where rebur_id = new_rebur_id;
    commit;
END;
/


CREATE OR REPLACE PROCEDURE update_emp(up_id in NUMBER, new_fname in NVARCHAR2, new_lname in NVARCHAR2, new_email in NVARCHAR2, new_add in NVARCHAR2)
AS
BEGIN
IF new_fname IS NOT NULL THEN
    Update GENERIC_EMPLOYEE set emp_first_name = new_fname where up_id = gen_emp_id;
END IF;
IF new_lname IS NOT NULL THEN
    Update GENERIC_EMPLOYEE set emp_last_name = new_lname where up_id = gen_emp_id;
END IF;
IF new_email IS NOT NULL THEN
    Update GENERIC_EMPLOYEE set emp_email = new_email where up_id = gen_emp_id;
END IF;
IF new_add IS NOT NULL THEN
    Update GENERIC_EMPLOYEE set emp_address = new_add where up_id = gen_emp_id;
END IF;
COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE upgrade(new_id in Number)
AS
Begin
insert into FINANCE_MANAGER(man_id) values(new_id);
End;
/

CREATE OR REPLACE PROCEDURE update_man(up_id in NUMBER, new_fname in NVARCHAR2, new_lname in NVARCHAR2, new_email in NVARCHAR2, new_add in NVARCHAR2)
AS
BEGIN
delete from GENERIC_EMPLOYEE where gen_emp_id = up_id;
IF new_fname IS NOT NULL THEN
    Update FINANCE_MANAGER set man_first_name = new_fname where man_id = up_id;
END IF;
IF new_lname IS NOT NULL THEN
    Update FINANCE_MANAGER set man_last_name = new_lname where man_id = up_id;
END IF;
IF new_email IS NOT NULL THEN
    Update FINANCE_MANAGER set man_email = new_email where man_id = up_id;
END IF;
IF new_add IS NOT NULL THEN
    Update FINANCE_MANAGER set man_address = new_add where man_id = up_id;
END IF;
COMMIT;
END;
/
    
CREATE SEQUENCE rebur_id_sequence
    MAXVALUE 10000
    START WITH 1
    INCREMENT BY 1;  

CREATE OR REPLACE TRIGGER rebur_insert
BEFORE INSERT 
ON reimbursement
FOR EACH ROW 
BEGIN
    IF :new.rebur_id IS NULL THEN
    SELECT rebur_id_sequence.nextval INTO : new.rebur_id FROM dual;
    END IF;
    IF :new.approver_id IS NULL THEN
    SELECT 0 INTO : new.approver_id from dual;
    end if;
    SELECT CURRENT_TIMESTAMP into : new.timeSubmitted from dual;
END;
/

CREATE OR REPLACE TRIGGER rebur_approve
After Update 
ON reimbursement
FOR EACH ROW 
BEGIN
    IF :new.rebur_id IS NULL THEN
    SELECT rebur_id_sequence.nextval INTO : new.rebur_id FROM dual;
    END IF;
    IF :new.approver_id IS NULL THEN
    SELECT 0 INTO : new.approver_id from dual;
    end if;
    SELECT CURRENT_TIMESTAMP into : new.timeApproved from dual;
END;
/

begin
upgrade(4);
UPDATE_Man(4,'megan','smith',null,null);
end;
/
insert into employee values('megan','password',4);
insert into generic_employee values ('megan','smith','','',4);
select * from FINANCE_MANAGER;
select * from generic_employee;
commit;
