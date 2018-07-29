package com.hemalatha.db.performance.model.practicethrow;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Collection;

@Entity
public class Course {

	@Id
	private float id;


//	@ManyToOne
//	@JoinTable
//	private Student students;

	@OneToOne
	private Instructor instructor;
}
