package com.revature.project0.New_Choice_Bank;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import  org.apache.log4j.Logger;

import com.revature.project0.dao.UserImplementation;
import com.revature.project0.model.User;
import com.revature.project0.service.UserService;

public class App {
	final static Logger Log = Logger.getLogger(UserImplementation.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			int index = 0;
			mainMenu(index);
		}
		catch( InputMismatchException i) {
			Log.error("catch block in mainMenu - Inputmismatch - occured");
			System.out.println("Please choose a valid option");
		}
		finally {
			int index = 0;
			mainMenu(index);

		}
	}
	public static void mainMenu(int index) {

		//main menu welcome
		System.out.println("----------------------------------");
		System.out.println("Welcome to New Choice Bank! ");
		System.out.println("Register now and receive $50 free when approved");
		System.out.println("Please choose an option:");
		//main menu options
		System.out.println("1. Register");
		System.out.println("2. Login");

		System.out.println("----------------------------------");

		//declare a new scanner object
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		//read in user input and store it
		int userInput = input.nextInt();

		optionsResponse(userInput);	
	}

	public static void optionsResponse(int userInput) {
		
		//if add a task
		if(userInput == 1){
			System.out.println("----------------------------------");
			System.out.println("Welcome to Registration!");			
			System.out.println("First Name: ");
			@SuppressWarnings("resource")
			Scanner input1 = new Scanner(System.in);
			String registrationFName = input1.nextLine();

			
			System.out.println("Last Name: ");
			//declare a new scanner object
			@SuppressWarnings("resource")
			Scanner input2 = new Scanner(System.in);
			String registrationLName = input2.nextLine();
			
			System.out.println("Username: ");
			@SuppressWarnings("resource")
			Scanner input3 = new Scanner(System.in);
			String registrationUName = input3.nextLine();
			
			System.out.println("password: ");
			@SuppressWarnings("resource")
			Scanner input4 = new Scanner(System.in);
			String registrationPass = input4.nextLine();

			//RegisterANewUser Stuff -------------------
			UserService.getUserService().registerUser(new User(registrationFName,registrationLName,registrationUName,registrationPass,false,false,50));
			
			System.out.println("Account has been created successfully!");
			System.out.println("Account still needs to be approved by an admin");
			
			System.out.println("----------------------------------");
			
			mainMenu(-1);
			
		}
		else if(userInput == 2){
			System.out.println("----------------------------------");

			System.out.println("Welcome back! Please login");
			
			System.out.println("Username: ");
			@SuppressWarnings("resource")
			Scanner input5 = new Scanner(System.in);
			String loginUsername = input5.nextLine();
			
			//PASSWORD
			System.out.println("password: ");
			@SuppressWarnings("resource")
			Scanner input6 = new Scanner(System.in);
			String loginPass = input6.nextLine();
			
			//LOGIN STUFF _____________
			Boolean UList = UserService.getUserService().logInUser(loginUsername,loginPass);
			if ( !UList ) {
				System.out.println("Please try again. ");
			}
			else {
				//TODO: make this function work;
				//bank options
				
				UserService.getUserService().somethingWithMoney(loginUsername, loginPass);
			}
		}
		else {
			System.out.println("Please Choose a valid option.");
			System.out.println("\n");
		}
		
	}
}


