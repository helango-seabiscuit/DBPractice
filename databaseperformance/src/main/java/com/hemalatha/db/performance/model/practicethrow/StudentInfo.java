package com.hemalatha.db.performance.model.practicethrow;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class StudentInfo {

	@Id
	private int id;

	@OneToOne(mappedBy = "info")
	//@OrderBy("las, las")
	private Student student;

	public void setStudent(Student student) {
		this.student = student;
	}
}
