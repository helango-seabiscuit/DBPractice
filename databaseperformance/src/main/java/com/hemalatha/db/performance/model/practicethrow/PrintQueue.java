package com.hemalatha.db.performance.model.practicethrow;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class PrintQueue {

	@Id
	private String name;

	//@CreationTimestamp
	private ZonedDateTime createTime;

	@OneToMany(mappedBy = "queue")
	@OrderColumn(name = "print_order")
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
