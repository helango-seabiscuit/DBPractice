package com.hemalatha.db.performance.model.practicethrow;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeName {

	@Column(name = "F_NAME")
	private String firstName;

	@Column(name = "L_NAME")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
