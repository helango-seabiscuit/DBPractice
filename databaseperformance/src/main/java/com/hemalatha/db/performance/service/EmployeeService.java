package com.hemalatha.db.performance.service;


import com.hemalatha.db.performance.model.collectionmapping.Department;
import com.hemalatha.db.performance.model.collectionmapping.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

public class EmployeeService {

	EntityManagerFactory emf;
	EntityManager manager;
	EntityManager manager2;
	public  EmployeeService(){
		emf = Persistence.createEntityManagerFactory("MyPGPU");
		manager = emf.createEntityManager();
	}

	public  EmployeeService(EntityManager manager){
		this.manager = manager;
	}

	public  EmployeeService(EntityManager manager,EntityManager manager2){
		this.manager = manager;
		this.manager2 = manager2;
	}


	public long generateDepSalaryReport(List<Integer> deptIds){
		long total = 0;
		for (int i:deptIds){
			long deptTotal = totalSalaryInDepartment(i);
			total += deptTotal;
		}
		return total;
	}

	private long totalSalaryInDepartment(int i) {
		manager.getTransaction().begin();
		long total = 0;
		Department department = manager.find(Department.class,i);
//		Map<Integer,Employee> e = department.getEmployees();
//		for(Employee emp:e.values()){
//			//manager.lock(emp, LockModeType.OPTIMISTIC);
//			total += emp.getId();
//			changeEmployeeDepartment(1,1);
//		}
		manager.getTransaction().commit();
		return total;
	}

	public void changeEmployeeDepartment(int deptId,int empId){
		manager2.getTransaction().begin();
		Employee employee = manager2.find(Employee.class,empId);
		//Department department = manager.find(Department.class, deptId);
		//employee.getDepartment().getEmployees().computeIfPresent(employee.getId(),(k,v)-> null);
		employee.setDepartment(null);
		employee.getId();
		//department.getEmployees().put(employee.getId(),employee);
		manager2.getTransaction().commit();
	}
}
