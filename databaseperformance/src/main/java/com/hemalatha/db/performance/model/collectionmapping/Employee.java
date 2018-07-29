package com.hemalatha.db.performance.model.collectionmapping;

import com.hemalatha.db.performance.converters.BoolensToIntegerConverter;
import com.hemalatha.db.performance.converters.CaseConverter;
import com.hemalatha.db.performance.converters.VersionConverter;
import com.hemalatha.db.performance.model.VacationEntry;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Entity
@NamedQuery(name = "findEmployeeByDepartment",
query = "select e from Employee e join e.department d where d.name=:depName")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empidgen")
	@SequenceGenerator(name = "empidgen",sequenceName = "emp_idgen_seq")
	private int id;

	@Version
	private int version;

	private String fullName;

	@Embedded
	@AssociationOverrides({@AssociationOverride(name = "primaryPhone",joinColumns = @JoinColumn(name = "PRIM_EMP_PHONE"))})
	private ContactInfo contactInfo;

	@ElementCollection(targetClass = VacationEntry.class)
	@CollectionTable(name = "vacation",
	     joinColumns = @JoinColumn(name="emp_id"))
	@AttributeOverride(name = "daysTaken",
	                column = @Column(name = "DAYS_ABS"))
	private Collection vacationEntry;

	@ElementCollection
	private Set<String> nickNames;

	@ElementCollection
	@CollectionTable(name = "EMP_PHONE")
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name = "PHONE_TYPE")
	@Column(name = "PHONE_NUM")
	//@Convert(converter = CaseConverter.class,attributeName = "value")
//	@Convert(converter = CaseConverter.class,attributeName = "key")
	private Map<PhoneType,String> phoneNumbers;

	@ManyToOne
	//@JoinColumn(name = "dept_id")
	@JoinColumns(value = {@JoinColumn(name = "dept_id",referencedColumnName = "id"),@JoinColumn(name ="country",referencedColumnName = "country")})
	private Department department;



	@Convert(converter = BoolensToIntegerConverter.class, attributeName = "isSecurityCleared")
	private SecurityInfo securityInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Collection getVacationEntry() {
		return vacationEntry;
	}

	public void setVacationEntry(Collection vacationEntry) {
		this.vacationEntry = vacationEntry;
	}

	public Set<String> getNickNames() {
		return nickNames;
	}

	public void setNickNames(Set nickNames) {
		this.nickNames = nickNames;
	}

	public Map<PhoneType, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public SecurityInfo getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(SecurityInfo securityInfo) {
		this.securityInfo = securityInfo;
	}

	public static enum PhoneType{
		Home,
		Mobile,
		Work;
	}
}
