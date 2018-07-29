package com.hemalatha.db.performance.model.practicethrow.throwsoon;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Location {


	public Location(String c, String s){
		this.city = c;
		this.street = s;
	}

	@Column(nullable = false)
	private String city;
	private String street;

	public Location() {
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
}
