package com.project.utility;

import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import com.project.entity.Inventory;
import com.project.entity.Orders;
import com.project.service.InventoryServiceImpl;

public class InventoryUtility {
	
	public static void main(String[] args) {
		//displayById();
		// getAllInventory();
		// insertIntoInventory();
		
		// deleteFromInventory();
		displayMenu();

	}

/*	public static void displayById() {
		  Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter the product Id to search");
	        int id = scanner.nextInt();
	       
	        InventoryServiceImpl inventoryServiceImpl = new InventoryServiceImpl();
	        Inventory inventory =inventoryServiceImpl.findById(id);
	        if (inventory != null) {
			System.out.println("PRODUCT ID\tPRODUCT NAME\tPRODUCT DESCRIPTION\tQUANTITY");
			System.out.println("---------------------------------------------------------------------------------");
			Formatter fmt = new Formatter();
			 
			fmt.format("%8s %18s %20s %14s\n", inventory.getProductId(), inventory.getProductName(),
						inventory.getProductDescription(), inventory.getQuantity());
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
			System.out.println("********************Inventory Details************************");
			System.out.println("Choose an Option :\n");
			System.out.println("1.Display all inventory Details");
			System.out.println("2.Add new inventory info");
			System.out.println("3.Import bulk inventory Data");
			System.out.println("4.Edit inventory info");
			System.out.println("5.Remove from inventory details ");
			System.out.println("6.Exit");
			System.out.println("Enter your Choice : ");
			int choice= scanner.nextInt();
			switch(choice) {
			case 1:
				getAllInventory();
				System.out.println("\n");
				break;
			case 2:
				getAllInventory();
				insertIntoInventory();
				getAllInventory();
				break;
			case 3:
				getAllInventory();
				InventoryDataImport.dataImport();
				getAllInventory();
				break;
			case 4:
				getAllInventory();
				updateIntoInventory();
				getAllInventory();
				break;
			case 5:
				getAllInventory();
				deleteFromInventory();
				getAllInventory();
				break;
			case 6:
				System.out.println("*************THANK YOU*******************");
				condition=false;
				break;
			default:
				System.out.println("Invalid Option");
				}
				}
				while(condition);
				}	
	
	private static void deleteFromInventory() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Delete from Inventory***********************");
		System.out.println("Enter the Product ID to delete:");
		int productId = scanner.nextInt();
		Inventory inventory = new Inventory();
		inventory.setProductId(productId);		
		InventoryServiceImpl inventoryService = new InventoryServiceImpl();
		inventoryService.delete(inventory);
	}
	private static void updateIntoInventory() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Update into Inventory***********************");
		System.out.println("Enter the Product ID to update:");
		int productId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Product name to update:");
		String productName = scanner.nextLine();
		System.out.println("Enter the Product Description to update:");
		String productDescription = scanner.nextLine();
		System.out.println("Enter the Quantity to update:");
		int quantity = scanner.nextInt();
		Inventory inventory = new Inventory(productId, productName, productDescription, quantity);
		InventoryServiceImpl inventoryService = new InventoryServiceImpl();
		inventoryService.update(inventory);		
	}

	public static void insertIntoInventory() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Inserting into Inventory***********************");
		System.out.println("Enter the Product ID:");
		int productId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the Product name:");
		String productName = scanner.nextLine();
		System.out.println("Enter the Product Description:");
		String productDescription = scanner.nextLine();
		System.out.println("Enter the Quantity:");
		int quantity = scanner.nextInt();
		Inventory inventory = new Inventory(productId, productName, productDescription, quantity);
		InventoryServiceImpl inventoryService = new InventoryServiceImpl();
		inventoryService.save(inventory);
		
	}
		
	public static void getAllInventory() {
		InventoryServiceImpl inventoryServiceImpl = new InventoryServiceImpl();
		List<Inventory>inventoryList = inventoryServiceImpl.findAll();
		System.out.println("******************************Inventory Details**********************************");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("PRODUCT ID\tPRODUCT NAME\t\tPRODUCT DESCRIPTION\tquantity");
		System.out.println("---------------------------------------------------------------------------------");
		Formatter fmt = new Formatter();
		for (Inventory inventory : inventoryList) {
			
			fmt.format("%8s %18s %25s %14s\n", inventory.getProductId(),inventory.getProductName(),inventory.getProductDescription(),
					inventory.getQuantity());
		}
		System.out.println(fmt);
			
			
		}

	public static void updateInventoryAfterOrder(Orders order) {
	    // Get the product ID and order quantity from the order
	    int productId = order.getInventory().getProductId();
	    int orderQuantity = order.getOrderQuantity();
	    
	    // Find the inventory item with the matching product ID
	    InventoryServiceImpl inventoryService = new InventoryServiceImpl();
	    Inventory inventoryItem = inventoryService.findById(productId);
	    
	    // Subtract the order quantity from the inventory quantity
	    int newQuantity = inventoryItem.getQuantity() - orderQuantity;
	    inventoryItem.setQuantity(newQuantity);
	    
	    // Update the inventory item in the database
	    inventoryService.update(inventoryItem);
	}
}	
