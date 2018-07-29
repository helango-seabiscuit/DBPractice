package com.hemalatha.db.application.order.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "ORDERVENDOR")
@NamedQueries({
		@NamedQuery(name = "findVendorsByPartialName",
		query = "select v from Vendor v where LOCATE(:name, v.name) > 0"),
		@NamedQuery(name = "findVendorByCustomerOrder",
		query = "select distinct l.vendorPart.vendor from CustomerOrder co, IN(co.lineItems) l " +
				"where co.orderId = :id order by l.vendorPart.vendor.name")
})
public class Vendor {
	private int vendorId;
	private String name;
	private String address;
	private String contact;
	private String phone;
	private Collection<VendorPart> vendorParts;

	public Vendor(){
	}

	public Vendor(int vendorId, String name, String address, String contact, String phone) {
		this.vendorId = vendorId;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.phone = phone;
	}


	@Id
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	@Column(name = "VENDORNAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vendor")
	public Collection<VendorPart> getVendorParts() {
		return vendorParts;
	}

	public void setVendorParts(Collection<VendorPart> vendorParts) {
		this.vendorParts = vendorParts;
	}

	public void addVendorPart(VendorPart part){
		this.getVendorParts().add(part);
	}
}
