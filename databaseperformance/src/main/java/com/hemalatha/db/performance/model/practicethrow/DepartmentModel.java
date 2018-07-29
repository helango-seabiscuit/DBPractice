package com.hemalatha.db.performance.model.practicethrow;

import com.hemalatha.db.performance.model.collectionmapping.Employee;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import java.util.Map;

@Entity
public class DepartmentModel {

	@Id
	private int id;

	private String name;

//	@ElementCollection
//	@CollectionTable(name = "EMP_SENIORITY")
//	@MapKeyJoinColumn(name = "EMP_ID")
//	private Map<EmployeeModel,Integer> seniorities;

//	@OneToMany(mappedBy = "department")
//	@MapKeyColumn(name = "cub_id")
//	private Map<String,EmployeeModel> employeesByCubicle;

//	@ManyToMany
//	@JoinTable(name = "DEPT_EMP",
//			joinColumns = @JoinColumn(name = "DEPT_ID"),
//			inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
//	@AttributeOverrides({
//			@AttributeOverride(name = "firstName",column = @Column(name = "EMP_FNAME")),
//			@AttributeOverride(name = "lastName",column = @Column(name = "EMP_LNAME"))
//	})
//	private Map<EmployeeName,EmployeeModel> employeesByName;

//	@ManyToMany
//	@JoinTable(name = "DEPT_EMP",
//	  joinColumns = @JoinColumn(name = "DEPT_ID"),
//	  inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
//	@MapKeyColumn(name = "CUB_ID")
//	private Map<String,EmployeeModel> employeesByMultiCubicle;

//	@OneToMany(mappedBy = "department")
//	private Map<EmployeeName,EmployeeModel> employees;


}
