package com.hemalatha.db.application.order.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ORDERVENDORPART",
uniqueConstraints = @UniqueConstraint(columnNames = {"PARTNUMBER","PARTREVISION"}))
@NamedQueries({
		@NamedQuery(name = "findAverageVendorPartPrice",
				query = "select avg(vp.price) from VendorPart vp"),
		@NamedQuery(name = "findTotalVendorPartPricePerVendor",
				query = "select sum(vp.price) from VendorPart vp where vp.vendor.vendorId = :id"),
		@NamedQuery(name = "findAllVendorParts",
				query = "select vp from VendorPart vp order by vp.vendorPartNumber")
})
public class VendorPart {

	private Long vendorPartNumber;
	private String description;
	private double price;
	private Part part;
	private Vendor vendor;

	public VendorPart() {
	}

	public VendorPart(String description, double price, Part part) {
		this.description = description;
		this.price = price;
		this.part = part;
	}

	@TableGenerator(name = "vendorPartGen",
	table = "ORDER_SEQ_GENERATOR",
	pkColumnName = "GEN_KEY",
	valueColumnName = "GEN_VALUE",
	pkColumnValue = "VENDOR_PART_ID",
	allocationSize = 10)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "vendorPartGen")
	public Long getVendorPartNumber() {
		return vendorPartNumber;
	}

	public void setVendorPartNumber(Long vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "PARTNUMBER",referencedColumnName = "PARTNUMBER"),
			@JoinColumn(name = "PARTREVISION", referencedColumnName = "REVISION")
	})
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@ManyToOne
	@JoinColumn(name = "vendorId")
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
}
