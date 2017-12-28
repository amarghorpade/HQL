package com.scp.HQL;

public interface EmployeeInterface
{
	void insertEmployee(Employee e) throws MyException;
	void searchEmployee(int empId)throws MyException;
	void updateEmployee(int empId)throws MyException;
	void getAllEmployee()throws MyException;
	void getEmployeeOfMaxSal(int sal)throws MyException;
}