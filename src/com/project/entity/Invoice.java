package com.project.entity;

public class Invoice {
	private String invoiceId;
	private Orders orders;
	private Vendors vendors;
	private int invoiceAmount;
	private double amountPerUnit;
	private String paymentDate;
	private String paymentMode;
	public Invoice() {
		super();
	}
	public Invoice(String invoiceId, Orders orders, Vendors vendors,int invoiceAmount, double amountPerUnit,
			String paymentDate, String paymentMode) {
		super();
		this.invoiceId = invoiceId;
		this.orders = orders;
		this.vendors = vendors;
		this.invoiceAmount = invoiceAmount;
		this.amountPerUnit = amountPerUnit;
		this.paymentDate = paymentDate;
		this.paymentMode = paymentMode;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public Vendors getVendors() {
		return vendors;
	}
	public void setVendors(Vendors vendors) {
		this.vendors = vendors;
	}
	public int getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(int invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	public double getAmountPerUnit() {
		return amountPerUnit;
	}
	public void setAmountPerUnit(double amountPerUnit) {
		this.amountPerUnit = amountPerUnit;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	

}
