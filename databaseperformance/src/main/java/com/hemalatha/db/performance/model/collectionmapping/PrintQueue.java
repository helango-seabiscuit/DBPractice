package com.hemalatha.db.performance.model.collectionmapping;

import com.hemalatha.db.performance.model.collectionmapping.PrintJob;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.List;

@Entity
public class PrintQueue {

	@Id
	private String name;

	@OneToMany(mappedBy = "queue",cascade = CascadeType.ALL)
	@OrderColumn(name = "PRINT_ORDER")
	private List<PrintJob> jobs;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PrintJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<PrintJob> jobs) {
		this.jobs = jobs;
	}
}
