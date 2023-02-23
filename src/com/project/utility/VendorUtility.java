package com.project.utility;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import com.project.entity.Orders;
import com.project.entity.Vendors;
import com.project.service.OrderServiceImpl;
import com.project.service.VendorServiceImpl;

public class VendorUtility {

	public static void main(String[] args) {
		//displayById();
		//getAllVendors();
		//insertIntoVendors();
		//updateIntoVendors();
		//deleteFromVendors();
		displayMenu();
	
		
	}

	/*private static void displayById() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Vendor Id to search");
		int id = scanner.nextInt();

		VendorServiceImpl vendorServiceImpl = new VendorServiceImpl();
		Vendors vendors = vendorServiceImpl.findById(id);
		if (vendors != null) {
			System.out.println("VENDOR ID\tVENDOR NAME\tVENDOR ADDRESS\t\tMOBILE NUMBER\t\tMAIL ID");
			System.out.println(
					"--------------------------------------------------------------------------------------");
			Formatter fmt = new Formatter();
			fmt.format("%1s %20s %25s %18s%25s\n",vendors.getVendorId(), vendors.getVendorName(),vendors.getVendorAddress()
					,vendors.getMobileNumber(),vendors.getMailId());
			System.out.println(fmt);
		} else {
			System.out.println("No details found with the given ID,Try again...");
		}
	}*/
	
	public static void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do {
			System.out.println("********************Vendor Details************************");
			System.out.println("Choose an Option :\n");
			System.out.println("1.Display all Vendor Details");
			System.out.println("2.Add new Vendor info");
			System.out.println("3.Edit Vendor info");
			System.out.println("4.Remove from Vendor details ");
			System.out.println("5.Exit");
			System.out.println("Enter your Choice : ");
			int choice= scanner.nextInt();
			switch(choice) {
			case 1:
				getAllVendors();
				System.out.println("\n");
				break;
			case 2:
				getAllVendors();
				insertIntoVendors();
				getAllVendors();
					break;
			case 3:
				getAllVendors();
				updateIntoVendors();
				getAllVendors();
					break;
			case 4:
				getAllVendors();		
				deleteFromVendors();
				getAllVendors();
					break;
			case 5:
				System.out.println("*************THANK YOU*******************");
				condition=false;
				break;
			default:
				System.out.println("Invalid Option");
				}
				}
				while(condition);
				}	
	private static void deleteFromVendors() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Delete from Vendors***********************");
		System.out.println("Enter the Vendor ID to delete:");
		int vendorId = scanner.nextInt();
		Vendors vendors = new Vendors();
		vendors.setVendorId(vendorId);
		VendorServiceImpl vendorService = new VendorServiceImpl();
		vendorService.delete(vendors);	
	}

	private static void updateIntoVendors() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Update into Vendors***********************");
		System.out.println("Enter the Vendor ID to update:");
		int vendorId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Vendor name to update:");
		String vendorName = scanner.nextLine();
		System.out.println("Enter the Vendor Address to update:");
		String vendorAddress = scanner.nextLine();
		System.out.println("Enter the Mobile number to update:");
		String mobileNumber = scanner.nextLine();
		System.out.println("Enter the Mail ID to update:");
		String mailId = scanner.nextLine();
		Vendors vendors = new Vendors(vendorId, vendorName, vendorAddress, mobileNumber,mailId);
		VendorServiceImpl vendorService = new VendorServiceImpl();
		vendorService.update(vendors);
	
	}

	public static void insertIntoVendors() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Inserting into Vendors***********************");
		System.out.println("Enter the Vendor ID:");
		int vendorId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Vendor name:");
		String vendorName = scanner.nextLine();
		System.out.println("Enter the Vendor Address:");
		String vendorAddress = scanner.nextLine();
		System.out.println("Enter the Mobile number:");
		String mobileNumber = scanner.nextLine();
		System.out.println("Enter the Mail ID:");
		String mailId = scanner.nextLine();
		Vendors vendors = new Vendors(vendorId, vendorName, vendorAddress, mobileNumber,mailId);
		VendorServiceImpl vendorService = new VendorServiceImpl();
		vendorService.save(vendors);
	
	}


	public static void getAllVendors() {
		VendorServiceImpl vendorServiceImpl = new VendorServiceImpl();
		List<Vendors>vendorList = vendorServiceImpl.findAll();
		System.out.println("************************************Vendor Details****************************************");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("VENDOR ID\tVENDOR NAME\tVENDOR ADDRESS\t\tMOBILE NUMBER\t\tMAIL ID");
		System.out.println("------------------------------------------------------------------------------------------");
		Formatter fmt = new Formatter();
		
		for (Vendors vendor : vendorList) {
			fmt.format("%1s %20s %25s %18s%25s\n",vendor.getVendorId(), vendor.getVendorName(),vendor.getVendorAddress()
			,vendor.getMobileNumber(),vendor.getMailId());
			
		}
		System.out.println(fmt);
	}
	}
