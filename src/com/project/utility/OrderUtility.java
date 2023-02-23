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
import com.project.service.OrderServiceImpl;

public class OrderUtility {

	public static void main(String[] args) {
		// dispayById();
		// displayAllOrders();
		// insertIntoOrders();
		// updateIntoOrders();
		// deleteFromOrders();
		displayMenu();
	}

	// public static void dispayById() {
	/*
	 * Scanner scanner = new Scanner(System.in);
	 * System.out.println("Enter the Order Id to search"); int id =
	 * scanner.nextInt();
	 * 
	 * OrderServiceImpl orderServiceImpl = new OrderServiceImpl(); Orders orders =
	 * orderServiceImpl.findById(id); if (orders != null) { System.out.
	 * println("ORDER ID\tVENDOR ID\tORDER DATE\t\tPRODUCT ID\tORDER QUANTITY");
	 * System.out.println(
	 * "--------------------------------------------------------------------------------------"
	 * ); Formatter fmt = new Formatter();
	 * 
	 * fmt.format("%1s %20s %20s %16s%23s\n",orders.getOrderId(),orders.getVendors()
	 * .getVendorId(),orders.getOrderDate(),
	 * orders.getInventory().getProductId(),orders.getOrderQuantity());
	 * System.out.println(fmt); } else {
	 * System.out.println("No details found with the given ID,Try again..."); } }
	 */

	public static void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;

		do {
			System.out.println("********************Order Details************************");
			System.out.println("Choose an Option :\n");
			System.out.println("1.Display all Order Details");
			System.out.println("2.Add new Order info");
			System.out.println("3.Edit Order info");
			System.out.println("4.Remove from Order details ");
			System.out.println("5.Exit");
			System.out.println("Enter your Choice : ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				displayAllOrders();
				System.out.println("\n");
				break;
			case 2:
				displayAllOrders();
				insertIntoOrders();
				displayAllOrders();
				break;
			case 3:
				displayAllOrders();
				updateIntoOrders();
				displayAllOrders();
				break;
			case 4:
				displayAllOrders();
				deleteFromOrders();
				displayAllOrders();
				break;
			case 5:
				System.out.println("*************THANK YOU*******************");
				condition = false;
				break;
			default:
				System.out.println("Invalid Option");
			}
		} while (condition);
	}

	public static void displayAllOrders() {
		OrderServiceImpl orderService = new OrderServiceImpl();
		List<Orders> orderList = orderService.findAll();
		System.out.println("************************************Order Details****************************************");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("ORDER ID\tVENDOR ID\tORDER DATE\t\tPRODUCT ID\tORDER QUANTITY");
		System.out.println("---------------------------------------------------------------------------------");
		Formatter fmt = new Formatter();

		for (Orders orders : orderList) {
			fmt.format("%1s %20s %20s %16s%23s\n", orders.getOrderId(), orders.getVendors().getVendorId(),
					orders.getOrderDate(), orders.getInventory().getProductId(), orders.getOrderQuantity());
		}
		System.out.println(fmt);
	}

	private static void deleteFromOrders() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Delete from Orders***********************");
		System.out.println("Enter the Order ID to delete:");
		int orderId = scanner.nextInt();
		Orders orders = new Orders();
		orders.setOrderId(orderId);
		OrderServiceImpl orderService = new OrderServiceImpl();
		orderService.delete(orders);
	}

	private static void updateIntoOrders() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Update into Orders***********************");
		System.out.println("Enter the Order ID to update:");
		int orderId = scanner.nextInt();
		System.out.println("Enter the vendor ID to update:");
		int vendorId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the order date to update:");
		String orderDate = scanner.nextLine();
		System.out.println("Enter the product ID to update:");
		int productId = scanner.nextInt();
		System.out.println("Enter the order Quantity to update:");
		int orderQuantity = scanner.nextInt();

		InventoryServiceImpl inventoryService = new InventoryServiceImpl();
		Inventory inventory = inventoryService.findById(productId);
		int currentQuantity = inventory.getQuantity();

		if (orderQuantity > currentQuantity) {
			System.out.println("Cannot place order. Insufficient inventory.");
			return;
		}
		Vendors vendors = new Vendors();
		vendors.setVendorId(vendorId);

		inventory.setProductId(productId);
		Orders orders = new Orders(orderId, vendors, orderDate, inventory, orderQuantity);
		OrderServiceImpl orderService = new OrderServiceImpl();
		orderService.update(orders);

	}

	private static void insertIntoOrders() {
		VendorUtility.getAllVendors();
		InventoryUtility.getAllInventory();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Inserting into Orders***********************");
		System.out.println("Enter the Order ID:");
		int orderId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Vendor ID:");
		int vendorID = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Order date in yyyy-mm-dd:");
		String orderDate = scanner.nextLine();
		System.out.println("Enter the product ID:");
		int productId = scanner.nextInt();
		System.out.println("Enter the order quantity:");
		int quantity = scanner.nextInt();

		InventoryServiceImpl inventoryService = new InventoryServiceImpl();
		Inventory inventory = inventoryService.findById(productId);
		int currentQuantity = inventory.getQuantity();

		if (quantity > currentQuantity) {
			System.out.println("Cannot place order. Insufficient inventory.");
			return;
		}

		Vendors vendors = new Vendors();
		vendors.setVendorId(vendorID);

		Orders orders = new Orders(orderId, vendors, orderDate, inventory, quantity);
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.save(orders);

		// Update inventory quantity
		inventory.setQuantity(currentQuantity - quantity);
		inventoryService.update(inventory);
	}

}
