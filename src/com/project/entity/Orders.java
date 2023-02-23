package com.project.entity;
public class Orders {
	private int orderId;
	private Vendors vendors;
	private String orderDate;
	private Inventory inventory;
	private int orderQuantity;
	public Orders() {
		super();
	}
	public Orders(int orderId, Vendors vendors, String orderDate, Inventory inventory, int orderQuantity) {
		super();
		this.orderId = orderId;
		this.vendors = vendors;
		this.orderDate = orderDate;
		this.inventory = inventory;
		this.orderQuantity = orderQuantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Vendors getVendors() {
		return vendors;
	}
	public void setVendors(Vendors vendors) {
		this.vendors = vendors;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String date) {
		this.orderDate = date;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}	

}
