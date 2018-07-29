package com.hemalatha.db.performance.model.practicethrow;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Version;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Collection;
import java.util.Set;

@Entity
@StaticMetamodel(Student.class)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Version
	private int version;

	@OneToOne(cascade = CascadeType.ALL)
	private StudentInfo info;

	@ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name = "person_ids"))
	private Set<String> nickNames;

	private String grade;

	private final  String test;

	public  Student (){
		test = "test";
	}

	public void setInfo(StudentInfo info) {
		this.info = info;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@PostLoad
	public void after(){
		System.out.println("after load");
	}

	//	@OneToMany
//  Collection<Presentation> presentations;
//
//	@OneToMany(mappedBy = "students")
//	Collection<Course> courses;

	public static  final  void test(){
		System.out.println("");
	}

}
