package com.project.utility;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import com.project.DAO.PurchaseDAOImpl;
import com.project.entity.Inventory;
import com.project.entity.Invoice;
import com.project.entity.Orders;
import com.project.entity.Purchase;
import com.project.entity.Shipment;
import com.project.service.InventoryService;
import com.project.service.InventoryServiceImpl;
import com.project.service.InvoiceServiceImpl;
import com.project.service.OrderServiceImpl;
import com.project.service.PurchaseServiceImpl;
import com.project.service.ShipmentServiceImpl;

public class PurchaseUtility {

	public static void main(String[] args) {
		//displayById();
		//getAllPurchase();
	//	insertIntoPurchase();
	//	updateIntoPurchase();
	//	deletFromPurchase();
		dispalyMenu();
		

	}

//	public static void displayById() {
	/*	Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Purchase Id to search");
		String id = scanner.nextLine();

		PurchaseDAOImpl purchaseDAOImpl = new PurchaseDAOImpl();
		Purchase purchase = purchaseDAOImpl.findById(id);
		if (purchase != null) {
			System.out.println("PURCHASE ID\tPRODUCT ID\tQUANTITY\tPURCHASE AMOUNT\tAMOUNT PER UNIT");
			System.out.println(
					"--------------------------------------------------------------------------------------");
			Formatter fmt = new Formatter();
			fmt.format("%1s %14s %18s %16s%18s  \n",purchase.getPurchaseId(),purchase.getInventory().getProductId(),purchase.getQuantity(),
					purchase.getPurchaseAmount(),purchase.getUnitAmount());
			System.out.println(fmt);
		} else {
			System.out.println("No details found with the given ID,Try again...");
		}
	}
	*/
	public static void dispalyMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do {
			System.out.println("********************Purchase Details************************");
			System.out.println("Choose an Option :\n");
			System.out.println("1.Display all Purchase Details");
			System.out.println("2.Add new Purchase info");
			System.out.println("3.Edit Purchase info");
			System.out.println("4.Remove from Purchase details ");
			System.out.println("5.Exit");
			System.out.println("Enter your Choice : ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				getAllPurchase();
				System.out.println("\n");
				break;
			case 2:
				getAllPurchase();
				insertIntoPurchase();
				getAllPurchase();
				break;
			case 3:
				getAllPurchase();
				updateIntoPurchase();
				getAllPurchase();
				break;
			case 4:
				getAllPurchase();
				deletFromPurchase();
				getAllPurchase();
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
	private static void deletFromPurchase() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Delete from Purchase***********************");
		System.out.println("Enter the Purchase ID to delete:");
		String PurchaseId = scanner.next();
		Purchase purchase = new Purchase();
		purchase.setPurchaseId(PurchaseId);;	
		PurchaseServiceImpl purchaseServiceImpl = new PurchaseServiceImpl();
		purchaseServiceImpl.delete(purchase);
	}

	private static void updateIntoPurchase() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Update into Purchase***********************");
		System.out.println("Enter the Purchase ID to update:");
		String purchaseId = scanner.nextLine();
		System.out.println("Enter the product ID to update:");
		int productId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the quantity to update:");
		int quantity = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the purchase amount to update:");
		int purchaseAmount = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the amount per unit to update:");
		int unitAmount = scanner.nextInt();
		scanner.nextLine();
	
		Inventory inventory = new Inventory();
		inventory.setProductId(productId);
		
		Purchase purchase = new Purchase(purchaseId,inventory,quantity,purchaseAmount,unitAmount);
		PurchaseServiceImpl purchaseServiceImpl = new PurchaseServiceImpl();
		purchaseServiceImpl.update(purchase);
		
		// update inventory quantity
		InventoryService inventoryService = new InventoryServiceImpl();
		Inventory existingInventory = inventoryService.findById(productId);
		if (existingInventory != null) {
			existingInventory.setQuantity(existingInventory.getQuantity() + quantity);
			inventoryService.update(existingInventory);
		} else {
			System.out.println("Inventory with product ID " + productId + " does not exist.");
		}
	}

	private static void insertIntoPurchase() {
		InventoryUtility.getAllInventory();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Inserting into Purchase***********************");
		System.out.println("Enter the Purchase ID :");
		String purchaseId = scanner.nextLine();
		System.out.println("Enter the Product ID:");
		int productId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the quantity:");
		int quantity = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the purchase amount:");
		int purchaseAmount = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the amount per unit:");
		int unitAmount = scanner.nextInt();
		scanner.nextLine();

		Inventory inventory = new Inventory();
		inventory.setProductId(productId);
	   
		Purchase purchase = new Purchase(purchaseId,inventory,quantity,purchaseAmount,unitAmount);
		PurchaseServiceImpl purchaseServiceImpl = new PurchaseServiceImpl();
		purchaseServiceImpl.save(purchase);
		// update inventory quantity
		InventoryService inventoryService = new InventoryServiceImpl();
		Inventory existingInventory = inventoryService.findById(productId);
		if (existingInventory != null) {
			existingInventory.setQuantity(existingInventory.getQuantity() + quantity);
			inventoryService.update(existingInventory);
		} else {
			System.out.println("Inventory with product ID " + productId + " does not exist.");
		}
	}
	
	private static void getAllPurchase() {
		PurchaseServiceImpl purchaseServiceImpl = new PurchaseServiceImpl();
		List<Purchase>purchaseList = purchaseServiceImpl.findAll();
		System.out.println("************************************Purchase DETAILS****************************************");
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("PURCHASE ID\tPRODUCT ID\tQUANTITY\tPURCHASE AMOUNT\tAMOUNT PER UNIT");
		System.out.println("------------------------------------------------------------------------------------------------");
		Formatter fmt = new Formatter();
		for (Purchase purchase : purchaseList) {
			fmt.format("%1s %14s %18s %16s%18s  \n",purchase.getPurchaseId(),purchase.getInventory().getProductId(),purchase.getQuantity(),
					purchase.getPurchaseAmount(),purchase.getUnitAmount());
		}
			System.out.println(fmt);
		}	
	}


