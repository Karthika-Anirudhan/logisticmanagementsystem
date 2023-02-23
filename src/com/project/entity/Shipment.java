package com.project.entity;

public class Shipment {
	private String shipmentId;
	private Orders orders;
	private String shippingAddress;
	private String trackingNumber;
	private String estimatedDeliveryDate;
	private String status;

	public Shipment(String shipmentId, Orders orders, String shippingAddress, String trackingNumber,
			String estimatedDeliveryDate, String status) {
		super();
		this.shipmentId = shipmentId;
		this.orders = orders;
		this.shippingAddress = shippingAddress;
		this.trackingNumber = trackingNumber;
		this.estimatedDeliveryDate = estimatedDeliveryDate;
		this.status = status;
	}

	public Shipment() {
		super();
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
	