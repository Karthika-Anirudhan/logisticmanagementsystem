package com.project.utility;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import com.project.DAO.PurchaseDAOImpl;
import com.project.DAO.ShipmentDAOImpl;
import com.project.entity.Orders;
import com.project.entity.Purchase;
import com.project.entity.Shipment;
import com.project.service.ShipmentServiceImpl;

public class ShipmentUtility {

	public static void main(String[] args) {
		//getAllShipment();
	//	insertIntoShipments();
	//	updateIntoShipment();
	//	deletFromShipment();
		displayMenu();
	//	displayById();

	}

	/*private static void displayById() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Shipment Id to search");
		String id = scanner.nextLine();

		ShipmentDAOImpl shipmentDAOImpl = new ShipmentDAOImpl();
		Shipment shipment= shipmentDAOImpl.findById(id);
		if (shipment != null) {
			System.out.println("SHIPMENT ID\tORDER ID\tSHIPPING ADDRESS\tTRACKING NUMBER\tESTIMATED DELIVERY DATE\t\tSTATUS");
			System.out.println(
					"--------------------------------------------------------------------------------------");
			Formatter fmt = new Formatter();
			fmt.format("%1s %14s %20s %20s %20ss %20s \n",shipment.getShipmentId(),shipment.getOrders().getOrderId(),shipment.getShippingAddress(),
					shipment.getTrackingNumber(),shipment.getEstimatedDeliveryDate(),shipment.getStatus());
			System.out.println(fmt);
		} else {
			System.out.println("No details found with the given ID,Try again...");
		}
	}
	*/

	public static void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do {
			System.out.println("********************Shipment Details************************");
			System.out.println("Choose an Option :\n");
			System.out.println("1.Display all Shipment Details");
			System.out.println("2.Add new Shipment info");
			System.out.println("3.Edit shipment info");
			System.out.println("4.Remove from Shipment details ");
			System.out.println("5.Exit");
			System.out.println("Enter your Choice : ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				getAllShipment();
				System.out.println("\n");
				break;
			case 2:
				getAllShipment();
				insertIntoShipments();
				getAllShipment();
				break;
			case 3:
				getAllShipment();
				updateIntoShipment();
				getAllShipment();
				break;
			case 4:
				getAllShipment();
				deletFromShipment();
				getAllShipment();
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
	private static void deletFromShipment() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Delete from Shipment***********************");
		System.out.println("Enter the Shipment ID to delete:");
		String shipmentId = scanner.next();
		Shipment shipment = new Shipment();
		shipment.setShipmentId(shipmentId);	
		ShipmentServiceImpl shipmentServiceImpl = new ShipmentServiceImpl();
		shipmentServiceImpl.delete(shipment);
		
	}

	private static void updateIntoShipment() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Update into Shipment***********************");
		System.out.println("Enter the shipment ID to update:");
		String shipmentId = scanner.nextLine();
		System.out.println("Enter the order ID to update:");
		int orderId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the shipping address to update:");
		String shippingAddress = scanner.nextLine();
		System.out.println("Enter the tracking number to update:");
		String trackingNumber = scanner.nextLine();
		System.out.println("Enter the estimated delivery date to update:");
		String estimatedDeleveryDate = scanner.nextLine();
		System.out.println("Enter the status to update:");
		String status = scanner.nextLine();

		Orders orders = new Orders();
		orders.setOrderId(orderId);

		Shipment shipment = new Shipment(shipmentId,orders,shippingAddress,trackingNumber,estimatedDeleveryDate,status);
		ShipmentServiceImpl shipmentServiceImpl = new ShipmentServiceImpl();
		shipmentServiceImpl.update(shipment);
	}
	private static void insertIntoShipments() {
		OrderUtility.displayAllOrders();
		Scanner scanner = new Scanner(System.in);
		System.out.println("*********************Inserting into Shipment***********************");
		System.out.println("Enter the shipment ID :");
		String shipmentId = scanner.nextLine();
		System.out.println("Enter the order ID:");
		int orderId = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the shipping address:");
		String shippingAddress = scanner.nextLine();
		System.out.println("Enter the tracking number:");
		String trackingNumber = scanner.nextLine();
		System.out.println("Enter the estimated delivery date:");
		String estimatedDeleveryDate = scanner.nextLine();
		System.out.println("Enter the status:");
		String status = scanner.nextLine();

		Orders orders = new Orders();
		orders.setOrderId(orderId);
		
		Shipment shipment = new Shipment(shipmentId,orders,shippingAddress,trackingNumber,estimatedDeleveryDate,status);
		ShipmentServiceImpl shipmentServiceImpl = new ShipmentServiceImpl();
		shipmentServiceImpl.save(shipment);
	}

	private static void getAllShipment() {
	    ShipmentServiceImpl shipmentServiceImpl = new ShipmentServiceImpl();
	    List<Shipment> shipmentList = shipmentServiceImpl.findAll();
	    System.out.println("*********************************Shipment Details**************************************************");
	    System.out.println("---------------------------------------------------------------------------------------------------------");
	    System.out.printf("%-15s %-10s %-25s %-18s %-30s %s%n", "SHIPMENT ID", "ORDER ID", "SHIPPING ADDRESS", "TRACKING NUMBER", "ESTIMATED DELIVERY DATE", "STATUS");
	    System.out.println("---------------------------------------------------------------------------------------------------------");

	    Formatter fmt = new Formatter();
	    for (Shipment shipment : shipmentList) {
	        fmt.format("%-15s %-10s %-25s %-18s %-30s %s%n", shipment.getShipmentId(), shipment.getOrders().getOrderId(), shipment.getShippingAddress(),
	                shipment.getTrackingNumber(), shipment.getEstimatedDeliveryDate(), shipment.getStatus());
	    }
	    System.out.println(fmt);
	}
}