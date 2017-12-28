package com.scp.HQL;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeInterfaceImpl implements EmployeeInterface {

	public void insertEmployee(Employee e) throws  MyException 
	{
		Session session= Hibernateutil.getSessionFactory().openSession();
		Transaction trans= session.beginTransaction();
		session.save(e);
		trans.commit();
		//Hibernateutil.resourceCleanupActivities(session, trans);
	}


	public void getAllEmployee() throws MyException {
		Session session= Hibernateutil.getSessionFactory().openSession();
		String hql="from Employee";
		Query qry= session.createQuery(hql); 
		List list= qry.list();
		Iterator itr= list.iterator();
		while (itr.hasNext()) {
			Employee e = (Employee)itr.next();
			System.out.println(e.getEmpId()+"  "+e.getEmpName()+"  "+e.getEmpSal()+"  "+e.getDeptId());;
		}
		
	}

	
	public void searchEmployee(int id) throws  MyException 
	{
		Session session= Hibernateutil.getSessionFactory().openSession();
		String hql="from Employee e where e.empId=?";
		Query qry= session.createQuery(hql); 
		qry.setParameter(0, id);
		List list= qry.list();
		Iterator itr= list.iterator();
		while (itr.hasNext()) {
			Employee e = (Employee)itr.next();
			System.out.println("\n");
			System.out.println(e.getEmpId()+"  "+e.getEmpName()+"  "+e.getEmpSal()+"  "+e.getDeptId());;
		}
		
	}

	public void getEmployeeOfMaxSal(int sal) throws MyException {
		Session session= Hibernateutil.getSessionFactory().openSession();
	/*
		*//**
		 * Indexed parameter--when we use ? in query.
		 * ----? 
		 *//*
		String hql="from Employee e where e.empSal > ?";
		Query qry= session.createQuery(hql); 
		qry.setParameter(0, sal);
	*/	
		/**
		 * Named parameter--when we use : in query.
		 * ----? 
		 */
		String hql="from Employee e where e.empSal > :p1";
		Query qry= session.createQuery(hql); 
		qry.setParameter("p1", sal);
		
		List list= qry.list();
		Iterator itr= list.iterator();
		while (itr.hasNext()) {
			Employee e = (Employee)itr.next();
			System.out.println("\n");
			System.out.println(e.getEmpId()+"  "+e.getEmpName()+"  "+e.getEmpSal()+"  "+e.getDeptId());;
		}
		
	}


	public void updateEmployee(int empid) throws  MyException 
	{
		Session session= Hibernateutil.getSessionFactory().openSession();
		String hql="update Employee e set e.empSal=9999  where e.empId=?";
		Query qry= session.createQuery(hql); 
		qry.setParameter(0, empid);
		Transaction trans= session.beginTransaction();
		int i= qry.executeUpdate();
		trans.commit();
		System.out.println("number of rows affected-- "+i);
	}
}
