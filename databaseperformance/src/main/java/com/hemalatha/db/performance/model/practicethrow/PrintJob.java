package com.hemalatha.db.performance.model.practicethrow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrintJob {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private PrintQueue queue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PrintQueue getQueue() {
		return queue;
	}

	public void setQueue(PrintQueue queue) {
		this.queue = queue;
	}
}
