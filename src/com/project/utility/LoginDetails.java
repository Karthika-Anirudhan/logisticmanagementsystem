package com.project.utility;

import java.sql.SQLException;
import java.util.Scanner;

import com.project.DAO.LoginDAO;
/**
 * 
 * @author karthika
 *
 */

public class LoginDetails {

	public static void main(String[] args) {
		checkLogin();
	}

	public static void checkLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("********Login to Global Tech********");
		System.out.println("***User Login Credentials****");
		System.out.println("-------------------------------");
		System.out.println();

		System.out.println("Enter User Name :");
		String userName = scanner.nextLine();
		System.out.println("Enter Password :");
		String password = scanner.nextLine();

		LoginDAO loginDao = new LoginDAO();
		boolean isLoginValid;
		try {
			isLoginValid = loginDao.findByUsernameAndPassword(userName, password);
			if (isLoginValid) {
				System.out.println("Logged in successfully....");
				DisplayAllMenu.displayMenu();
			} else {
				System.out.println("Incorrect Login credentials. Try again");
				System.out.println();
				checkLogin();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
