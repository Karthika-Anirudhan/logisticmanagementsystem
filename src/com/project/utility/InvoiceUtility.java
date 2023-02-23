package com.project.utility;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import com.project.entity.Inventory;
import com.project.entity.Invoice;
import com.project.entity.Orders;
import com.project.entity.Vendors;
import com.project.service.InventoryServiceImpl;
import com.project.service.InvoiceServiceImpl;

public class InvoiceUtility {

	public static void main(String[] args) {
		//displayById();
		//displayAllInvoice();
		//insertIntoInvoice();
		//updateIntoInvoice();
		//deleteFromInvoice();
		displayMenu();
	}

/*	private static void displayById() {
		 Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter the invoice Id to search");
	        String id = scanner.nextLine();
	       
	        InvoiceServiceImpl invoiceServiceImpl = new InvoiceServiceImpl();
	        Invoice invoice =invoiceServiceImpl.findById(id);
	        if (invoice != null) {
	        System.out.println("INVOICE ID\tORDER ID\tVENDOR ID\tINVOICE AMOUNT\tAMOUNT PER UNIT\tPAYMENT DATE\tPAYMENT MODE");
			System.out.println("------------------------------------------------------------------------------------------------");
			Formatter fmt = new Formatter();
			 
			fmt.format("%1s %14s %18s %16s%18s %15s %15s \n",invoice.getInvoiceId(),invoice.getOrders().getOrderId(),invoice.getVendors().getVendorId(),
					invoice.getInvoiceAmount(),invoice.getAmountPerUnit(),invoice.getPaymentDate(),invoice.getPaymentMode());
			System.out.println(fmt);
	        }
			else {
				System.out.println("No details found with the given ID,Try again...");
			}
	}*/
	
	public static void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		
		do {
			System.out.println("********************Invoice Details************************");
			System.out.println("Choose an Option :\n");
			System.out.println("1.Display all Invoice Details");
			System.out.println("2.Add new Invoice info");
			System.out.println("3.Edit Invoice info");
			System.out.println("4.Remove from Invoice details ");
			System.out.println("5.Exit");
			System.out.println("Enter your Choice : ");
			int choice= scanner.nextInt();
			switch (choice) {
			case 1:
				displayAllInvoice();
				System.out.println("\n");
				break;
			case 2:
				displayAllInvoice();
				insertIntoInvoice();
				displayAllInvoice();
				break;
			case 3:
				displayAllInvoice();
				updateIntoInvoice();
				displayAllInvoice();
				break;
			case 4:
				displayAllInvoice();
				deleteFromInvoice();
				displayAllInvoice();
				break;
			case 5:
				System.out.println("*************THANK YOU*******************");
				condition = false;
				break;
			default:
				System.out.println("Invalid Option");
			}
				}
				while(condition);
				}		

	private static void deleteFromInvoice() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Delete from Invoice***********************");
		System.out.println("Enter the Invoice ID to delete:");
		String invoiceId = scanner.next();
		Invoice invoice = new Invoice();
		invoice.setInvoiceId(invoiceId);	
		InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
		invoiceService.delete(invoice);
	}

	private static void updateIntoInvoice() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Update into Invoice***********************");
		System.out.println("Enter the Invoice ID to update:");
		String invoiceId = scanner.nextLine();
		System.out.println("Enter the order ID to update:");
		int orderId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the vendor ID to update:");
		int vendorId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the invoice amount to update:");
		int invoiceAmount = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the amount per unit to update:");
		double amountPerUnit = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter the payment date to update:");
		String paymentDate = scanner.nextLine();
		
		System.out.println("Enter the payment mode to update:");
		String paymentMode = scanner.nextLine();
			
		Orders orders = new Orders();
		orders.setOrderId(orderId);
		Vendors vendors = new Vendors();
		vendors.setVendorId(vendorId);
		Invoice invoice = new Invoice(invoiceId,orders,vendors,invoiceAmount,amountPerUnit,paymentDate,paymentMode);
		InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
		invoiceService.update(invoice);
	}
	
	private static void insertIntoInvoice() {
		VendorUtility.getAllVendors();
		OrderUtility.displayAllOrders();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Inserting into Invoice***********************");
		System.out.println("Enter the Invoice ID:");
		String InvoiceId = scanner.nextLine();
		System.out.println("Enter the order ID:");
		int orderId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the vendor ID:");
		int vendorId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the invoice amount:");
		int invoiceAmount = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the amount per unit:");
		double amountPerUnit = scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter the payment date to update in yyyy-mm-dd:");
		String paymentDate = scanner.nextLine();
		scanner.nextLine();
		System.out.println("Enter the payment mode:");
		String paymentMode = scanner.nextLine();
			
      
		Orders orders = new Orders();
		orders.setOrderId(orderId);
		Vendors vendors = new Vendors();
		vendors.setVendorId(vendorId);
		
		Invoice invoice = new Invoice(InvoiceId,orders,vendors,invoiceAmount,amountPerUnit,paymentDate,paymentMode);
		InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
		invoiceService.save(invoice);

	}
	public static void displayAllInvoice() {
		InvoiceServiceImpl invoiceService = new InvoiceServiceImpl();
		List<Invoice>invoiceList = invoiceService.findAll();
		System.out.println("************************************INVOICE DETAILS****************************************");
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("INVOICE ID\tORDER ID\tVENDOR ID\tINVOICE AMOUNT\tAMOUNT PER UNIT\tPAYMENT DATE\tPAYMENT MODE");
		System.out.println("------------------------------------------------------------------------------------------------");
		Formatter fmt = new Formatter();
		for (Invoice invoice : invoiceList) {
			fmt.format("%1s %14s %18s %16s%18s %15s %15s \n",invoice.getInvoiceId(),invoice.getOrders().getOrderId(),invoice.getVendors().getVendorId(),
					invoice.getInvoiceAmount(),invoice.getAmountPerUnit(),invoice.getPaymentDate(),invoice.getPaymentMode());
		}
			System.out.println(fmt);
		}	
	}
