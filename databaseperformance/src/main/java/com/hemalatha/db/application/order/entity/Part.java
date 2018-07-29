package com.hemalatha.db.application.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERPART")
@IdClass(PartKey.class)
@SecondaryTable(name = "ORDER_PARTDETAIL",pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "PARTNUMBER",referencedColumnName = "PARTNUMBER"),
		@PrimaryKeyJoinColumn(name = "REVISION", referencedColumnName = "REVISION")
})
public class Part {

	private String partNumber;
	private int revision;
	private String description;
	private Date revieionDate;
	private Serializable drawing;
	private String specification;
	private Part bomPart;
	private List<Part> parts;
	private VendorPart vendorPart;

	public Part() {
	}

	public Part(String partNumber, int revision, String description, Date revieionDate, Serializable drawing, String specification) {
		this.partNumber = partNumber;
		this.revision = revision;
		this.description = description;
		this.revieionDate = revieionDate;
		this.drawing = drawing;
		this.specification = specification;
		this.parts = new ArrayList<>();
	}

	@Id
	@Column(nullable = false)
	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	@Id
	@Column(nullable = false)
	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	public Date getRevieionDate() {
		return revieionDate;
	}

	public void setRevieionDate(Date revieionDate) {
		this.revieionDate = revieionDate;
	}

	@Lob
	@Column(table = "ORDER_PARTDETAIL")
	public Serializable getDrawing() {
		return drawing;
	}

	public void setDrawing(Serializable drawing) {
		this.drawing = drawing;
	}

	@Lob
	@Column(table = "ORDER_PARTDETAIL")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}


	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "BOMPARTNUMBER",referencedColumnName = "PARTNUMBER"),
			@JoinColumn(name = "BOMREVISION",referencedColumnName = "REVISION")

	})
	public Part getBomPart() {
		return bomPart;
	}

	public void setBomPart(Part bomPart) {
		this.bomPart = bomPart;
	}

	@OneToMany(mappedBy = "bomPart")
	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	@OneToOne(mappedBy = "part")
	public VendorPart getVendorPart() {
		return vendorPart;
	}

	public void setVendorPart(VendorPart vendorPart) {
		this.vendorPart = vendorPart;
	}
}
