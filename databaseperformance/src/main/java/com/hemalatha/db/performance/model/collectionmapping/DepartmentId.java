package com.hemalatha.db.performance.model.collectionmapping;

import java.io.Serializable;
import java.util.Objects;

public class DepartmentId implements Serializable{
	private String country;
	private int id;


	public DepartmentId() {
	}

	public DepartmentId(String country, int id) {
		this.country = country;
		this.id = id;
	}

	@Override
	public int hashCode() {
		return country.hashCode() + id;
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this,obj);
	}

}
