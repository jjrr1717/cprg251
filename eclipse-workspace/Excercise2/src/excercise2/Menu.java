package excercise2;

import java.util.Scanner;

public class Menu {
	
	Scanner keyboard = new Scanner(System.in);
	private int mainSelection;
	private int customerSelection;
	private int productSelection;
	private int orderSelection;
	
	public Menu() 
	{

		
	}
	
	private void displayMainMenu() 
	{
		System.out.println("Welcome to the Store App! " +
							"Please make a selection.\n");
		System.out.println("[1]Customers\n" +
						   "[2]Products\n" +
						   "[3]Orders\n" +
						   "[4]Exit");
	}
	
	private int getMainSelection() 
	{
		System.out.print("Selection: ");
		mainSelection = keyboard.nextInt();
		return mainSelection;
			
	}
	
	private void displayCustomerMenu() 
	{
		System.out.println("\nWelcome to the Customer Menu!");
		System.out.println("Please make a selection.");
		System.out.println("[1]Print customer list\n" +
					       "[2]Add customer\n" +
				           "[3]Return to main menu");
	}
	
	private int getCustomerSelection() 
	{
		System.out.print("Selection: ");
		customerSelection = keyboard.nextInt();
		return customerSelection;
	}
	
	private void displayProductMenu() 
	{
		System.out.println("\nWelcome to the Product Menu!");
		System.out.println("Please make a selection.");
		System.out.println("[1]Print product list\n" +
					       "[2]Add product\n" +
				           "[3]Remove product\n" +
					       "[4]Return to main menu");
	}
	
	private int getProductSelection() 
	{
		System.out.print("Selection: ");
		productSelection = keyboard.nextInt();
		return productSelection;
		
	}
	
	private void displayOrderMenu() 
	{
		System.out.println("\nWelcome to the Order Menu!");
		System.out.println("Please make a selection.");
		System.out.println("[1]Print orders list\n" +
					       "[2]Add order\n" +
				           "[3]Return to main menu");
	}
	
	private int getOrderSelection() 
	{
		System.out.print("Selection: ");
		orderSelection = keyboard.nextInt();
		return orderSelection;
		
	}
	
	private void runCustomerMenu() throws Exception 
	{
		StoreManager manager = new StoreManager();
		int selection = -1;
		while(selection != 3) 
		{
			displayCustomerMenu();
			selection = getCustomerSelection();
			
			switch(selection) 
			{
				case 1:
					manager.printCustomers();
					break;
				case 2:
					manager.addCustomer();
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid Entry. Please select again.");
			}
		}
	}
	
	private void runProductMenu() throws Exception 
	{
		StoreManager manager = new StoreManager();
		int selection = -1;
		while(selection != 4) 
		{
			displayProductMenu();
			selection = getProductSelection();
			
			switch(selection) 
			{
				case 1:
					manager.printProducts();
					break;
				case 2:
					manager.addProduct();
					break;
				case 3:
					manager.removeProduct();
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid Entry. Please select again.");
			}
		}
	}
	
	private void runOrderMenu() throws Exception 
	{
		StoreManager manager = new StoreManager();
		int selection = -1;
		while(selection != 3) 
		{
			displayOrderMenu();
			selection = getOrderSelection();
			
			switch(selection) 
			{
				case 1:
					manager.printOrders();
					break;
				case 2:
					manager.addOrder();
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid Entry. Please select again.");
			}
		}
	}
	
	public void runMainMenu() throws Exception
	{

		while(mainSelection != 4) 
		{
			displayMainMenu();
			mainSelection = getMainSelection();
			
			switch(mainSelection) 
			{
				case 1:
					runCustomerMenu();
					break;
					
				case 2:

					runProductMenu();
					break;
					
				case 3:
					runOrderMenu();
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid Entry. Please make a selection again.");
				
			}
		}
		
		
	} 

}
