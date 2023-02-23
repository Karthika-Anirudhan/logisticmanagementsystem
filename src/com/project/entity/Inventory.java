package com.project.entity;

public class Inventory {
	private int productId;
	private String productName;
	private String productDescription;
	private int quantity;
	
	public Inventory(int productId, String productName, String productDescription, int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.quantity = quantity;
	}
	
	public Inventory() {
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
