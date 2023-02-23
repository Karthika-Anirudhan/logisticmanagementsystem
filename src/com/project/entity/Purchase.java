package com.project.entity;

public class Purchase {
	private String purchaseId;
	private Inventory inventory;
	private int quantity;
	private int purchaseAmount;
	private int unitAmount;
	public Purchase() {
		super();
	}
	public Purchase(String purchaseId, Inventory inventory, int quantity, int purchaseAmount, int unitAmount) {
		super();
		this.purchaseId = purchaseId;
		this.inventory = inventory;
		this.quantity = quantity;
		this.purchaseAmount = purchaseAmount;
		this.unitAmount = unitAmount;
	}
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public int getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(int unitAmount) {
		this.unitAmount = unitAmount;
	}
}