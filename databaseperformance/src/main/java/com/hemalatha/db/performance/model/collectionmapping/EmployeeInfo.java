package com.hemalatha.db.performance.model.collectionmapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class EmployeeInfo {

	@Id
	private int id;

	@OneToOne
	@JoinColumn(name = "emp_id")
	@MapsId
	private Employee employee;

	private String description;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
