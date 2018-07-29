package com.hemalatha.db.performance.model.practicethrow;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Embeddable
public class VacationEntry {

	@Temporal(TemporalType.DATE)
	private Calendar startDate;

	@Column(name = "days")
	private int daysTaken;
}
