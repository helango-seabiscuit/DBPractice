package com.hemalatha.db.application.order.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "CUSTOMERORDER")
@NamedQuery(name = "findAllOrders",
		    query = "select co from CustomerOrder co order by co.orderId")
public class CustomerOrder {

	private static final long serialVersionUID = 1l;
	private Integer orderId;
	private char status;
	private Date lastUpdate;
	private int discount;
	private String shipmentInfo;
	private Collection<LineItem> lineItems;

	public CustomerOrder(){
		this.lastUpdate = new Date();
		this.lineItems = new ArrayList<>();
	}

	public CustomerOrder(Integer orderId, char status, int discount, String shipmentInfo) {
		this.orderId = orderId;
		this.status = status;
		this.discount = discount;
		this.shipmentInfo = shipmentInfo;
		this.lineItems = new ArrayList<>();
	}


	@Id
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getShipmentInfo() {
		return shipmentInfo;
	}

	public void setShipmentInfo(String shipmentInfo) {
		this.shipmentInfo = shipmentInfo;
	}


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerOrder")
	public Collection<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Collection<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public double calculateAmount() {
		double amount = 0;
		Collection<LineItem> items = getLineItems();
		for(LineItem item:items){

		}

		return (amount * (100 - getDiscount()))/100;
	}

	public  void addLineItem(LineItem lineItem){
		this.getLineItems().add(lineItem);
	}

	@Transient
	public int getNextId(){
		return this.lineItems.size() +1;
	}
}
