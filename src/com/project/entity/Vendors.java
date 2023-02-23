package com.project.entity;

public class Vendors {
	private int vendorId;
	private String vendorName;
	private String vendorAddress;
	private String mobileNumber;
	private String mailId;
	public Vendors(int vendorId, String vendorName, String vendorAddress, String mobileNumber, String mailId) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorAddress = vendorAddress;
		this.mobileNumber = mobileNumber;
		this.mailId = mailId;
	}
	public Vendors() {
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
}
