package com.hemalatha.db.performance.model.collectionmapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Phone {

	@Id
	private String number;
	private Employee.PhoneType type;

	@ManyToMany(mappedBy = "contactInfo.phones")
	private List<Employee> employees;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Employee.PhoneType getType() {
		return type;
	}

	public void setType(Employee.PhoneType type) {
		this.type = type;
	}
}
