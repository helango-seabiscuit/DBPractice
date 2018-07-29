package com.hemalatha.db.application.order.entity;

import java.io.Serializable;
import java.util.Objects;

public class PartKey implements Serializable {
	private String partNumber;
	private int revision;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PartKey partKey = (PartKey) o;
		return revision == partKey.revision &&
				Objects.equals(partNumber, partKey.partNumber);
	}

	@Override
	public int hashCode() {

		return Objects.hash(partNumber, revision);
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}
}
