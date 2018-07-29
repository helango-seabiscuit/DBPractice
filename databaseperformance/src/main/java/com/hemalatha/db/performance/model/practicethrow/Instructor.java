package com.hemalatha.db.performance.model.practicethrow;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Instructor {

	@Id
	private int id;

	private String name;

	@OneToOne
	private Course course;
}
