package com.hemalatha.db.performance.model.practicethrow.throwsoon;


import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import java.util.List;

@Entity
public class PersonDetails {

	@Id
	private int id;

	@ElementCollection
	@CollectionTable(name = "person_locations",joinColumns = @JoinColumn(name = "person_id"))
	@OrderColumn
	private List<Location> locations;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
