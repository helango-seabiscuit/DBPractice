package com.hemalatha.db.performance.model.practicethrow;

import com.hemalatha.db.performance.model.collectionmapping.Employee;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Entity
public class EmployeeModel {
	@Id
	private int eid;

	private String name;

//	@Column(name = "F_NAME")
//	private String firstName;
//
//	@Column(name = "L_NAME")
//	private String lastName;

	@Embedded
	private EmployeeName employeeName;

	private long salary;

	@ElementCollection
	@Column(name = "names")
	private Set<String> nickNames;

	@ElementCollection
	@CollectionTable(name = "vacation",joinColumns = @JoinColumn(name = "emp_id"))
	@AttributeOverride(name = "daysTaken",column = @Column(name = "DAYS_ABS"))
	private Collection<VacationEntry> vacationBookings;

	@ElementCollection
	@CollectionTable(name = "EMP_PHONE",joinColumns = @JoinColumn(name = "emp_id"))
	@MapKeyColumn(name = "phone_type")
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "phone_num")
	//@JoinColumn(name = "emp_id")
	private Map<Employee.PhoneType,String> phoneNumbers;

//	@ManyToOne
//	@JoinColumn(name = "dept_id")
//	private DepartmentModel department;




}
