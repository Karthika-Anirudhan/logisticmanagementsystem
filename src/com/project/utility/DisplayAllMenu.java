package com.project.utility;

import java.util.Scanner;

public class DisplayAllMenu {

	public static void main(String[] args) {
		
		displayMenu();
		//OrderUtility.displayMenu();
		//PurchaseUtility.dispalyMenu();
	//	VendorUtility.displayMenu();
	//	ShipmentUtility.dispalyMenu();
		//InvoiceUtility.displayMenu();
		//InventoryUtility.dispalyMenu();
	}

	public static void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		int option;
	  	do {
			System.out.println("***********WELCOME TO GLOBAL TECH LOGISTIC MANAGEMENT SYSTEM***********");
			System.out.println("Choose the Department");
			System.out.println(
					"1.PURCHASE REGISTER\n2.INVENTORY REGISTER\n3.VENDOR DETAILS\n4.ORDER DETAILS\n5.INVOICE DETAILS\n"
							+ "6.SHIPMENT DETAILS\n7.EXIT");
			System.out.println("Enter your choice :");
			option = scanner.nextInt();
			switch (option) {
			case 1:
				PurchaseUtility.dispalyMenu();
				break;
			case 2:
				InventoryUtility.displayMenu();
				break;
			case 3:
				VendorUtility.displayMenu();
				break;
			case 4:
				OrderUtility.displayMenu();
				break;
			case 5:
				InvoiceUtility.displayMenu();
				break;
			case 6:
				ShipmentUtility.displayMenu();
				break;
			case 7:
				System.out.println("*************THANK YOU***************");
				condition = false;
				break;
			default:
				System.out.println("Invalid Option");
			}
		} while (condition);

	}
}
