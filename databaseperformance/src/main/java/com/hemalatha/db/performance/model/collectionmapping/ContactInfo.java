package com.hemalatha.db.performance.model.collectionmapping;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import java.util.Map;

@Embeddable
public class ContactInfo {

	@Embedded
	private Address address;

	@ManyToOne
	//@JoinColumn(name = "PRI_NUM")
	private Phone primaryPhone;


	@ManyToMany
	@MapKey(name = "type")
	@JoinTable(name = "EMP_PHONES_ENT")
	private Map<String,Phone> phones;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(Phone primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public Map<String, Phone> getPhones() {
		return phones;
	}

	public void setPhones(Map<String, Phone> phones) {
		this.phones = phones;
	}
}
