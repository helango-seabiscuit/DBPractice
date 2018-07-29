package com.hemalatha.db.application.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LINEITEM")
@IdClass(LineItemKey.class)
@NamedQueries({
		@NamedQuery(name = "findAllLineItems",
				query = "select l from LineItem l"),
		@NamedQuery(name = "findLineItemsByOrderId",
				query = "select l from LineItem l where l.customerOrder.orderId = :orderId " +
						"order by l.itemId"),
		@NamedQuery(name = "findLineByItemId",
				query = "select distinct l from LineItem l where " +
						"l.itemId = :itemId and l.customerOrder.orderId = :orderId")
})
public class LineItem {

   private static final long serialVersionUID = 1l;
   private int itemId;
   private int quantity;
   private VendorPart vendorPart;
   private CustomerOrder customerOrder;

	public LineItem() {
	}

	public LineItem(int quantity, VendorPart vendorPart, CustomerOrder customerOrder) {
		this.quantity = quantity;
		this.vendorPart = vendorPart;
		this.customerOrder = customerOrder;
		this.itemId = customerOrder.getNextId();
	}

	@Id
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@ManyToOne
	@JoinColumn(name = "VENDORPARTNUMBER")
	public VendorPart getVendorPart() {
		return vendorPart;
	}

	public void setVendorPart(VendorPart vendorPart) {
		this.vendorPart = vendorPart;
	}

	@Id
	@ManyToOne
	@JoinColumn(name = "ORDERID")
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}
}
