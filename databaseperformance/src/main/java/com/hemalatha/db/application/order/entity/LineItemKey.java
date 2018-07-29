package com.hemalatha.db.application.order.entity;

import java.io.Serializable;
import java.util.Objects;

public class LineItemKey implements Serializable {

	private Integer customerOrder;
	private int itemId;

	public LineItemKey() {
	}

	public LineItemKey(Integer customerOrder, int itemId) {
		this.customerOrder = customerOrder;
		this.itemId = itemId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LineItemKey that = (LineItemKey) o;
		return itemId == that.itemId &&
				Objects.equals(customerOrder, that.customerOrder);
	}

	@Override
	public int hashCode() {

		return Objects.hash(customerOrder ==null? 0:customerOrder, itemId);
	}

	public Integer getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(Integer customerOrder) {
		this.customerOrder = customerOrder;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
