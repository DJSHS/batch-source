package com.revature.scanner;

import java.util.Scanner;

import com.revature.model.User;
import com.revature.service.UserService;

public class Prompt {
	
	Scanner sc = new Scanner(System.in);	// Used to read input from console.
	UserService us = new UserService();		// Used to call various methods for CRUD operations.
	UserActions ua = new UserActions();		// Used to call methods like withdraw, deposit and transfer.
	
	public Prompt() {
		super();
	}
	
	// This is the function that gets invoked in the Driver class.
	public void welcomeMsg() {
		System.out.println("\tWelcome!");
		System.out.println("\nWhat would you like to do?\n\n1. Create new user\n\n2. Log in\n\n3. Quit");
		
		String answer = sc.next();	// Even though I'am using numbers for the answer, sc.next would be able to check for any input
									// that is not 1, 2 or 3.
		switch (answer) {	
		
		// CREATE NEW USER
		case "1":
			createUsrMsg();
			
			break;
			
		// LOG IN
		case "2":
			logInMsg();
			
			break;
			
		// QUIT
		case "3":
			System.out.println("\n\tHave A Nice Day!");
			System.exit(0);
			
			break;
			// if the user inputs anything besides 1, 2 or 3 then this message shows and it recursively calls itself again.
		default:
			System.out.println("That isn't an option try again!\n\n");
			welcomeMsg();
			
			break;
		}
	}
	
	// This function is invoked when the user wants to create a new account.
	private void createUsrMsg() {
		
		User newUser = new User();
		
		System.out.println("Create a username (b to go back): ");
		String username = sc.next().toLowerCase(); // use toLowerCase to not have similar user names that only vary with capital letters.
		
		if (username.equals("b")) {
			welcomeMsg();
		}
		
		while (username.length() < 5) {	// checks if the username is less than 5 characters long.
			System.out.println("\nUsername should be more than 5 characters long!");
			System.out.println("\n\nCreate a username: ");
			username = sc.next().toLowerCase();
		}
		
		while (us.getUser(username).getUsername() != null) {
			System.out.println("\nusername taken!");
			System.out.println("\n\nCreate a username: ");
			username = sc.next().toLowerCase();
		}
		
		System.out.println("Create a password: ");
		String password = sc.next();
		
		while (password.length() < 5) { // checks if the password is less than 5 characters long.
			System.out.println("\nPassword should be more than 5 characters long!");
			System.out.println("\n\nCreate a password: ");
			password = sc.next();
		}
		
		System.out.println("Enter your first name: ");
		String firstName = sc.next();
		
		System.out.println("Enter your last name: ");
		String lastName = sc.next();
		
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setBalance(0);	// Since it is a new account balance starts at 0.
			
		String result = us.createUser(newUser);
		System.out.println(result);
		
		logInMsg();	// After the user is created, it takes the user to the log in "page".
	}
	
	// This function is invoked when the user wants to log in or if the user recently created an account.
	// This function is the log in "page" where it then validates them and invokes the loggedInMsg function.
	private void logInMsg() {
		System.out.println("\nLog In (b to go back) \n");
		
		System.out.println("Enter your username: ");
		String username = sc.next();
		
		if (username.equals("b")) {	// if the user accidentally chose to log in, then they can go back by inputting "b".
			welcomeMsg();
		}
		
		System.out.println("\nEnter your password: ");
		String password = sc.next();
		
		User u = us.getUser(username.toLowerCase());
				
		if (u.getUsername() != null && password.equals(u.getPassword())) {
			loggedInMsg(u);
		} else {
			System.out.println("Wrong username or password");
			logInMsg();	// calls itself again if the username / password is wrong.
		}
		
	}
	
	// This function is called after the username and password is validated.
	// This function shows choices to the user like deposit withdraw etc.
	// After the user decides to log out, the welcomeMsg function gets invoked again.
	private void loggedInMsg(User u) {
		
		System.out.println("\n\nWelcome " + u.getFirstName() + " " + u.getLastName() + ".");
		System.out.println("\nWhat would you like to do today?\n\n1. View Balance\n\n2. Deposit\n\n3. Withdraw\n\n4. Transfer to a user\n\n5. Log out\n\n6. Delete account");
		
		String answer = sc.next();
		
		while (answer != null) {	// I use null here since the switch already has a log out feature, and answer can never be null.
									
			switch (answer) {
			// VIEW BALANCE
			case "1":
				
				System.out.println("\nYour balance is: $" + u.getBalance());
				break;
				
			// DEPOSIT
			case "2":
				
				ua.deposit(u);
				u = us.getUser(u.getUsername());	// reassigns "u" to get the updated balance.
				break;
				
			// WITHDRAW
			case "3":
				
				ua.withdraw(u);
				u = us.getUser(u.getUsername());				
				break;
			
			// TRANSFER TO ANOTHER USER
			case "4":
				
				ua.transfer(u);
				u = us.getUser(u.getUsername());
				break;
				
			// LOG OUT
			case "5":
				
				System.out.println("\nLogging out\n");
				welcomeMsg();	// after logging out it goes back to the welcome message prompt.
				break;
				
			// DELETE USER
			case "6":
				
				// checks if the user still has funds in their account before deleting.
				if (u.getBalance() > 0) {	
					System.out.println("\nPlease withdraw all funds before deleting account!");
				} else {
					
					us.deleteUser(u.getUsername());
					System.out.println("\nUser deleted!\n\n");
					welcomeMsg();	// After deleting the account it goes back to the welcome message prompt.
					
				}
				
				break;
			
			default:
				System.out.println("That isn't an option try again!\n\n");
				loggedInMsg(u);
			}
			
			System.out.println("\nWhat else would you like to do?");
			answer = sc.next();	// This switch is in a while loop and will keep asking until the user decides to log out.
		}
		
	}
}