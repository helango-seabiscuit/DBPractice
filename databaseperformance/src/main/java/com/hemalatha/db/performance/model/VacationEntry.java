package com.hemalatha.db.performance.model;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

public class VacationEntry {

	@Temporal(TemporalType.DATE)
	private Calendar startDate;

	@Column(name = "DAYS")
	private int daysTaken;
}
