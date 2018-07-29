package com.hemalatha.db.performance.model.collectionmapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.Map;

@Entity
@IdClass(DepartmentId.class)
public class Department {

	@Id
	private int id;

	@Id
	private String country;


	private String name;

//	@ManyToMany
//	@JoinTable(name = "DEPT_EMP",
//	joinColumns = @JoinColumn(name = "DEPT_ID"),
//	inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
//	@MapKeyColumn(name = "CUB_ID")
//	private Map<String,Employee> employeesByCubicle = new HashMap<>();
//
//	@OneToMany(mappedBy = "department")
//	@MapKey(name = "id") //key is the attribute in employee entity
//	private Map<Integer,Employee> employees = new HashMap<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Map<String, Employee> getEmployeesByCubicle() {
//		return employeesByCubicle;
//	}
//
//	public void setEmployeesByCubicle(Map<String, Employee> employeesByCubicle) {
//		this.employeesByCubicle = employeesByCubicle;
//	}
//
//	public Map<Integer, Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(Map<Integer, Employee> employees) {
//		this.employees = employees;
//	}
}
